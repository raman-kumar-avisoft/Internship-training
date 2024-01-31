import java.util.Scanner;

public class Sum_of_all_the_element_in_an_array{
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

//        displaying
        System.out.println("Elements entered are: ");
        int sum = 0;
        for(int i=0; i<len; i++){
            System.out.print(arr[i]+ " ");
            sum += arr[i];
        }
        System.out.println("\nThe sum of elements of array is: "+sum);
    }
}