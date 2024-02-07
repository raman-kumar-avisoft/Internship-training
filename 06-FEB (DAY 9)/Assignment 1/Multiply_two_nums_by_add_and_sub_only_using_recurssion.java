import java.util.Scanner;

public class Multiply_two_nums_by_add_and_sub_only_using_recurssion {
    static int solve(int number, int count){
        if(count == 0){
            return 0;
        }
        return solve(number, count-1) + number;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the two numbers whom you want to multiply: ");
        int first = sc.nextInt();
        int second = sc.nextInt();

        int ans = solve(first , second);
        System.out.println("Answer is: " + ans);
    }
}