import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TextFormatter {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        Scanner scannerString = new Scanner(System.in);
        int stringArrayLength = -1;
        int maxWrongTries = 3;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the size of string array length: ");
                stringArrayLength = scannerInteger.nextInt();
                if(stringArrayLength <= 0){
                    throw new InputMismatchException();
                }
                break;
            }catch (InputMismatchException inputMismatchException){
                scannerInteger.nextInt();
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }

        String[] stringArray = new String[stringArrayLength];
        System.out.println("INSERT THE ELEMENTS IN THE ARRAY OF STRING TYPE: ");
        for(int stringArrayIndex = 0; stringArrayIndex < stringArrayLength; stringArrayIndex++){
            maxWrongTries = 3;
            while(--maxWrongTries >= 0){
                try{
                    System.out.print("Enter the string at index " + stringArrayIndex + ": ");
                    stringArray[stringArrayIndex] = scannerString.next();

                    for(int stringIndex=0; stringIndex < stringArray[stringArrayIndex].length(); stringIndex++){
                        char ch = stringArray[stringArrayIndex].charAt(stringIndex);
                        if(!(ch >= 'a' && ch <='z') && !(ch >='A' && ch <='Z')){
                            throw new InputMismatchException();
                        }
                    }
                    break;
                }catch (InputMismatchException inputMismatchException){
                    scannerString.nextLine();
                    System.out.println("NOT A VALID WORD **");
                }
            }
            if(maxWrongTries < 0){
                System.out.println("TOO MANY WRONG TRIES **");
                System.exit(0);
            }
        }

        int maxCharactersInLine = -1;
        maxWrongTries = 3;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter maximum Characters in Line: ");
                maxCharactersInLine = scannerInteger.nextInt();
                if(maxCharactersInLine <= 0){
                    throw new InputMismatchException();
                }
                break;
            }catch (InputMismatchException inputMismatchException){
                scannerInteger.nextLine();
                System.out.println("NOT A VALID CHARACTERS LENGTH **");
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }

        ArrayList<String> answer = setLineWithMaximumCharacters(stringArray, stringArrayLength, maxCharactersInLine);
        if(answer.size() == 0){
            System.out.println("NOT POSSIBLE TO FORMAT A ARRAY OF STRING IN THESE LESS CHARACTERS");
        }else{
            displayAnswer(answer);
        }
    }
    static void displayAnswer(ArrayList<String> answer){
        for(int answerIndex=0; answerIndex < answer.size(); answerIndex++){
            System.out.println(answer.get(answerIndex));
        }
    }

    static ArrayList<String> setLineWithMaximumCharacters(String[] stringArray, int stringArrayLength, int maxCharactersInLine){
        ArrayList<String> answer = new ArrayList<>();
        String newLine = "";
        for(int stringArrayIndex=0; stringArrayIndex < stringArrayLength; stringArrayIndex++){
            if(newLine.length() + stringArray[stringArrayIndex].length() + 1 <= maxCharactersInLine){
                if(newLine.length() == 0){
                    newLine += stringArray[stringArrayIndex];
                }else{
                    newLine += " " + stringArray[stringArrayIndex];
                }
            }else{
                answer.add(newLine);
                newLine = stringArray[stringArrayIndex];
            }
        }
        if(!newLine.equals("")){
            answer.add(newLine);
        }

        ArrayList<Integer> spaces = new ArrayList<>();
        for(String str: answer){
            String[] words = str.split(" ");
            spaces.add(words.length-1);
        }

        ArrayList<String> modifiedAnswer = new ArrayList<>();
        for (int answerIndex = 0; answerIndex < answer.size(); answerIndex++) {
            int len = answer.get(answerIndex).length();
            int space = spaces.get(answerIndex);
            int distributeSpaces = maxCharactersInLine - len + space;

            String newLine2 = "";
            String spacesStr = "";
            for (int i = 0; i < distributeSpaces / 2; i++) {
                spacesStr += " ";
            }
            System.out.println("Spaces are: " + distributeSpaces/2);
            if (distributeSpaces % 2 == 0) {
                for (int strIndex = 0; strIndex < answer.get(answerIndex).length(); strIndex++) {
                    if (answer.get(answerIndex).charAt(strIndex) == ' ') {
                        newLine2 += spacesStr;
                    } else {
                        newLine2 += answer.get(answerIndex).charAt(strIndex);
                    }
                }
                modifiedAnswer.add(newLine2);
            }else{
                boolean first = true;
                for(int strIndex = 0; strIndex < answer.get(answerIndex).length(); strIndex++){
                    if(answer.get(answerIndex).charAt(strIndex) == ' '){
                        if(first){
                            newLine2 += spacesStr + " ";
                            first = false;
                        }else{
                            newLine2 += spacesStr;
                        }
                    }else{
                        newLine2 += answer.get(answerIndex).charAt(strIndex);
                    }
                }
                modifiedAnswer.add(newLine2);
            }
        }
        return modifiedAnswer;
    }
}