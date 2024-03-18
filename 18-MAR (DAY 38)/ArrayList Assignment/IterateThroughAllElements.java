import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IterateThroughAllElements {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);

        System.out.println("Enter the element in the arraylist (-1 to finish)");

        int maxWrongTries = 2;
        boolean continueLooping = true;
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (continueLooping) {
            try {
                System.out.print("Enter the element Value: ");
                int value = scannerInteger.nextInt();
                if(value == -1){
                    continueLooping = false;
                    continue;
                }
                arrayList.add(value);
            } catch (InputMismatchException inputMismatchException) {
                maxWrongTries--;
                System.out.println("NOT A VALID NUMBER **");
            } finally {
                scannerInteger.nextLine();
                if (maxWrongTries == 0) {
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }

        if(arrayList.size() == 0){
            System.out.println("ARRAYLIST IS EMPTY **");
        }else{
            System.out.print("Element in the arrayList are: ");
            for(int value: arrayList){
                System.out.print(value+" ");
            }
        }
    }
}