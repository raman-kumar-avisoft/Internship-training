import java.util.Scanner;

public class Palindrome_or_not {
    public static void main(String[] args) {
        System.out.println("Enter the number: ");
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();
        int copy_num = num;
        int palindrome_num = 0;
        int pp = 1;

        while(copy_num > 0){
            palindrome_num *= pp;
            palindrome_num += copy_num%10;
            copy_num /= 10;
            pp = 10;
        }

        if(num == palindrome_num){
            System.out.println("It's a palindrome number");
        }else{
            System.out.println("It's not palindrome number");
        }
    }
}