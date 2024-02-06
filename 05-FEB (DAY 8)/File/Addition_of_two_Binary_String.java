import java.util.Scanner;

public class Addition_of_two_Binary_String {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the First binary Strings");
        StringBuilder str = new StringBuilder(sc.next());

        System.out.println("Enter the Second binary String");
        StringBuilder str2 = new StringBuilder(sc.next());

        int len = (str.length() < str2.length()) ? str.length() : str2.length();
        str.reverse();
        str2.reverse();

        int i=0;
        int c = 0;
        int f,s;
        StringBuilder ans = new StringBuilder("");
        while(i<len){
            if(str.charAt(i) == '1'){
                f = 1;
            }else{
                f = 0;
            }
            if(str2.charAt(i) == '0'){
                s = 0;
            }else{
                s = 1;
            }

            if(f+s+c == 0){
                ans.append('0');
                c = 0;
            }else if(f+s+c == 1){
                ans.append('1');
                c = 0;
            }else if(f+s+c == 2){
                ans.append('0');
                c = 1;
            }else{
                ans.append('1');
                c = 1;
            }
            i++;
        }
        while(i<str.length()){
            f = str.charAt(i);
            s = 0;
            if(f+s+c == 0){
                ans.append('0');
                c = 0;
            }else if(f+s+c == 1){
                ans.append('1');
                c = 0;
            }else if(f+s+c == 2){
                ans.append('0');
                c = 1;
            }else{
                ans.append('1');
                c = 1;
            }
            i++;
        }
        while(i<str2.length()){
            f = 0;
            s = str2.charAt(i);
            if(f+s+c == 0){
                ans.append('0');
                c = 0;
            }else if(f+s+c == 1){
                ans.append('1');
                c = 0;
            }else if(f+s+c == 2){
                ans.append('0');
                c = 1;
            }else{
                ans.append('1');
                c = 1;
            }
            i++;
        }
        if(c != 0){
            ans.append('1');
        }
        System.out.println("Answer is: "+ans.reverse());
    }
}