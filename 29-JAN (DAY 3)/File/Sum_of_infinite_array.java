import java.util.ArrayList;
import java.util.Scanner;

public class Sum_of_infinite_array {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of array: ");
        int sz = sc.nextInt();

        ArrayList<Integer> ar = new ArrayList<>();
        System.out.println("Enter the elements in an array");
        for (int i=0; i<sz; i++){
            ar.add(sc.nextInt());
        }

        System.out.println("enter the left and right bounds to sum for infinite array");
        int l = sc.nextInt();
        int r = sc.nextInt();

        int sum = 0;
        while(l<=r){
            sum += ar.get((l-1)%sz);
            l++;
        }
        System.out.println(sum);
    }
}