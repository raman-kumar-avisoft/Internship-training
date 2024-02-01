import java.util.Scanner;

public class Print_all_substring_of_a_string {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string:  ");
        String str = sc.nextLine();

        for(int i=0; i<str.length(); i++){
            int j=str.length();
//            System.out.println(str.length());

            while(j>i){
                String str2 = str.substring(i,j);
//                System.out.println(str);
                System.out.print(str2 + " ");
                j--;
            }
//            System.out.println(i);
        }
    }
}