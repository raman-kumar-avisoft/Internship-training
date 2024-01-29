import java.util.Scanner;
import java.lang.String;

public class toggle_case_in_string {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string");
        String s = sc.nextLine();

        int len = s.length();
        String str = "";
        for(int i=0; i<len; i++) {
            if (s.charAt(i) >= 65 && s.charAt(i) <= 90) {
                char ch2 = (char)(s.charAt(i)+32);
                str += ch2;

            } else if (s.charAt(i) >= 97 && s.charAt(i) <= 122) {
                char ch2 = (char)(s.charAt(i)-32);
                str += ch2;
            }else{
                str += s.charAt(i);
            }
        }
        System.out.println("ANSWER IS: "+ str);
    }
}