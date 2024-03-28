import java.util.*;

public class Parition_Array {

    public static int countPartitions(int N, int D, int[] ARR) {
        int sum = Arrays.stream(ARR).sum();
        if ((sum + D) % 2 != 0) {
            return 0;
        }
        int S = (sum + D) / 2;
        int MOD = 1000000007;
        int[][] dp = new int[N + 1][S + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= S; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - ARR[i - 1] >= 0) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - ARR[i - 1]]) % MOD;
                }
            }
        }
        return dp[N][S];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int maxWrongTries = 3;
        int N = -1;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the Number of Houses in the Society: ");
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
                    System.out.println("Enter the gold at house indexed " + i + ": ");
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
        int D = -1;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the Number of Houses in the Society: ");
                D = scanner.nextInt();

                if(D <= 0){
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
        System.out.println(countPartitions(N, D, arr)); // Print the number of partitions satisfying the conditions
        scanner.close();
    }
}
