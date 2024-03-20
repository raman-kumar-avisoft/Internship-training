import java.util.HashSet;
import java.util.Scanner;

public class DistinctSubArray {
    public static void main(String[] args) {
        Scanner scannerString = new Scanner(System.in);
        System.out.print("Enter the String: ");
        String str = scannerString.next();

        int answer = 0;
        HashSet<String> hs = new HashSet<>();
        hs.add("");
        for(int i=0; i<str.length(); i++){
            String str2 = "";
            for(int j=i; j<str.length(); j++){
                str2 += str.charAt(j);
                hs.add(str2);
            }
        }

        for(String distinctSubArrays : hs){
            System.out.println(distinctSubArrays);
        }

        System.out.println("ANSWER IS : "+ hs.size());
    }
}