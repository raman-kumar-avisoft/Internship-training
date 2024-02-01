import java.util.Scanner;

public class Count_number_of_words {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string");
        String str = new String(sc.nextLine());

        char[] ch = new char[str.length()];
        ch = str.toCharArray();
        int count = 0;
        for (int i=0; i<str.length(); i++){
            if(i==0){
                count++;
            }else if(ch[i] == ' '){
                count++;
            }
        }
        System.out.println("The number of words in string is: " +count);
    }
}