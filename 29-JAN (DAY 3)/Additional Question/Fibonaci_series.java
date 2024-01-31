import java.util.Scanner;

public class Fibonaci_series {
    public static void main(String[] args) {
//        iterative way
        int first = 0;
        int second = 1;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number upto which you want to print the series");
        int num = sc.nextInt();

        if(num >= 1){
            System.out.print(first+" ");
        }
        int pp = first + second;
        while(pp<num){
            System.out.print(pp+" ");
            first = second;
            second = pp;
            pp = first + second;
        }
    }
}