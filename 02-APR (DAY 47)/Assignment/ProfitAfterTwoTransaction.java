import java.util.InputMismatchException;
import java.util.Scanner;

public class ProfitAfterTwoTransaction {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        int maxWrongTries = 3;
        int days = -1;
        while (--maxWrongTries >= 0) {
            try {
                System.out.print("Enter the number of days: ");
                days = scannerInteger.nextInt();
                if (days <= 0) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("NOT A VALID NUMBER OF DAYS **");
                scannerInteger.nextLine();
            }
        }
        if (maxWrongTries < 0) {
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }

        int stockPrice[] = new int[days];
        for (int stockPriceIndex = 0; stockPriceIndex < days; stockPriceIndex++) {
            maxWrongTries = 3;
            while (--maxWrongTries >= 0) {
                try {
                    System.out.print("Enter the stock price for day " + (stockPriceIndex + 1) + ": ");
                    stockPrice[stockPriceIndex] = scannerInteger.nextInt();

                    break;
                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("NOT A VALID STOCK VALUE **");
                    scannerInteger.nextLine();
                }
            }
            if (maxWrongTries < 0) {
                System.out.println("TOO MANY WRONG VALUES **");
                System.exit(0);
            }
        }

        int answer = findMaxProfitAfterTwoTransaction(stockPrice, days);
        System.out.println("Maximum Profit after two transaction is: " + answer);
    }

    static int findMaxProfitAfterTwoTransaction(int[] stockPrice, int days) {
        int maxFirstTransactionPrices[] = new int[days];
        int maxSecondTransactionPrices[] = new int[days];

        int maxFirstTransaction = 0;
        int maxSecondTransaction = 0;

//        updating maxFirstTransactionPrices
        int minPrice = stockPrice[0];
        for (int daysIndex = 1; daysIndex < days; daysIndex++) {
            minPrice = Math.min(minPrice, stockPrice[daysIndex]);
            maxFirstTransaction = Math.max(maxFirstTransaction, stockPrice[daysIndex] - minPrice);
            maxFirstTransactionPrices[daysIndex] = maxFirstTransaction;
        }
        for(int value: maxFirstTransactionPrices){
            System.out.print(value+" ");
        }
        System.out.println();
//        System.out.println(maxFirstTransactionPrices);
        int maxPrice = stockPrice[days - 1];
        for (int daysIndex = days - 2; daysIndex >= 0; daysIndex--) {
            maxPrice = Math.max(maxPrice, stockPrice[daysIndex]);
            maxSecondTransaction = Math.max(maxSecondTransaction, maxPrice - stockPrice[daysIndex]);
            maxSecondTransactionPrices[daysIndex] = maxSecondTransaction;
        }
        for(int value: maxSecondTransactionPrices){
            System.out.print(value+" ");
        }
        System.out.println();
//        System.out.println(maxSecondTransactionPrices);
//        now for each iteration we check the maxProfit after two iteration
        int maxProfit = 0;
        for(int daysIndex = 0; daysIndex < days; daysIndex++){
            maxProfit = Math.max(maxProfit, maxFirstTransactionPrices[daysIndex] + maxSecondTransactionPrices[daysIndex]);
        }
        return maxProfit;
    }
}