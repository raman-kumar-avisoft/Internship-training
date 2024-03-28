import java.util.*;

public class Robber_Cannot_Rob_Consecutive_House {

    public static int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(robLinear(Arrays.copyOfRange(nums, 0, n - 1)), robLinear(Arrays.copyOfRange(nums, 1, n)));
    }

    public static int robLinear(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[n - 1];
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
        System.out.println(rob(arr)); // Print the maximum amount of money Mr. X can rob without alerting the police
        scanner.close();
    }
}
