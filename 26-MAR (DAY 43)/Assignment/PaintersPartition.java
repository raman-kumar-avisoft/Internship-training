import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PaintersPartition {

    public static boolean isPossible(int[] arr, int k, int mid) {
        int total = 0;
        int painters = 1;
        for (int length : arr) {
            total += length;
            if (total > mid) {
                total = length;
                painters++;
                if (painters > k) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int minTimeToPaintBoards(int[] arr, int k) {
        int low = Arrays.stream(arr).max().getAsInt(); // minimum time will be when each painter paints one board
        int high = Arrays.stream(arr).sum(); // maximum time will be when one painter paints all boards
        int result = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isPossible(arr, k, mid)) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        int maxWrongTries = 3;
        int sizee = -1;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the size of the array: ");
                sizee = scannerInteger.nextInt();
                if(sizee <= 0){
                    throw new InputMismatchException();
                }
                break;
            }catch (InputMismatchException inputMismatchException){
                scannerInteger.nextLine();
                System.out.println("NOT A VALID NUMBER");
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES");
            System.exit(0);
        }

        int[] arr = new int[sizee];
        for(int arrIndex = 0; arrIndex < sizee; arrIndex++){
            arr[arrIndex] = scannerInteger.nextInt();
        }

        maxWrongTries = 3;
        int K = -1;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the number of painters: ");
                K = scannerInteger.nextInt();
                if(K <= 0){
                    throw new InputMismatchException();
                }
                break;
            }catch (InputMismatchException inputMismatchException){
                scannerInteger.nextLine();
                System.out.println("NOT A VALID NUMBER");
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES");
            System.exit(0);
        }

        System.out.println(minTimeToPaintBoards(arr, K)); // Output: 11

    }
}
