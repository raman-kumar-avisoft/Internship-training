import java.util.Scanner;
import java.lang.String;

public class Sum_of_odd_digits_and_even_digits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int num = sc.nextInt();

        int odd = 0;
        int even =0;

        while(num>0){
            if(num%2 == 0){
                even += num%10;
            }else{
                odd += num%10;
            }
            num /= 10;
        }
        System.out.println("Odd sum is: "+odd+" and even sum is: "+even);
    }
}