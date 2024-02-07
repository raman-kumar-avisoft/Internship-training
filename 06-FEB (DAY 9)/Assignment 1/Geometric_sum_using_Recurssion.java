import java.util.Scanner;

public class Geometric_sum_using_Recurssion {
    static double solve(int num, int i){
//        base condtion
        if(num < 0){
            return 0;
        }
        double ans = 1/Math.pow(2,i) + solve(num-1, i+1);
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of term you want to print you answer: ");
        int num = sc.nextInt();

        double sum = solve(num, 0);
        System.out.println("Answer is: " + sum);
    }
}