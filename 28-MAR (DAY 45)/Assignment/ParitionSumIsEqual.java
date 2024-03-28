import java.util.*;

public class ParitionSumIsEqual {
    public static boolean canPartition(int[] nums) {
        int n = nums.length;
        int totalSum = 0;

        // Calculate the total sum of elements in the array
        for (int num : nums) {
            totalSum += num;
        }

        // If the total sum is odd, it can't be partitioned into two equal subsets
        if (totalSum % 2 != 0) {
            return false;
        }

        int targetSum = totalSum / 2;
        boolean[] dp = new boolean[targetSum + 1];
        dp[0] = true; // Base case: an empty subset can always have a sum of 0

        // Dynamic programming to find if there exists a subset with sum equal to targetSum
        for (int num : nums) {
            for (int i = targetSum; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }

        return dp[targetSum];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int T = 0; // Number of test cases
        boolean validInputT = false;
        int attemptsT = 0;
        while (!validInputT && attemptsT < 3) {
            try {
                System.out.println("Enter the number of test cases:");
                T = Integer.parseInt(scanner.nextLine());
                if (T > 0) {
                    validInputT = true;
                } else {
                    System.out.println("Invalid input! Please enter a positive integer value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter an integer value.");
            }
            attemptsT++;
        }

        if (validInputT) {
            for (int t = 0; t < T; t++) {
                int N = 0; // Size of the array
                boolean validInputN = false;
                int attemptsN = 0;
                while (!validInputN && attemptsN < 3) {
                    try {
                        System.out.println("Enter the size of array for test case " + (t + 1) + ":");
                        N = Integer.parseInt(scanner.nextLine());
                        if (N > 0) {
                            validInputN = true;
                        } else {
                            System.out.println("Invalid input! Please enter a positive integer value.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter an integer value.");
                    }
                    attemptsN++;
                }

                if (validInputN) {
                    int[] ARR = new int[N]; // Array of positive integers

                    System.out.println("Enter the elements of array for test case " + (t + 1) + ":");
                    for (int i = 0; i < N; i++) {
                        boolean validInputArr = false;
                        int attemptsArr = 0;
                        while (!validInputArr && attemptsArr < 3) {
                            try {
                                ARR[i] = Integer.parseInt(scanner.nextLine());
                                validInputArr = true;
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input! Please enter an integer value.");
                            }
                            attemptsArr++;
                        }
                    }

                    boolean result = canPartition(ARR);
                    System.out.println("Result for test case " + (t + 1) + ": " + result);
                } else {
                    System.out.println("Failed to read input for test case " + (t + 1) + " after 3 attempts.");
                }
            }
        } else {
            System.out.println("Failed to read input for the number of test cases after 3 attempts.");
        }

        scanner.close();
    }
}
