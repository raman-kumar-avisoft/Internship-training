import java.util.InputMismatchException;
import java.util.Scanner;

public class Slice_The_Log {
    public static int maxCostCuttingRod(int N, int[] A) {
        int[] dp = new int[N + 1]; // Initialize an array to store maximum costs for each length up to N
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = Math.max(dp[i], A[j] + dp[i - j - 1]); // Update the maximum cost for length i
            }
        }
        return dp[N];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int maxWrongTries = 3;
        int N = -1;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the size of the rod: ");
                N = scanner.nextInt();

                if(N<=0){
                    throw new InputMismatchException();
                }
                break;
            }catch (InputMismatchException inputMismatchException){
                scanner.nextLine();
                System.out.println("NOT A VALID ARRAY SIZE **");
            }

        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }

        int[] arr = new int[N]; // Array to store cost of sub-length

        for (int i = 0; i < N; i++) {
            maxWrongTries = 2;
            while(--maxWrongTries >= 0){
                try{
                    System.out.println("Enter the amount to slice the log in " + (i+1) + ": ");
                    arr[i] = scanner.nextInt();
                    break;
                }catch(InputMismatchException inputMismatchException){
                    scanner.nextLine();
                    System.out.println("NOT A VALID NUMBER **");
                }
            }
            if(maxWrongTries < 0){
                System.out.println("TOO MANY WRONG TRIES **");
                System.exit(0);
            }
        }
        System.out.println(maxCostCuttingRod(N, arr)); // Print the maximum cost for each test case
        scanner.close();
    }
}
