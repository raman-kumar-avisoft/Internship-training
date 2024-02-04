import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Possible_Balanced_String {
    static boolean balancedString(ArrayList<Character> al){
        Stack<Character> st = new Stack<>();
        for(int i=0; i<al.size(); i++){
            if(al.get(i) == '('){
                st.push('(');
            }
            else{
                if(st.isEmpty()){
                    return false;
                }else{
                    st.pop();
                }
            }
        }
        return true;
    }
    static void solve(){
//        base case

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the arraylist: ");
        int len = sc.nextInt();
        ArrayList<Character> al = new ArrayList<>(len);

        for(int i = 0; i<len; i++){
            al.add(sc.next().charAt(0));
        }

        if(balancedString(al)){
            System.out.println("Balanced String");
        }else{
            System.out.println("Not Balanced String");
        }
    }
}