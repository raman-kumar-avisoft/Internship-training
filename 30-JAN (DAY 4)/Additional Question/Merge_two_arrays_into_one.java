import java.util.Scanner;

public class Merge_two_arrays_into_one {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of first array");
        int size1 = sc.nextInt();
        int[] arr = new int[size1];
        System.out.println("Enter the elements: ");
        for (int i=0; i<size1; i++){
            arr[i] = sc.nextInt();
        }

        System.out.println("Enter the size of second array");
        int size2 = sc.nextInt();
        int[] arr2 = new int[size2];
        System.out.println("Enter the elements: ");
        for (int i=0; i<size2; i++){
            arr2[i] = sc.nextInt();
        }

        int[] arr3 = new int[size1+size2];
        int k=0;
        for (int i=0; i<size1; i++){
            arr3[k] = arr[i];
            k++;
        }
        for (int i=0; i<size2; i++){
            arr3[k] = arr2[i];
            k++;
        }

        System.out.println("Combined array is: ");
        for (int i=0; i<size1+size2; i++){
            System.out.print(arr3[i]+" ");
        }
    }
}