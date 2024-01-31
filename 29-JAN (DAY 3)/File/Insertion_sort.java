import java.util.Arrays;
import java.util.Scanner;

public class Insertion_sort {
    public static void main(String[] args) {
//        using array
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the length of array");
        int leng = sc.nextInt();

        int[] arr = new int[leng];
        System.out.println("Insert the element in the array");
        for(int i=0; i<leng; i++){
            arr[i] = sc.nextInt();
        }

        for(int i=0; i<leng; i++){
//            int k = i;
            int j = i;
            while(j>0 && arr[j]<arr[j-1]){
                int temp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = temp;
                j--;
            }
        }

//        after sortin answer is:
        for(int i=0; i<leng; i++){
            System.out.println(arr[i]);
        }

    }
}