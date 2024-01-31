import java.util.Scanner;

public class Reverse_the_array_after_specific_position {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int len = sc.nextInt();

        int[] arr = new int[len];
//        initialize the array
        System.out.println("Enter the element in an array");
        for (int i=0; i<len; i++){
            arr[i] = sc.nextInt();
        }

        int pos;
        System.out.print("Enter the index of array after which you want to reverse your array: ");
        pos = sc.nextInt();
        int e = len -1;
        for(int i=pos; i<=(pos+e)/2; i++){
            int temp = arr[i];
            arr[i] = arr[e-i+pos];
            arr[e-i+pos] = temp;
        }

        System.out.println("After reversing array will be: ");
        for (int i=0; i<len; i++){
            System.out.print(arr[i] + " ");
        }
    }
}