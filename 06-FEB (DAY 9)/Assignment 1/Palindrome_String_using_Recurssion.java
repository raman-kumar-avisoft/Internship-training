import java.util.Scanner;

public class Palindrome_String_using_Recurssion {
    static boolean solve(String str, int index){
//        base condition
        if(index >= str.length()/2){
            return true;
        }
        if(str.charAt(index) != str.charAt(str.length()-index-1)){
            return false;
        }

        return solve(str, index+1);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string: ");
        String str = sc.nextLine();

        boolean ans = solve(str, 0);
        System.out.println("The inputted String is: "+ans);
    }
}