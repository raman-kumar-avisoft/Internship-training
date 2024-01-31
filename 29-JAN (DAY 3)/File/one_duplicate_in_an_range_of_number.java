import java.util.ArrayList;
import java.util.Scanner;

public class one_duplicate_in_an_range_of_number {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter length of the array: ");
        int len = 5;
        ArrayList<Integer> arr = new ArrayList<>();

        System.out.println("Enter the elements in the array");
        for(int i=0; i<len; i++){
            arr.add(sc.nextInt());
        }

        int missing, duplicate;
        for(int i=0; i<len; i++){
            arr.set(i, arr.get(i)-1);
        }
//        for(int i=0; i<len; i++){
//            System.out.println(arr.get(i));
//        }
        for(int i=0; i<len; i++){
            arr.set(arr.get(i)%len, arr.get(i)+len);
        }
        for(int i=0; i<len; i++){
            System.out.println(arr.get(i));
        }
    }
}