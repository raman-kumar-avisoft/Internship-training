import java.util.Scanner;
import java.util.Stack;

public class Reverse_the_Stack {
    static void solve(Stack<Integer> st){
        if(st.isEmpty()){
            return;
        }
        int elem = st.pop();
        solve(st);
        while(!st.isEmpty()){
            int elem2 = st.pop();
        }
        st.push(elem);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of elements in stack: ");
        int len = sc.nextInt();

        Stack<Integer> st = new Stack<>();
        int i=0;
        while (i<len){
            System.out.println("Enter the element: ");
            st.push(sc.nextInt());
            i++;
        }

//        displaying elements before reversing
//        System.out.println("After reversing elements in stack are: ");
//        for(int j=0; i<len; i++){
//            System.out.println(st.pop());
//        }

        solve(st);

//        displaying elements
        System.out.println("After reversing elements in stack are: ");
        for(int j=0; j<len; j++){
            System.out.println(st.pop());
        }
    }
}