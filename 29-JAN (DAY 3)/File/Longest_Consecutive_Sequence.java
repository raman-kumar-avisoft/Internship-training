import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Longest_Consecutive_Sequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the arraylist");
        int sz = sc.nextInt();

        ArrayList<Integer> al = new ArrayList<>();
        System.out.println("Insert the elements in the arraylist");
        for (int i=0; i<sz; i++){
            al.add(sc.nextInt());
        }
        int mini = Integer.MAX_VALUE;
        TreeMap<Integer, Integer> hs= new TreeMap<>();
//        TREE MAP KEEPS THE KEYS IN SORTED ORDER.
        for (int i=0; i<sz; i++){
            hs.put(al.get(i), 1);
            mini = Math.min(mini, al.get(i));
        }
        int prev;
        int next = mini;
        int ans = 1;
        int count = 1;
        int ff = 0;
        for (var i: hs.entrySet()) {
            prev = next;
            next = i.getKey();
            if(next-prev == 1){
                count++;
                ans = Math.max(ans, count);
            }else{
                count = 1;
            }
        }
        System.out.println("longest consecutive sequence is: " + (ans));
    }
}