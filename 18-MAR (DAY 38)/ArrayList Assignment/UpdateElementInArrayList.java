import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateElementInArrayList {
    public static void main(String[] args) {
        Scanner scannerString = new Scanner(System.in);
        int maxWrongTries  = 3;
        boolean continueLooping = true;

        ArrayList<String> arrayList = new ArrayList<>();
        System.out.println("Enter the element in the arrayList(-1 to finish)");
        while(continueLooping){
            try{
                System.out.print("Enter the element: ");
                String value = scannerString.next();
                if(value.equals("-1")){
                    continueLooping = false;
                    continue;
                }
                arrayList.add(value);
            } catch (InputMismatchException inputMismatchException){
                maxWrongTries--;
            } finally {
                scannerString.nextLine();
                if(maxWrongTries == 0){
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }

        continueLooping = true;
        maxWrongTries = 3;
        while(continueLooping){
            try{
                System.out.print("Enter the index whose value you want to change: ");
                int index = scannerString.nextInt();
                if(index > 0 && index < arrayList.size()){
                    scannerString.nextLine();
                    System.out.print("Enter the new update value: ");
                    String newValue = scannerString.next();
                    arrayList.set(index, newValue);
                    continueLooping = false;
                }else{
                    System.out.println("NOT A VALID INDEX **");
                }
            } catch (InputMismatchException inputMismatchException){
                maxWrongTries--;
            } finally {
                scannerString.nextLine();
                if(maxWrongTries == 0){
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }

        if(arrayList.size() > 0){
            System.out.println("Elements in the Array after Updated Value is:");
            for(String str: arrayList){
                System.out.print(str+" ");
            }
        }else{
            System.out.println("ARRAYLIST IS EMPTY **");
        }
    }
}