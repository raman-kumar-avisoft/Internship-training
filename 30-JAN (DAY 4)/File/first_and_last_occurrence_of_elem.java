import java.util.Arrays;
import java.util.Scanner;

public class first_and_last_occurrence_of_elem {
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
        int first = -1;
        int last = -1;

        for (int i=0; i<len; i++){
            if(arr[i] == num){
                if(first == -1){
                    first = i;
                    last = i;
                }
                else{
                    last = i;
                }
            }
        }
        System.out.println("First and Last Occurrence is: " + first + " " + last);
    }
}