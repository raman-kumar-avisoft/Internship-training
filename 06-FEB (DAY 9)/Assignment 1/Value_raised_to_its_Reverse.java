import java.util.Scanner;

public class Value_raised_to_its_Reverse {
    static long solve(int num, int rev){
        if(rev == 0){
            return 1;
        }
        long mod = 1000000007;
        return (num * solve(num, rev-1) % (int)mod);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number: ");
        int num = sc.nextInt();
        int rev = 0;
        int num2 = num, count = 0;
        while(num2 > 0){
            count++;
            num2 /= 10;
        }
        int num3 = num;
        while (num3>0){
            rev += (num3%10) * (int)Math.pow(10, --count);
            num3/=10;
        }

        System.out.println("number is : "+ num);
        System.out.println("Its reverse is: "+rev);

        System.out.println("Number raised to its reverse is: " + solve(num, rev)%1000000007);
    }
}
