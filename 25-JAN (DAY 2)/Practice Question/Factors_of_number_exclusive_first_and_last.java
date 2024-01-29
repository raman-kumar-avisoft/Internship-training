import java.util.Scanner;
import java.lang.String;

public class Factors_of_number_exclusive_first_and_last {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int num = sc.nextInt();

        System.out.println("Factors of the number");
        for(int i=2; i<=Math.sqrt(num); i++){
            if(num%i == 0){
                if(i == (num/i)){
                    System.out.println(i);
                }else{
                    System.out.println(i);
                    System.out.println(num/i);
                }
            }
        }

//        NOT THE OPTIMAL SOLUTION
//        String ans = "";
//        for(int i=2; i<=num/2; i++){
//            if(num%i==0){
//                ans+= i;
//                ans+= " ";
//            }
//        }
//        if (ans.equals("")) {
//            System.out.println("NO FACTOR OTHER THAN THE NUMBER ITSELF");
//        }else{
//            System.out.println("FACTORS ARE: "+ans);
//        }
    }
}