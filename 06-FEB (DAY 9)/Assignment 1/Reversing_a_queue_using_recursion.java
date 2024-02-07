import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Reversing_a_queue_using_recursion {
    static Queue<Integer> solve(Queue<Integer> q){
        if(q.isEmpty()){
            return q;
        }

        int elm = q.remove();
        q = solve(q);
        q.add(elm);
        return q;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the queue: ");
        int len = sc.nextInt();

        Queue<Integer> q = new LinkedList<>();
        System.out.println("Enter the element in the queue");
        for(int i=0; i<len; i++){
            q.add(sc.nextInt());
        }

        System.out.println("Before reversing the queue");
        for(int i: q){
            System.out.print(i+ " ");
        }
        q = solve(q);
        System.out.println("\nAfter reversing the queue");
        for(int i: q){
            System.out.print(i+ " ");
        }
    }
}