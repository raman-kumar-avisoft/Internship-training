import java.util.*;

public class MinimumPathSumTriangle {

    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                int n = scanner.nextInt(); // Number of rows in the triangle
                if (n < 1) {
                    throw new IllegalArgumentException("Number of rows must be at least 1.");
                }

                List<List<Integer>> triangle = new ArrayList<>();

                for (int j = 0; j < n; j++) {
                    List<Integer> row = new ArrayList<>();
                    for (int k = 0; k <= j; k++) {
                        row.add(scanner.nextInt());
                    }
                    triangle.add(row);
                }

                System.out.println(minimumTotal(triangle));
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid integers.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
