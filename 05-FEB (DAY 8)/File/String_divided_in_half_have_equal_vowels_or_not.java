import java.util.Scanner;

public class String_divided_in_half_have_equal_vowels_or_not {
    static boolean equal_vowel(String str){
        int vowel1 = 0, vowel2 = 0;
        for(int i=0; i<str.length(); i++){

            if(str.charAt(i) == 'a' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'o' || str.charAt(i) == 'u' || str.charAt(i) == 'A' || str.charAt(i) == 'E' || str.charAt(i) == 'I' || str.charAt(i) == 'O' || str.charAt(i) == 'U'){
                if(i>=str.length()/2){
                    vowel2++;
                }else{
                    vowel1++;
                }
            }
        }
        if(vowel1 == vowel2){
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the string of even length: ");
        String str = sc.nextLine();

        if(str.length()%2 != 0){
            System.out.println("Length Entered is not even");
        }else{
            if(equal_vowel(str)){
                System.out.println("TRUE");
            }else{
                System.out.println("FALSE");
            }
        }
    }
}