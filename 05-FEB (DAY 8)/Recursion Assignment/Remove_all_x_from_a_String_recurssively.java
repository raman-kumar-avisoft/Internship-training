import java.util.Scanner;

public class Remove_all_x_from_a_String_recurssively {
    static StringBuilder solve(String str, int index){
        if(index == str.length()){
            return new StringBuilder("");
        }
        StringBuilder ans = solve(str, index+1);
        if(str.charAt(index) != 'x' ){
            ans.append(str.charAt(index));
        }
//        System.out.println(ans);
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the String from which you want to remove all x's: ");
        String str = sc.nextLine();

        StringBuilder ans = solve(str, 0);
        System.out.println("Answer is: " + ans.reverse());
    }
}