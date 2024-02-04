import java.util.Scanner;

public class Distinct_String {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the String");
        String str = sc.nextLine();

        int[] arr = new int[26];
        for(int i=0; i<26; i++){
            arr[i] = 0;
        }
        int count = 0;
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i)>=97 && str.charAt(i) <=122){
//                System.out.println("hehe");
                ++arr[str.charAt(i)-97];
                if(arr[str.charAt(i)-97]>1){
                    count++;
                }
            }else{
                System.out.println("String contains other character than lowercase letters");
            }
        }
        int j = count;
        for(int i=0; i<26; i++){
            if(arr[i] == 0){
                --j;
            }
        }
        if(j<=0){
            System.out.println("Need minimum of changes to make all substring distinct is: "+count);
        }else{
            System.out.println("not possible to make all substring distinct");
        }
    }
}