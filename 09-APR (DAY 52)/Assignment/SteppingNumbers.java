import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Math.*;

public class SteppingNumbers {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        int maxWrongTries = 3;
        int startNumber = -1;
        int endNumber = -1;
        while (--maxWrongTries >= 0) {
            try {
                System.out.print("Enter the first Number (inclusive): ");
                startNumber = scannerInteger.nextInt();

                System.out.print("Enter the Ending Number (inclusive):");
                endNumber = scannerInteger.nextInt();


                if (startNumber > endNumber) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("NOT IN VALID PARAMETERS **");
                scannerInteger.nextLine();
            }
        }
        if (maxWrongTries < 0) {
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }
        ArrayList<Integer> answer = absDifOne(startNumber, endNumber);
        for (int value : answer) {
            System.out.print(value + " ");
        }
    }

    static ArrayList<Integer> absDifOne(int startNumber, int endNumber) {
        ArrayList<Integer> answer = new ArrayList<>();
        if (endNumber <= 9) {
            return answer;
        }

        for (int i = startNumber; i <= endNumber; i++) {
            int value = checkAbsoluteDiff(i);
            if (value != -1) {
                answer.add(i);
            }
        }
        return answer;
    }

    static int checkAbsoluteDiff(int num) {
        int numLength = 0;
        int numCopy = num;
        while (num != 0) {
            numLength++;
            num = num / 10;
        }
        num = numCopy;
        int[] arr = new int[numLength];

        for (int numIndex = 0; numIndex < numLength; numIndex++) {
            arr[numIndex] = numCopy % 10;
            numCopy /= 10;
        }

        int answer = -1;
        boolean corNum = true;
        for (int arrIndex = 1; arrIndex < numLength; arrIndex++) {
            int dif = abs(arr[arrIndex] - arr[arrIndex - 1]);
            if (dif != 1) {
                corNum = false;
            }
        }
        if (corNum) {
            return num;
        } else {
            return -1;
        }
    }
}