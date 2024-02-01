import java.util.Scanner;

public class String_Palindrome {
    static boolean check_palindrome(char[] arr){
        int j = arr.length - 1;
        int i = 0;

        while(i<j && i<arr.length-1 && j>0){

            while((i<arr.length-1 && j>0) && !((arr[i] >= 'a' && arr[i] <= 'z') || (arr[i] >= 'A' && arr[i] <= 'Z') || (arr[i] >= '0' && arr[i] <= '9'))){
                System.out.println(arr[i]);
                i++;
            }
            while((i<arr.length-1 && j>0) && !((arr[j] >= 'a' && arr[j] <= 'z') || (arr[j] >= 'A' && arr[j] <= 'Z') || (arr[j] >= '0' && arr[j] <= '9'))){
                j--;
            }
            if(i<arr.length-1 && j>0 && i<j && arr[i] != arr[j]){
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the String: ");
        String str = sc.nextLine();
        char[] ch = new char[str.length()];
        ch = str.toCharArray();

        System.out.println("The String is Palindrome? "+check_palindrome(ch));
    }
}