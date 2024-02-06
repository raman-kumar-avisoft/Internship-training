import java.util.Scanner;

public class Remove_Consecutive_duplicates {
    static StringBuilder solve(String str, int i){
//        base condition
        if(i >= str.length()){
            return new StringBuilder(" ");
        }

        StringBuilder ans = solve(str, i+1);
        if(ans.equals(" "))
        {
//            System.out.println("hello" + i);
            ans.append(str.charAt(i));
        }else if(ans.charAt(ans.length()-1) != str.charAt(i)){
            ans.append(str.charAt(i));
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string: ");
        String str = sc.nextLine();

        System.out.println(str.length());
        StringBuilder ans = solve(str, 0);
        System.out.println("Answer is: "+ans.reverse());
    }
}