import java.util.Scanner;
import java.lang.String;

public class power_function_implementation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int num = sc.nextInt();

        System.out.print("Enter the power you want to calculate of the number: ");
        int power = sc.nextInt();

        double ans = Math.pow(num, power);
//        int ans2 = (int)ans;
//        int ans3 = (int)Math.round(ans); // as Math.round() returns value in integer.
        System.out.println(num+" with power of "+power+" is: "+ ans);
    }
}