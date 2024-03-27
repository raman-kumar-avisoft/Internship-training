import java.util.InputMismatchException;
import java.util.Scanner;

public class SumNoAdjacent {

    public static int maxSumNoAdjacent(int[] arr, int n) {
        if (n == 0) return 0;
        if (n == 1) return arr[0];

        int incl = arr[0];
        int excl = 0;

        for (int i = 1; i < n; i++) {
            int temp = incl;
            incl = Math.max(excl + arr[i], incl);
            excl = temp;
        }

        return Math.max(incl, excl);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int siz = -1;
        try{
            System.out.print("Enter the number of test cases: ");
            siz = scanner.nextInt();
            if(siz <= 0){
                throw new InputMismatchException();
            }
        }catch (InputMismatchException inputMismatchException){
            System.out.println("NOT A VALID SIZE VALUE **");
            System.exit(0);
        }

        int[] arr = new int[siz];
        System.out.println("Enter the element in the array ");
        for (int arrIndex = 0; arrIndex < siz; arrIndex++) {
            arr[arrIndex] = scanner.nextInt();
        }

        System.out.println(maxSumNoAdjacent(arr, siz));

        scanner.close();
    }
}
