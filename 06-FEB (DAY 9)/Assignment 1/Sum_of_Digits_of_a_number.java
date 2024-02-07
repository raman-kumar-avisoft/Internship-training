import java.util.Scanner;

public class Sum_of_Digits_of_a_number {
    static int solve(int number){
//        base case
        if(number == 0){
            return 0;
        }

        int ans = number%10 + solve(number/10);
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number: ");
        int num = sc.nextInt();

        int ans = solve(num);
        System.out.println("Answer is: "+ans);
    }
}