import java.util.Arrays;
import java.util.Scanner;

public class Number_of_occurrene {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        int len = sc.nextInt();

        int[] arr = new int[len];
        System.out.println("Enter the elements in the array: ");
        for (int i=0; i<len; i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);
        System.out.println("Enter the number whose occurrence you want to know");
        int num = sc.nextInt();
        int count = 0;

        for (int i=0; i<len; i++){
            if(arr[i] == num){
                count++;
            }
        }
        System.out.println("Occurrence is: "+ count);
    }
}