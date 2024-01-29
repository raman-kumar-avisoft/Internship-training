import java.util.Scanner;
import java.lang.String;

public class Reverse_of_a_number {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int num = sc.nextInt();

        String ans2 = "";
        while(num>0){
            int value = num%10;
            ans2 += value;
            num /= 10;
        }
        System.out.println(ans2);
        int num2 = Integer.valueOf(ans2);
        System.out.println(num2);
    }
}