import java.util.ArrayList;
import java.util.Scanner;

public class Find_duplicates_in_array {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array");
        int len = sc.nextInt();

        System.out.println("Enter the elements in the array");
        int[] arr = new int[len];
        for(int i=0; i<len; i++){
            arr[i] = sc.nextInt();
        }

        for(int i=0; i<len; i++){
            arr[arr[i]%len] += len;
        }
        System.out.println("Duplicate Elements are: ");
        ArrayList<Integer> al = new ArrayList<>();
        for (int i=0; i<len; i++){
            if(arr[i]>=2*len){
                al.add(arr[i]%len);
            }
        }
        System.out.println(al);
    }
}