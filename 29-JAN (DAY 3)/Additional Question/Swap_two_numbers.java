import java.util.Scanner;

public class Swap_two_numbers {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first number");
        int first = sc.nextInt();

        System.out.println("Enter the second number");
        int second = sc.nextInt();

        first += second;
        second = first - second;
        first = first - second;

        System.out.println("First No is: "+first);
        System.out.println("Second No is: "+second);
    }
}