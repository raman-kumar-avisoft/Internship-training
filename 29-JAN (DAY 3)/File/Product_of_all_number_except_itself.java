import java.util.ArrayList;
import java.util.Scanner;

public class Product_of_all_number_except_itself {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size: ");
        int len = sc.nextInt();

        ArrayList<Integer> ar = new ArrayList<>();
        System.out.println("Enter the array values: ");
        for(int i=0; i<len; i++){
            ar.add(sc.nextInt());
        }
        int product = 1;
        for(int i=0; i<len; i++){
            product *= ar.get(i);
        }
        for(int i=0; i<len; i++){
            ar.set(i, product/ar.get(i));
        }

        for (int i=0; i<len; i++){
            System.out.println(ar.get(i));
        }
    }
}