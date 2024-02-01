import java.util.Scanner;

public class Remove_Character {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first String: ");
        String str1 = sc.nextLine();

        System.out.println("Enter the character you want to remove");
        char ch = sc.nextLine().charAt(0);

        String ans = "";
        for (int i=0; i<str1.length(); i++){
            if(str1.charAt(i) != ch){
                ans += str1.charAt(i);
            }
        }
        System.out.println("Answer is: " + ans);
    }
}