package weekly.Assignment;

import java.io.*;
import java.util.ArrayList;

public class CsvFile {
    private String filepath;
    private String[] columnName;
    private int totalColumns;
    private ArrayList<String> LineData = new ArrayList<>();
    private ArrayList<String> corruptFileData = new ArrayList<>();
    File file = null;


    CsvFile(String path) {
        setFilePath(path);
        setSentencesData(path);
    }

    void setFilePath(String path) {
        try {
//            checking if the file is a csv file or not
            if (!path.substring(path.length() - 4).equals(".csv")) {
                throw new IOException("Not a csv file");
            }
            this.filepath = path;
            File file = new File(this.filepath);

//            if we cannot read the file or file is empty throw exception
            if (!file.canRead()) {
                throw new IOException("FIle is not readable");
            } else if (file.length() == 0) {
                throw new IOException("Empty file");
            }

//            setting our File class object in properties
            setFile(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not exists");
        } catch (IOException e) {
            System.out.println("Enter a valid path that exists");
        }
    }

    void setFile(File file) {
        this.file = file;
    }

    void setSentencesData(String path) {
        String dataLine = "";
        String splitBy = ",";
        try {
//          parsing a CSV file into BufferedReader class constructor
            BufferedReader bufferReader = new BufferedReader(new FileReader(this.filepath));
            dataLine = bufferReader.readLine();
            columnName = dataLine.split(splitBy); // use comma as separator
            while ((dataLine = bufferReader.readLine()) != null)   //returns a Boolean value
            {
                LineData.add(dataLine);
            }

            setTotalColumns();
        } catch (IOException e) {
            System.out.println("There are some Exception in csv file");
        }
//        displayData();
    }

    void displayData() {
        for (int lineDataIndex = 0; lineDataIndex < LineData.size(); lineDataIndex++) {
            System.out.println(LineData.get(lineDataIndex));
        }
    }

    void setTotalColumns() {
        this.totalColumns = columnName.length;
    }

    int getTotalColumns() {
        return this.totalColumns;
    }

    ArrayList<String> getLineData() {
        return this.LineData;
    }

    boolean fileComparison(CsvFile obj, int leaveColumn) {
        int rowIndex;
        boolean ifSameData = true;

        try {
            if (leaveColumn >= getTotalColumns()) {
                throw new Exception("leaving column index is greater than total column in line");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

//        first we are comparing the sentences if they are same then we move to next sentence
        for (rowIndex = 0; rowIndex < LineData.size() && rowIndex < obj.getLineData().size(); rowIndex++) {
            String actualLine = LineData.get(rowIndex);
            String expectedLine = obj.getLineData().get(rowIndex);

            if (!actualLine.equals(expectedLine)) {
                String corruptRowData = "";

                String[] actualLineWords = actualLine.split(","); // split the sentences of file1 with delimiter(",")
                String[] expectedLineWords = expectedLine.split(","); // split the sentences of file2 with delimiter(",")
                int colIndex = 0;

//                checking for the corrupt word in the corrupt sentence
                corruptRowData += "Row Number: " + (rowIndex + 1) + " ";
                for (colIndex = 0; colIndex < actualLineWords.length && colIndex < expectedLineWords.length; colIndex++) {
                    String corruptColumnDataDetails = "";
                    if (!actualLineWords[colIndex].equals(expectedLineWords[colIndex]) && colIndex != leaveColumn) {
                        ifSameData = false;
                        corruptColumnDataDetails += "Column Number: " + (colIndex + 1) + " and Column Name: " + columnName[colIndex] + ", " + actualLineWords[colIndex] + "," + expectedLineWords[colIndex];
                        this.corruptFileData.add(corruptRowData + corruptColumnDataDetails);
                    }
                }

//                MISSING COLUMNS CODE
//                printing the missing words which are not present in the actual file but are in secondary file
//                boolean firstTimeEntry = true; // for first time we will print missing words
//                while (colIndex < expectedLineWords.length) {
//                    if(firstTimeEntry){
//                        corruptRowData += "Missing Columns are: "+ "\"" + expectedLineWords[colIndex] + "\" ";
//                        firstTimeEntry = false;
//                    }else{
//                        corruptRowData += "," + "\"" + expectedLineWords[colIndex] + "\" ";
//                    }
//                    colIndex++;
//                }

            }
        }

//        MISSING LINES CODE
////        printing Missing sentences (data) which is not in the actual file but present in the second file
//        while (rowIndex < obj.getLineData().size()) {
//            this.corruptFileData.add("Missing row: " + (rowIndex+2) + obj.getLineData().get(rowIndex));
//            rowIndex++;
//        }

        return ifSameData;
    }

    ArrayList<String> getCorruptFileData() {
        return this.corruptFileData;
    }
}
