import java.util.Scanner;

public class seprate_the_adjacent_repeated_characters_by_adding_1 {
    static String solve(String str, int index){
//        base case
        if(index == str.length()){
            return "";
        }

        String ans = solve(str, index+1);
        if(ans.equals("")){
            ans += str.charAt(index);
        }else if(ans.charAt(ans.length()-1) == str.charAt(index)){
            ans += "*"+str.charAt(index);
        }else{
            ans += str.charAt(index);
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the String: ");
        String str = sc.nextLine();

        String ans = solve(str, 0);
        StringBuilder str2 = new StringBuilder(ans);
        System.out.println("Answer is: " + str2.reverse());
    }
}