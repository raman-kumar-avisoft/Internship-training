import java.util.Scanner;

public class Factorial_of_a_number {
//    recursive way
    static int ff(int num){
        if(num==0 || num==1){
            return 1;
        }
        return num * ff(num-1);
    }
    public static void main(String[] args) {
//        iterative way
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int num = sc.nextInt();

        int fact = 1;
        for(int i=1; i<=num; i++){
            fact *= i;
        }
//        System.out.println("Factorial of "+num+" is: "+fact);
        System.out.println("Factorial of "+num+" is: "+ff(num));

    }
}