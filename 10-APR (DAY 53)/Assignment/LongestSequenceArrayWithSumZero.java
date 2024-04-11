import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.SortedMap;

public class LongestSequenceArrayWithSumZero {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        int maxWrongTries = 3;

        int arraySize = -1;
        while (--maxWrongTries >= 0) {
            try {
                System.out.print("Enter the size of the array: ");
                arraySize = scannerInteger.nextInt();
                if (arraySize <= 0) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException inputMismatchException) {
                scannerInteger.nextLine();
                System.out.println("NOT A VALID ARRAY SIZE **");
            }
        }
        if (maxWrongTries < 0) {
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }

        int array[] = new int[arraySize];
        for (int arrayIndex = 0; arrayIndex < arraySize; arrayIndex++) {
            maxWrongTries = 3;
            while (--maxWrongTries >= 0) {
                try {
                    System.out.print("Enter the value at index [" + arrayIndex + "]: ");
                    array[arrayIndex] = scannerInteger.nextInt();
                    break;
                } catch (InputMismatchException inputMismatchException) {
                    scannerInteger.nextLine();
                    System.out.println("NOT A VALID ARRAY ELEMENT VALUE **");
                }
            }
            if (maxWrongTries < 0) {
                System.out.println("TOO MANY WRONG INPUTTED VALUES **");
                System.exit(0);
            }
        }

        longestSequenceWithSumZero(array);
    }

    static void longestSequenceWithSumZero(int[] array) {
        int arraySize = array.length;
        ArrayList<Integer> answer = new ArrayList<>();
        for (int arrayIndex = 0; arrayIndex < arraySize; arrayIndex++) {
            ArrayList<Integer> sequence = new ArrayList<>();
            int sum = 0;
            for (int sequenceArray = arrayIndex; sequenceArray < arraySize; sequenceArray++) {
                sum += array[sequenceArray];
                sequence.add(array[sequenceArray]);
                if (sum == 0) {
                    if (answer.size() == 0 || sequence.size() > answer.size()) {
                        answer = sequence;
                    }
                }
            }
        }
        if (answer.size() != 0) {
            System.out.print("LONGEST SUBSEQUENCE IS: ");
            for (int value : answer) {
                System.out.print(value + " ");
            }
        } else {
            System.out.println("NO SUBSEQUENCE WHOSE SUM OF ELEMENTS IS EQUAL TO ZERO **");
        }
    }
}