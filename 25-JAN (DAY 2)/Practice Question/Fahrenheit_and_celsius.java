import java.util.Scanner;
import java.lang.String;

public class Fahrenheit_and_celsius {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the starting fahrenheit temperature: ");
        int S = sc.nextInt();

        System.out.print("Enter the ending fahrenheit temperature: ");
        int E = sc.nextInt();

        System.out.print("Enter the gap you want to see after starting fahrenheit till ending fahrenheit temperature: ");
        int W = sc.nextInt();

        while(S<=E){
            int value = ((S -32) * 5)/9;

            System.out.println("For fehrenheit value: "+S+" its value in celsius is: "+value);
            S += W;
        }
    }
}