import java.util.Scanner;
import java.lang.String;

public class Count_number_of_digits {
    public static void main(String[] args) {
        int digit2 = 1203;
        int num;
        int count = 0;
        num = Math.abs(digit2);
        if(num == 0){
            count++;
        }
        while(num>=1){
            num/=10;
            count++;
        }
        System.out.println(count);
    }
}