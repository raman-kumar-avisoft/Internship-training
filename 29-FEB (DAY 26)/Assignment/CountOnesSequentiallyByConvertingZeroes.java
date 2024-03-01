import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class CountOnesSequentiallyByConvertingZeroes {
    public static void main(String[] args) {

        Scanner scannerInt = new Scanner(System.in);
        int binaryArraySize = -1;
        int MaxErrorAllowed = 3;
        while (MaxErrorAllowed >= 1) {
            try {
                System.out.print("Enter the Size for the binary Array: ");
                binaryArraySize = scannerInt.nextInt();
                MaxErrorAllowed = -1;
            } catch (InputMismatchException e) {
                MaxErrorAllowed--;
                System.out.println(e.getMessage());
            }
        }
        if (MaxErrorAllowed == 0) {
            System.out.println("Too many wrong Tries --");
            System.exit(0);
        }

        int[] binaryArray = new int[binaryArraySize];
        System.out.println("Enter the element in the array: ");
        int wrongInputTries = 3;
        for (int binaryArrayIndex = 0; binaryArrayIndex < binaryArraySize; binaryArrayIndex++) {
            try {
                int value = scannerInt.nextInt();
                if (value != 0 && value != 1) {
                    binaryArrayIndex--;
                    wrongInputTries--;
                    if (wrongInputTries == 0) {
                        break;
                    }
                } else {
                    binaryArray[binaryArrayIndex] = value;
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                wrongInputTries--;
                binaryArrayIndex--;
            }
        }
        if (wrongInputTries == 0) {
            System.out.println("Wrong Inputted value too many times --");
            System.exit(0);
        }

        int maxZeroesCanReplaced = -1;
        MaxErrorAllowed = 3;
        while (MaxErrorAllowed >= 1) {
            try {
                System.out.print("Enter the Number of Zeroes That can be Replaced: ");
                maxZeroesCanReplaced = scannerInt.nextInt();
                MaxErrorAllowed = -1;
            } catch (InputMismatchException e) {
                MaxErrorAllowed--;
                System.out.println(e.getMessage());
            }
        }
        if (MaxErrorAllowed == 0) {
            System.out.println("Too many wrong Tries --");
            System.exit(0);
        }

        int answer = maxSequentiallyLengthOfOnes(binaryArray, maxZeroesCanReplaced);
        System.out.println("Maximum consecutive ones with some allowance is: " + answer);
    }

    static int maxSequentiallyLengthOfOnes(int[] binaryArray, int maxZeroesCanReplaced) {
        Queue<Integer> zeroesReplacedIndices = new LinkedList<>(); // the size of this queue will be <= maxZeroesCanReplaced.
        Queue<Integer> onesIndices = new LinkedList<>(); // this will track of the ones indices we will counter while traversing.

        int ans = 0;
        int minIndex = Integer.MAX_VALUE;

        for (int binaryArrayIndex = 0; binaryArrayIndex < binaryArray.length; binaryArrayIndex++) {
            if (binaryArray[binaryArrayIndex] == 0 && maxZeroesCanReplaced > 0) {
                maxZeroesCanReplaced--;
                zeroesReplacedIndices.add(binaryArrayIndex);
                ans = Math.max(ans, binaryArrayIndex - minIndex + 1);
            } else if (binaryArray[binaryArrayIndex] == 1) {
                onesIndices.add(binaryArrayIndex);
                minIndex = Math.min(minIndex, binaryArrayIndex);
                ans = Math.max(ans, binaryArrayIndex - minIndex + 1);
            } else if (binaryArray[binaryArrayIndex] == 0) {
                if (!zeroesReplacedIndices.isEmpty()) {
                    minIndex = zeroesReplacedIndices.poll() + 1;
                    zeroesReplacedIndices.add(binaryArrayIndex);
                    ans = Math.max(ans, binaryArrayIndex - minIndex + 1);
                }
            }
        }
        return ans;
    }
}