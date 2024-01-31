import java.util.Scanner;

public class Print_the_prime_numbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number upto which you want to see prime number starting from 1");
        int num = sc.nextInt();

        for(int i=2; i<=num; i++){
            boolean prime = true;
            int j=2;
            while(j<i){
                if(i%j==0){
                    prime = false;
                }
                j++;
            }
            if(prime){
                System.out.print(i+" ");
            }
        }
    }
}