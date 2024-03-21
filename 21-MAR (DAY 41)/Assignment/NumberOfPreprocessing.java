import java.util.Scanner;

public class NumberOfPreprocessing {
    public static void main(String[] args) {
        Scanner scannerString = new Scanner(System.in);

        System.out.print("Enter the String1: ");
        String str1 = scannerString.next();

        System.out.println("Enter the String2: ");
        String str2 = scannerString.next();

        int answer = minimumOperations(str1, str2);
        System.out.println("ANSWER IS: " + answer);
    }
    public static int minimumOperations(String a, String b) {
        // Write your code here.

        if(a.length() != b.length()){
            return -1;
        }

        // main logic
        int n = a.length();
        int count = 0;
        if(a.length()%2 == 1){
            // odd number of elements
            if(a.charAt(a.length()/2) != b.charAt(a.length()/2)){
                count++;
            }
        }
        char c1,c2,c3,c4;
        for(int i=0; i<a.length()/2; i++){
            c1 = a.charAt(i);
            c2= a.charAt(n-i-1);
            c3 = b.charAt(i);
            c4 = b.charAt(n-i-1);

            if((c1 == c3 && c2 == c4) || (c1 == c4 && c2 == c3) || (c1==c2 && c3 == c4)){
                continue;
            }
            else if(c1 == c3 || c1 == c4 || c2 == c3 || c2 == c4 || c3 == c4){
                count++;
            }
            else{
                count+=2;
            }
        }
        return count;
    }
}