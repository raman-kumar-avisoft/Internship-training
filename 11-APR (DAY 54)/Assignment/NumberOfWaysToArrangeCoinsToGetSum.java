import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NumberOfWaysToArrangeCoinsToGetSum {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        int maxWrongTries = 3;
        while(--maxWrongTries >= 0){
            try {
                System.out.print("Enter the number of coins: ");
                int n = scannerInteger.nextInt();
                int[] A = new int[n];
                System.out.println("Enter the coin denominations:");
                for (int i = 0; i < n; i++) {
                    A[i] = scannerInteger.nextInt();
                }
                System.out.print("Enter the target sum: ");
                int B = scannerInteger.nextInt();

                int ways = coinSumWays(A, B);
                System.out.println("Number of ways to make the sum " + B + ": " + ways);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter integers only.");
            } finally {
                scannerInteger.close();
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }
    }
    public static int coinSumWays(int[] A, int B) {
        int MOD = 1000007;
        int[] dp = new int[B + 1];
        Arrays.fill(dp, 0);
        dp[0] = 1;

        for (int coin : A) {
            for (int j = coin; j <= B; j++) {
                dp[j] = (dp[j] + dp[j - coin]) % MOD;
            }
        }

        return dp[B];
    }
}