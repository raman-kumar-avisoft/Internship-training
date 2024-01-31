import java.util.Arrays;
import java.util.Scanner;

public class Binary_Search {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        int len = sc.nextInt();

        int[] arr = new int[len];
        System.out.println("Insert the element in the array");
        for(int i=0; i<len; i++){
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        System.out.println("Enter the element you want to serch in the array");
        int elm = sc.nextInt();
        int s = 0;
        int e = len-1;
        int index = -1;

        while(s<=e){
            int mid = (e-s)/2 + s;
            if(elm == arr[mid]){
                System.out.println("Search success");
                index = mid;
                break;
            }else if(elm > arr[mid]){
                s = mid+1;
            }else{
                e = mid-1;
            }
        }
        if(index == -1){
            System.out.println("not successful");
        }
    }
}