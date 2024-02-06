import java.util.Scanner;

public class Number_of_digits {
    static int count(int num){
        if(num == 0){
            return 0;
        }
        return count(num/10) + 1;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number: ");
        int num = sc.nextInt();

        int ans = count(num);
        System.out.println("answer is: "+ans);
    }
}