import java.util.Scanner;

public class Concat_all_String_of_an_array_with_some_cond {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array of Strings");
        int len = sc.nextInt();

        String[] arr = new String[len];
        sc.nextLine();
        System.out.println("Enter the strings");
        for(int i=0; i<len; i++){
            arr[i] = sc.nextLine();
//            sc.nextLine();
        }

        String ans = "";
        for(int i=0; i<len; i++){
            boolean first = false;
            int space = 0;
            String ans2 = ans;
            if(i != 0 && ans2 != ans){
                ans += " ";
            }
            for(int j=0; j<arr[i].length(); j++){
                if(arr[i].charAt(j) >= 'A' &&  arr[i].charAt(j) <= 'Z'){
                    if(first == false){
                        ans += arr[i].charAt(j);
                        first = true;
                    }
                    else{
                        ans += (arr[i].charAt(j) + 32);
                    }
                    space = 0;
                }else if(arr[i].charAt(j) >= 'a' &&  arr[i].charAt(j) <= 'z'){
                    if(first == false){
                        char ch = (char)(arr[i].charAt(j) - 32);
                        ans += ch;
                        first = true;
                    }else{
                        ans += arr[i].charAt(j);
                    }
                    space = 0;
                }else if(arr[i].charAt(j) == ' '){
                    if(space == 0){
                        ans += " ";
                    }
                    space++;
                }else if(arr[i].charAt(j) >= '0' &&  arr[i].charAt(j) <= '9'){
                    continue;
                }else{
                    ans += arr[i].charAt(j);
                }
            }
        }

        System.out.println("Answer is: "+ans);
    }
}