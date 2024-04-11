import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DivideArrayInTwoHalves {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        int maxWrongTries = 3;

        int arraySize = -1;
        while (--maxWrongTries >= 0) {
            try {
                System.out.print("Enter the size of the array: ");
                arraySize = scannerInteger.nextInt();
                if (arraySize <= 0) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException inputMismatchException) {
                scannerInteger.nextLine();
                System.out.println("NOT A VALID ARRAY SIZE **");
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int arrayListIndex=0; arrayListIndex < arraySize; arrayListIndex++){
            maxWrongTries = 3;
            while(--maxWrongTries >= 0){
                try{
                    System.out.print("Enter the value of element at index [" + arrayListIndex + "]: ");
                    int value = scannerInteger.nextInt();
                    arrayList.add(value);
                    break;
                }catch (InputMismatchException inputMismatchException){
                    scannerInteger.nextLine();
                    System.out.println("NOT A VALID ELEMENT VALUE **");
                }
            }
            if(maxWrongTries < 0){
                System.out.println("TOO MANY WRONG TRIES **");
                System.exit(0);
            }
        }

        ArrayList<ArrayList<Integer>> answer = findTwoHalvesWhoseAvgSumIsEqual(arrayList);
        for(ArrayList<Integer> value: answer){
            System.out.println(value);
        }

    }

    public static ArrayList<ArrayList<Integer>> findTwoHalvesWhoseAvgSumIsEqual(ArrayList<Integer> A) {
        int totalSum = 0;
        for (int num : A) {
            totalSum += num;
        }
        int n = A.size();

        // Dynamic programming array to store whether a subset with sum i can be formed using elements up to j
        boolean[][] dp = new boolean[n + 1][totalSum / 2 + 1];

        // Base case: subset with sum 0 can always be formed
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Dynamic programming to fill the dp array
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= totalSum / 2; j++) {
                if (j >= A.get(i - 1)) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - A.get(i - 1)];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        if (!dp[n][totalSum / 2]) {
            return new ArrayList<>();
        }

        // Reconstructing the subset with sum equal to half of the total sum
        int subsetSum = totalSum / 2;
        ArrayList<Integer> subset = new ArrayList<>();
        int i = n, j = subsetSum;
        while (i > 0 && j > 0) {
            if (dp[i][j] && !dp[i - 1][j]) {
                subset.add(A.get(i - 1));
                j -= A.get(i - 1);
            }
            i--;
        }

        // Forming the two parts based on the subset
        ArrayList<Integer> part1 = subset;
        ArrayList<Integer> part2 = new ArrayList<>();
        for (int num : A) {
            if (!part1.contains(num)) {
                part2.add(num);
            }
        }

        // Sorting both parts
        part1.sort(null);
        part2.sort(null);

        // Returning the two parts
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add(part1);
        result.add(part2);
        return result;
    }
}