import java.util.Scanner;

public class Reverse_the_array {
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

        for(int i=0; i<len/2; i++){
            int temp = arr[i];
            arr[i] = arr[len-i-1];
            arr[len-i-1] = temp;
        }

        System.out.println("After reversing array will be: ");
        for (int i=0; i<len; i++){
            System.out.print(arr[i] + " ");
        }
    }
}