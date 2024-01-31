import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Remove_duplicatees_from_array {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter length of the array: ");
        int len = sc.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();

        System.out.println("Enter the elements in the array");
        for(int i=0; i<len; i++){
            arr.add(sc.nextInt());
        }

        Collections.sort(arr);

        int j=1;
        int prev = arr.get(0);
        for(int i=0; i<arr.size(); i++){
            if(prev != arr.get(i)){
                Collections.swap(arr,i,j);
                prev = arr.get(i);
                j++;
            }
        }
//        modified array
        for(int i=0; i<=len-j; i++){
            System.out.println(arr.get(i));
        }
    }
}