import java.util.InputMismatchException;
import java.util.Scanner;

public class MinimumHorseStaplesPreservingOrder {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        Scanner scannerString = new Scanner(System.in);

        int maxWrongTries = 3;
        int horsesStrength = -1;
        while (--maxWrongTries >= 0) {
            try {
                System.out.print("Enter the strength of the horses: ");
                horsesStrength = scannerInteger.nextInt();
                if (horsesStrength <= 0) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException inputMismatchException) {
                scannerInteger.nextLine();
                System.out.println("NOT A VALID HORSES STRENGTH **");
            }
        }
        if (maxWrongTries < 0) {
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }
        String horses = "";
        for (int horsesIndex = 0; horsesIndex < horsesStrength; horsesIndex++) {
            maxWrongTries = 3;
            while (--maxWrongTries >= 0) {
                try {
                    System.out.print("Enter the horse colour(W->WHITE, B->BLACK) at index [" + horsesIndex + "]: ");
                    char horseColour = scannerString.next().charAt(0);

                    if (horseColour != 'W' && horseColour != 'B') {
                        throw new InputMismatchException();
                    }
                    horses += horseColour;
                    break;
                } catch (InputMismatchException inputMismatchException) {
                    scannerString.nextLine();
                    System.out.println("NOT A VALID HORSE COLOUR **");
                }
            }
            if (maxWrongTries < 0) {
                System.out.println("TOO MANY WRONG TRIES **");
                System.exit(0);
            }
        }

        maxWrongTries = 3;
        int K = -1;
        while (--maxWrongTries >= 0) {
            try {
                System.out.print("Enter the Number of the staples: ");
                K = scannerInteger.nextInt();
                if (K <= 0 || K > horsesStrength) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException inputMismatchException) {
                scannerInteger.nextLine();
                System.out.println("NOT A VALID HORSES STAPLES **");
            }
        }
        if (maxWrongTries < 0) {
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }
        int answer = arrangeHorses(horses, K);
        System.out.println("ANSWER IS: " + answer);
    }
    public static int arrangeHorses(String horses, int K) {
        int N = horses.length();
        int[][] dp = new int[N + 1][K + 1];

        // Initialize dp array to a large value
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        // Base case: no horses, no stables
        dp[0][0] = 0;

        // Dynamic programming to fill the dp array
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= K; j++) {
                int whiteCount = 0;
                int blackCount = 0;

                // Iterate from the current horse to the first horse
                // and update dp[i][j] by considering all possible splits
                for (int k = i; k >= 1; k--) {
                    if (horses.charAt(k - 1) == 'W') {
                        whiteCount++;
                    } else {
                        blackCount++;
                    }

                    // Update dp[i][j] using the cost of the current split
                    if (dp[k - 1][j - 1] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[k - 1][j - 1] + (whiteCount * blackCount));
                    }
                }
            }
        }

        // If a solution is not possible, return -1
        if (dp[N][K] == Integer.MAX_VALUE) {
            return -1;
        }
        return dp[N][K];
    }
}