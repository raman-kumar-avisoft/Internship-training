import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LinearSearchInArrayList {
    public static void main(String[] args) {
        Scanner scannerString = new Scanner(System.in);

        ArrayList<String> arrayList = new ArrayList<>();
        System.out.println("Enter the element in the ArrayList (-1 to finish Inserting)");
        int maxWrongTries = 3;
        boolean continueLooping = true;
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

        maxWrongTries = 3;
        continueLooping = true;
        String value = "";
        while(continueLooping){
            try{
                System.out.print("Enter the String you want to search in the arrayList: ");
                value = scannerString.next();
                continueLooping = false;
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

        int index = linearSearch(arrayList, value);
        if (index == -1) {
            System.out.println("ELEMENT NOT FOUND **");
        }else{
            System.out.println("ELEMENT FOUND AT THE INDEX " + index);
        }

        displayArrayList(arrayList);
    }
    static void displayArrayList(ArrayList<String> arrayList){
        System.out.println("Elements in the Array: ");
        for(String value: arrayList){
            System.out.print(value+" ");
        }
    }
    static int linearSearch(ArrayList<String> arrayList, String value){
        for(int arrayListIndex = 0; arrayListIndex < arrayList.size(); arrayListIndex++){
            if(value.equals(arrayList.get(arrayListIndex))){
                return arrayListIndex;
            }
        }
        return -1;
    }
}