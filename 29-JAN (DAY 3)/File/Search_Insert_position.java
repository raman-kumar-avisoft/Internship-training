import java.util.Arrays;
import java.util.Scanner;

public class Search_Insert_position {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        System.out.println("enter the length of array");
        int leng = sc.nextInt();

        int[] arr = new int[leng];
        System.out.println("Insert the element in the array");
        for(int i=0; i<leng; i++){
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        System.out.println("enter the target element");
        int target = sc.nextInt();
        int index = 0;
        for(int i=0; i<leng; i++){
            if(arr[i]>target){
                index = i;
                break;
            }
        }
        System.out.println("Target value is: "+ index);

    }
}