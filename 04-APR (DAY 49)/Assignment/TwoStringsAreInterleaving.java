import java.util.InputMismatchException;
import java.util.Scanner;

public class TwoStringsAreInterleaving {
    public static void main(String[] args) {
        Scanner scannerString = new Scanner(System.in);
        int maxWrongTries = 3;
        String firstString = "";
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the first String (all lowercase): ");
                firstString = scannerString.next();
                int firstStringLength = firstString.length();
                for(int firstStringIndex = 0; firstStringIndex < firstStringLength; firstStringIndex++){
                    if(firstString.charAt(firstStringIndex) < 'a' && firstString.charAt(firstStringIndex) > 'z'){
                        throw new InputMismatchException();
                    }
                }
                break;
            }catch(InputMismatchException inputMismatchException){
                scannerString.nextLine();
                System.out.println("NOT A VALID STRING **");
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES**");
            System.exit(0);
        }

        String secondString = "";
        maxWrongTries = 3;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the second String (all lowercase): ");
                secondString = scannerString.next();
                int secondStringLength = secondString.length();
                for(int secondStringIndex = 0; secondStringIndex < secondStringLength; secondStringIndex++){
                    if(secondString.charAt(secondStringIndex) < 'a' && secondString.charAt(secondStringIndex) > 'z'){
                        throw new InputMismatchException();
                    }
                }
                break;
            }catch(InputMismatchException inputMismatchException){
                scannerString.nextLine();
                System.out.println("NOT A VALID STRING **");
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES**");
            System.exit(0);
        }

        String thirdString = "";
        maxWrongTries = 3;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the third(Interleaving) String (all lowercase): ");
                thirdString = scannerString.next();
                int thirdStringLength = thirdString.length();
                for(int thirdStringIndex = 0; thirdStringIndex < thirdStringLength; thirdStringIndex++){
                    if(thirdString.charAt(thirdStringIndex) < 'a' && thirdString.charAt(thirdStringIndex) > 'z'){
                        throw new InputMismatchException();
                    }
                }
                break;
            }catch(InputMismatchException inputMismatchException){
                scannerString.nextLine();
                System.out.println("NOT A VALID STRING **");
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES**");
            System.exit(0);
        }

        boolean interleavingString = checkInterleaving(firstString, secondString, thirdString, 0, 0, 0);
        if(interleavingString){
            System.out.println(thirdString + " can be interleaved by using Strings: " + firstString + " and " + secondString);
        }else{
            System.out.println(thirdString + " can NOT be interleaved by using Strings: " + firstString + " and " + secondString);
        }
    }
    static boolean checkInterleaving(String firstString, String secondString, String thirdString, int firstIndex, int secondIndex, int thirdIndex){
        if(thirdIndex == thirdString.length()){
            return true;
        }
        boolean ans = false;
        if(firstIndex < firstString.length() && firstString.charAt(firstIndex) == thirdString.charAt(thirdIndex)){
            ans |= checkInterleaving(firstString,secondString,thirdString,firstIndex+1,secondIndex,thirdIndex+1);
        }
        if(secondIndex < secondString.length() && secondString.charAt(secondIndex) == thirdString.charAt(thirdIndex)){
            ans |= checkInterleaving(firstString,secondString,thirdString,firstIndex,secondIndex+1,thirdIndex+1);
        }

        return ans;
    }
}