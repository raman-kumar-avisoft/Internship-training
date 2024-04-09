import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class PickCoinsInTurns {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        int maxWrongTries = 3;

        int lineLength = -1;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the number of coins in the line: ");
                lineLength = scannerInteger.nextInt();

                if(lineLength <= 0){
                    throw new InputMismatchException();
                }
                break;
            }catch (InputMismatchException inputMismatchException){
                System.out.println("NOT A VALID LINE LENGTH **");
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }

        int coinsArray[] = new int[lineLength];

        for(int coinsArrayIndex = 0; coinsArrayIndex < lineLength; coinsArrayIndex++){
            maxWrongTries = 3;
            while(--maxWrongTries >= 0){
                try{
                    System.out.println("Enter the coin value at index[" + coinsArrayIndex +"]: ");
                    int coinValue = scannerInteger.nextInt();

                    if(coinValue <= 0){
                        throw new InputMismatchException();
                    }
                    coinsArray[coinsArrayIndex] = coinValue;
                    break;
                }catch (InputMismatchException inputMismatchException){
                    System.out.println("NOT A VALID COIN VALUE **");
                }
            }
            if(maxWrongTries < 0){
                System.out.println("TOO MANY WRONG TRIES **");
                System.exit(0);
            }
        }

        int maxCoins = maxMoney(coinsArray);
        System.out.println("Answer is: " + maxCoins);
    }

    public static int maxMoney(int[] coinsArray) {
        int numberOfCoins = coinsArray.length;
        int[][] dp = new int[numberOfCoins][numberOfCoins];

        // Base case: if only one coin left, return its value
        for (int i = 0; i < numberOfCoins; i++) {
            dp[i][i] = coinsArray[i];
        }

        // Build the dp table from smaller subProblems to larger ones
        for (int gap = 1; gap < numberOfCoins; gap++) {
            for (int i = 0; i < numberOfCoins - gap; i++) {
                int j = i + gap;
                dp[i][j] = Math.max(
                        coinsArray[i] + Math.min(dp[i + 2][j], dp[i + 1][j - 1]),
                        coinsArray[j] + Math.min(dp[i + 1][j - 1], dp[i][j - 2])
                );
            }
        }

        // The answer is stored at dp[0][n-1]
        return dp[0][numberOfCoins - 1];
    }
}