import java.util.Scanner;

public class Number_of_Zero_present_in_number_using_Recurssion {
    static int solve(int num){
//        base case
        if(num == 0){
            return 0;
        }
        int digit = num%10;
        int ans = solve(num/10);
        if(digit == 0){
            ans += 1;
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number: ");
        int num = sc.nextInt();
        if(num == 0){
            System.out.println("Answer is: "+ 1);
        }else{
            int ans = solve(num);
            System.out.println("Answer is: "+ans);
        }
    }
}