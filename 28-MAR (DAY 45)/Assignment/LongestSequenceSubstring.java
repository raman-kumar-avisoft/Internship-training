import java.util.*;

public class LongestSequenceSubstring {

    public static int longestCommonSubstring(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        int maxLength = 0;

        // Fill the dp table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first string:");
        String str1 = scanner.nextLine(); // Input for the first string
        System.out.println("Enter the second string:");
        String str2 = scanner.nextLine(); // Input for the second string

        int length = longestCommonSubstring(str1, str2);
        System.out.println("Length of the longest common substring: " + length);

        scanner.close();
    }
}
