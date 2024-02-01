import java.util.Scanner;

public class Remove_consecutive_duplicates {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first String: ");
        String str1 = sc.nextLine();

        for (int i=0; i<str1.length(); i++){
            int j=i+1;
            while(j<str1.length() && (str1.charAt(i) == str1.charAt(j))){
                if(j==(i+1)){
                    System.out.println(str1.charAt(i));
                }
                j++;
            }
            if(j==(i+1)){
                System.out.println(str1.charAt(i));
            }
            i = (j-1);
        }
    }
}