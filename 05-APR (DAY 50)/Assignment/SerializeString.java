import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SerializeString {
    public static void main(String[] args) {
        Scanner scannerString = new Scanner(System.in);
        Scanner scannerInteger = new Scanner(System.in);

        int maxWrongTries = 3;
        int stringSize = -1;

        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the Number of String in the StringArray: ");
                stringSize = scannerInteger.nextInt();

                if(stringSize <= 0){
                    throw new InputMismatchException();
                }
                break;
            }catch (InputMismatchException inputMismatchException){
                System.out.println("NOT A VALID OPTION");
                scannerInteger.nextLine();
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }

        ArrayList<String> stringArrayList = new ArrayList<>();
        for(int stringArrayIndex=0; stringArrayIndex < stringSize; stringArrayIndex++){
            maxWrongTries = 3;
            while(--maxWrongTries >= 0){
                try{
                    String string = scannerString.next();
                    for(int stringIndex=0; stringIndex < string.length(); stringIndex++){
                        if(string.charAt(stringIndex) == '~'){
                            throw new InputMismatchException();
                        }
                    }
                    stringArrayList.add(string);
                    break;
                }catch (InputMismatchException inputMismatchException){
                    System.out.println("NOT A VALID STRING (~) NOT ALLOWED **");
                    scannerString.nextLine();
                }
            }
            if(maxWrongTries < 0){
                System.out.println("TOO MANY WRONG TRIES **");
                System.exit(0);
            }
        }

        String answer = serializeStringArray(stringArrayList);
        System.out.println("AFTER SERIALIZATION STRING CONTAINS: " + answer);
    }

    static String serializeStringArray(ArrayList<String> stringArrayList){
        String serializedString = "";
        for(String string: stringArrayList){
            serializedString += string + string.length() +"~";
        }
        return serializedString;
    }
}