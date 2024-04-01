import java.util.Arrays;
import java.util.Scanner;

public class LongestSubSequence {

    public static int longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        // dp array to store the length of the longest increasing subsequence ending at index i
        int[] dp = new int[n];
        // Initialize dp array with 1, as each element itself is a subsequence of length 1
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    // If nums[i] is greater than nums[j], we can extend the subsequence ending at nums[j]
                    // by including nums[i]
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // Find the maximum value in dp array, which represents the length of the longest increasing subsequence
        int maxLength = 0;
        for (int length : dp) {
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n;

        // Ask the user for the size of the array with exception handling
        while (true) {
            try {
                System.out.print("Enter the size of the array: ");
                n = scanner.nextInt();
                if (n <= 0) {
                    throw new IllegalArgumentException("Size of the array must be a positive integer.");
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid positive integer for the size of the array.");
                scanner.nextLine(); // Clear the input buffer
            }
        }

        int[] nums = new int[n];

        // Ask the user for the elements of the array with exception handling
        for (int i = 0; i < n; i++) {
            while (true) {
                try {
                    System.out.print("Enter element " + (i + 1) + ": ");
                    nums[i] = scanner.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.nextLine(); // Clear the input buffer
                }
            }
        }

        System.out.println(longestIncreasingSubsequence(nums));
    }
}
