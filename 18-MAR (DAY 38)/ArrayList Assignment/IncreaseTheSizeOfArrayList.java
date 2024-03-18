import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IncreaseTheSizeOfArrayList {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);

        int maxWrongTries = 3;
        boolean continueLooping = true;
        int size = -1;

        while(continueLooping){
            try{
                ArrayList<String> arrayList = new ArrayList<>(3);
                arrayList.add("Raj");
                arrayList.add("Raman");
                arrayList.add("Money");
                System.out.println("ArrayList size: " + arrayList.size());

                arrayList.ensureCapacity(5);
                System.out.println("New ArrayList size: " + arrayList.size());
                arrayList.add("Famous");
                arrayList.add("Respect");
                System.out.println("New ArrayList size: " + arrayList.size());
                continueLooping = false;
            }catch (InputMismatchException inputMismatchException){
                maxWrongTries--;
            }finally {
                if(maxWrongTries == 0){
                    System.out.println("TOO MANY WRONG TRIES");
                }
            }
        }

    }
}