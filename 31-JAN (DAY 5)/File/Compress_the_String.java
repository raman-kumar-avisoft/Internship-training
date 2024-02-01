import java.util.Scanner;

public class Compress_the_String {
    static String str_compression(String str){
        String ans = "";
        for(int i=0; i<str.length(); i++){
            int count = 1;
            while(i+1<str.length() && str.charAt(i) == str.charAt(i+1)){
                i++;
                count++;
            }
            ans += str.charAt(i);
            if(count > 1){
                ans += count;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string");
        String str = sc.nextLine();

        String compressed_str = str_compression(str);
        System.out.println("The answer is: "+compressed_str);
    }
}