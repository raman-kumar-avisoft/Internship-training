import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LongestSubStringWithAtmostKDistinctChar {
    public static void main(String[] args) {
        Scanner scannerString = new Scanner(System.in);
        Scanner scannerInteger = new Scanner(System.in);

        System.out.print("Enter the String: ");
        String str = scannerString.next();

        int maxWrongTries = 3;
        int K = -1;
        while(maxWrongTries-- > 0){
            try{
                System.out.print("Enter the Distinct Characters in String: ");
                K = scannerInteger.nextInt();
                if(K <= 0){
                    throw new InputMismatchException();
                }
                break;
            } catch(InputMismatchException inputMismatchException){
                scannerInteger.nextInt();
            }
        }
        if(maxWrongTries == 0){
            System.out.println("TOO MANY WRONG TRIES **");
        }

        int answer = longestSubString(str, K);
        System.out.println("ANSWER IS: " + answer);
    }
    static int longestSubString(String str, int K){
        int ans = -1;
        String subString = "";
        HashMap<Character, Integer> hm = new HashMap<>();
        for(int strIndex = 0; strIndex < str.length(); strIndex++){
            if(K < 0){
                while(K < 0){
                    char first = subString.substring(1).charAt(0);
                    subString = subString.substring(1);
                    hm.put(first, hm.get(first)-1);
                    if(hm.get(first) == 0){
                        hm.remove(first);
                    }
                    K = hm.size();
                }
                ans = Integer.max(ans, subString.length());
            }else{
                if(hm.containsKey(str.charAt(strIndex))){
                    hm.put(str.charAt(strIndex), hm.get(str.charAt(strIndex))+1);
                }else{
                    hm.put(str.charAt(strIndex), 1);
                }
                subString += str.charAt(strIndex);
                K--;
                ans = Integer.max(ans, subString.length());
            }
        }
        return ans;
    }
}