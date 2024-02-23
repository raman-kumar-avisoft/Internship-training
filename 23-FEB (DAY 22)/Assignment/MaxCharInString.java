import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MaxCharInString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = 3;
        String str = "";
        while(count>0){
            try{
                System.out.print("Enter the String: ");
                str = sc.nextLine();
                count = -1;
            }catch (InputMismatchException e){
                System.out.println("Enter a valid input value !!");
            }finally {
                count--;
                if(count == 0){
                    System.out.println("Too many wrong tries !!");
                    System.exit(0);
                }
            }
        }
        char maxChar = findMaxChar(str);
        System.out.println("Maximum repeating character in a string is: " + maxChar);
    }
    static char findMaxChar(String str){
        int maxOcc = 0;
        char ch = '\0';
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        for(int i=0; i<str.length(); i++){
            if(hm.containsKey(str.charAt(i))){
                hm.put(str.charAt(i), hm.get(str.charAt(i))+1);
            }else{
                hm.put(str.charAt(i), 1);
            }

            if(maxOcc < hm.get(str.charAt(i))){
                maxOcc = hm.get(str.charAt(i));
                ch = str.charAt(i);
            }
        }
        return ch;
    }
}