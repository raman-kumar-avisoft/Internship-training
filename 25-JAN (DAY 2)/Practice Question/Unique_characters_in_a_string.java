import java.util.Scanner;
import java.lang.String;

public class Unique_characters_in_a_string {
    public static void main(String[] args) {

        System.out.print("Enter the string: ");
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        int len = str.length();
        char arr[] = new char[26];
//        initialize the array
        for(int i=0; i<26; i++){
            arr[i] = 0;
        }
        String ans = "";
        for(int i=0; i<len; i++){
            int value = str.charAt(i);
            if((value-97)>25 || (value-97)<0){
                continue;
            }
            arr[value-97] += 1;
        }
        for(int i=0; i<26; i++){
            if(arr[i]==1){
                char ch = (char)('a'+i);
                ans += ch;
            }
        }
        System.out.println("THE ANSWER IS: "+ans);
    }
}
