import java.util.Scanner;
import java.lang.String;

public class Count_vowels_and_consonants {
    public static void main(String[] args) {
        String sent = "hallo nice to meet you@@3";
        int len2 = sent.length();
        int vowel1 = 0;
        int cons1 = 0;
        int other = 0;

        for(int j = 0; j<len2; j++){
            if (sent.charAt(j) >= 'a' && sent.charAt(j) <= 'z') {
                if(sent.charAt(j) ==  'a' || sent.charAt(j) == 'e' || sent.charAt(j) == 'i' || sent.charAt(j) == 'o' || sent.charAt(j) == 'u'){
                    vowel1++;
                }else{
                    cons1++;
                }
            }else{
                other++;
            }
        }
        System.out.println("Vowels are: "+ vowel1 + ", const are: "+cons1 + " and other letters are : "+other);
    }
}