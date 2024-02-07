import java.util.Scanner;
import java.util.Stack;

public class Remove_all_adjacent_duplicates_using_recursion {
    static String solve2(String str, Stack<Character> st){
        while(!(st.isEmpty()) && st.peek() == str.charAt(str.length()-1)){
            st.pop();
        }

        String value = "";
        System.out.println(str);
//        System.out.println(str.length() == 1);
        if(str.length() == 1){
            return "";
        }else{
            return str.substring(0, str.length()-1);
        }
//        System.out.println(value);
    }
    static String solve(String str, Stack<Character> st, int index){
//        base case
        if(index == str.length()){
            return "";
        }

//        push char to stack
        st.push(str.charAt(index));
        String ans = solve(str, st, index+1);

        if(!(st.isEmpty()) && ans.charAt(ans.length()-1) == st.peek()){
            return solve2(ans, st);
        }else{
            ans += st.pop();
            return ans;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the String: ");

        String str = sc.nextLine();

        System.out.println("Before removing adjacent duplicates String is: "+str);
        Stack<Character> st = new Stack<Character>();
        String ans = solve(str, st,0);
        System.out.println("After removing adjacent duplicates String is: "+ans);

    }
}