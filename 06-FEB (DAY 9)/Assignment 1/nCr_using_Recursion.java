import java.util.Scanner;

public class nCr_using_Recursion {
    static int solve(int num){
        if(num == 0){
            return 1;
        }
        return num * solve(num-1);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the value of n: ");
        int n = sc.nextInt();
        System.out.println("Enter the value of r: ");
        int r = sc.nextInt();

        int ans = solve(n)/(solve(n-r)*solve(r));
        System.out.println("Answer is: " + ans);
    }
}