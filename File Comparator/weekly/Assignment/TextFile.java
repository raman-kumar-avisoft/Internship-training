package weekly.Assignment;

import java.io.*;
import java.util.ArrayList;

public class TextFile {
    private String filePath;
    private String fileData;
    private ArrayList<String> sentences = new ArrayList<>();
    File file = null;

    TextFile(String textFilePath) {
        setFilePath(textFilePath);
        setFileData(textFilePath);
        setSentences();
    }

    void setFile(File file) {
        this.file = file;
    }

    void setFilePath(String path) {
        try {
//            checking if the file is a txt file or not.
            if (!path.substring(path.length() - 4).equals(".txt")) {
                throw new IOException("Not a text file");
            }

//            initialize the filepath and creating a File object.
            this.filePath = path;
            File file = new File(this.filePath);

//            if we cannot read the file or the file is empty throw exception.
            if (!file.canRead()) {
                throw new IOException("File is not readable");
            } else if (file.length() == 0) {
                throw new IOException("Empty file");
            }

//            initializing the File object as well.
            setFile(file);

        } catch (FileNotFoundException e) {
            System.out.println("File not exists");
        } catch (IOException e) {
            System.out.println("Enter a valid path that exists");
        }
    }

    void setFileData(String textFilePath) {

//        initialize the fileData variable to empty string
        this.fileData = "";

//        reading the file using bufferReader
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(textFilePath));
            while (bufferedReader.ready()) {
                this.fileData += bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Exception caught while using buffer reader in text file. i.e there are some IOException");
        }
    }

    void setSentences() {

//        after reading the data we can be split the data in sentences so that we know where is the corrupt data more precisely
        String[] sentence = this.fileData.split("\\. ");
        for (int i = 0; i < sentence.length; i++) {
            sentences.add(sentence[i]);
        }

        //displaySentences();
    }

    void displaySentences() {
        for (int sentenceIndex = 0; sentenceIndex < sentences.size(); sentenceIndex++) {
            System.out.println(sentences.get(sentenceIndex));
        }
    }

    ArrayList<String> getSentences() {
        return this.sentences;
    }

    String getFileData() {
        return this.fileData;
    }

    void fileComparison(TextFile obj) {
        int sentencesIndex;

//        first we are comparing the sentences if they are same then we move to next sentence
        for (sentencesIndex = 0; sentencesIndex < sentences.size() && sentencesIndex < obj.getSentences().size(); sentencesIndex++) {
            String actualSentence = sentences.get(sentencesIndex);
            String expectedSentence = obj.getSentences().get(sentencesIndex);

//            if sentence don't match
            if (!actualSentence.equals(expectedSentence)) {
                System.out.println("\nCorrupt in sentence: " + (sentencesIndex + 1)); // row number of sentence in 1 based indexing
                System.out.print("Actual sentence is: " + "\"" + actualSentence + "\"");
                System.out.print(", Corrupt sentence is: " + "\"" + expectedSentence + "\"");
                System.out.print(", Corrupt words in the sentence are: ");

//                Now we know the sentences are not matching and to get to root of problem we will split the sentence with " " to make them array of words.
                String[] wordsSentence1 = actualSentence.split(" ");
                String[] wordsSentence2 = expectedSentence.split(" ");

//                checking for the corrupt word in the corrupt sentence
                int wordsIndex = 0;
                for (wordsIndex = 0; wordsIndex < wordsSentence1.length && wordsIndex < wordsSentence2.length; wordsIndex++) {
                    if (!wordsSentence1[wordsIndex].equals(wordsSentence2[wordsIndex])) {
                        System.out.print("\"" + wordsSentence2[wordsIndex] + "\", ");
                    }
                }
//                printing the extra words which are not present in the actual file but are in second file
                while (wordsIndex < wordsSentence2.length) {
                    System.out.print("Missing Words are: " + "\"" + wordsSentence2[wordsIndex] + "\", ");
                    wordsIndex++;
                }
            }
        }
//        printing extra sentences (data) which is not in the actual file but present in the second file
        while (sentencesIndex < obj.getSentences().size()) {
            System.out.println("Missing Sentences are: " + obj.getSentences().get(sentencesIndex));
            sentencesIndex++;
        }
    }
}
