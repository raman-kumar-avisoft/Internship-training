import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Kth_Largest_Element {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter size of first array: ");
            int sz1 = sc.nextInt();
            ArrayList<Integer> al1 = new ArrayList<>();
            System.out.println("Enter the element of first arraylist digit wise");
            for(int i=0; i<sz1; i++){
                al1.add(sc.nextInt());
            }

            Collections.sort(al1);

            System.out.println("Enter Which largest number you want to see followed by greater element: ");
            int kth = sc.nextInt();


            System.out.println("Here's the Answer");
            for(int i = sz1-kth; i<sz1; i++){
                System.out.println(al1.get(i));
            }
    }
}