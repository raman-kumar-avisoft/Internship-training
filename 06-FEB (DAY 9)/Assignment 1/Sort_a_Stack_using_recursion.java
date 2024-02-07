import java.util.Scanner;
import java.util.Stack;

public class Sort_a_Stack_using_recursion {
    static void solve2 (Stack<Integer> st, int elem){
        if(st.isEmpty() || st.peek()<=elem){
            st.push(elem);
            return;
        }
        int elem2 = st.pop();
        solve2(st, elem);
        st.push(elem2);
    }
    static void solve (Stack<Integer> st){
//        base condition
        if(st.isEmpty()){
            return;
        }

        int elem = st.pop();
        solve(st);
        solve2(st,elem);
//        st.push(elem);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the length of the stack: ");
        int len = sc.nextInt();

        Stack<Integer> st = new Stack<>();
        System.out.println("Enter the elements in the stack: ");
        for(int i=0; i<len; i++){
            st.push(sc.nextInt());
        }

        solve(st);

        System.out.println("AFTER SORTING ELEMENTS ARE: ");
        for(int i=0; i<len; i++){
            System.out.println(st.pop());
        }
    }
}