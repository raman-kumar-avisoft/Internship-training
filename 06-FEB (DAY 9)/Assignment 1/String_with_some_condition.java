import java.util.Scanner;

public class String_with_some_condition {
    static boolean solve(String str, int index){
//        base condition
        if(index == str.length()){
            return true;
        }

        boolean ans = false;
        boolean ans2 = solve(str, index+1);
        if(ans2 == false && str.charAt(index) == 'b' && index != 0){
            ans = true;
        }else if(ans2 == false && str.charAt(index) != 'b'){
            ans = false;
        }else{
            if(str.charAt(index) == 'a'){
                ans = true;
            }else if(str.charAt(index) == 'b'){
                ans = false;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the String: ");
        String str = sc.nextLine();

        boolean ans = solve(str, 0);
        System.out.println("The Condition are meting? "+ans);
    }
}