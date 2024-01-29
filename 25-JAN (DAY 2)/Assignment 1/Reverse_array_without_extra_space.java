import java.util.Scanner;
import java.lang.String;

public class Reverse_array_without_extra_space {

    static void displ(int[] arr, int n){
        for(int i=0; i<n; i++){
            System.out.print(arr[i]+" ");
        }
    }
    public static void main(String[] args) {
        int n =5;
        int[] arr = new int[5];
        for(int i =0; i<5; i++){
            arr[i] = i+1;
        }
        displ(arr,n);
        for(int i=0; i<n/2; i++){
            int temp = arr[i];
            arr[i] = arr[n-i-1];
            arr[n-i-1] = temp;
        }
        System.out.println("After reversing the array: ");
        displ(arr, n);
        System.out.println();
    }
}