import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RetriveElemFromSpecificIndex {
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

        maxWrongTries = 2;
        continueLooping = true;
        int index = -1;
        while (continueLooping){
            try {
                System.out.print("Enter the index you want to see the value at: ");
                index = scannerInteger.nextInt();
                if(index>0 && index < arrayList.size()){
                    System.out.println("Element at index " + index + " is: " + arrayList.get(index));
                    continueLooping = false;
                }else{
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException inputMismatchException) {
                maxWrongTries--;
            } finally {
                scannerInteger.nextLine();
                if (maxWrongTries == 0) {
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }
        if(maxWrongTries != 0){
            System.out.println("INDEX IS OUT OF BOUNDS **");
        }
    }
}