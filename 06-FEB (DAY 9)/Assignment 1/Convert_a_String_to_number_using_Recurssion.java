import java.util.Scanner;

public class Convert_a_String_to_number_using_Recurssion {
    static int solve(String str, int index){
//        base case
        if(index == str.length()){
            return 0;
        }


        return solve(str, index+1) + (str.charAt(index)-'0') * (int)Math.pow(10, str.length()-index-1);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number");
        String str = sc.next();

        int ans = solve(str, 0);
        System.out.println("Answer is: "+ ans);
    }
}