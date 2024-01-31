import java.util.Scanner;

public class Check_armstrong_number {
    public static void main(String[] args) {
//        ARMSTRONG NUMBER: WHEN SUM OF CUBES OF ALL THE DIGIT IS EQUAL TO THE NUMBER

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number: ");
        int num = sc.nextInt();

        int num2 = num;
        int sum=0;
        while(num>0){
            sum += Math.pow(num%10,3);
            num /=10;
        }
        if(sum == num2){
            System.out.println("It's an ARMSTRONG NUMBER");
        }else{
            System.out.println("IT's NOT an ARMSTRONG NUMBER");
        }
    }
}