import java.util.Arrays;
import java.util.Scanner;

public class Find_duplicate_values_in_array {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        int len = sc.nextInt();

        int[] arr = new int[len];
        System.out.println("Enter the elements in the array: ");
        for(int i=0; i<len; i++){
            arr[i] = sc.nextInt();
        }

        String duplicate_value = "";
        Arrays.sort(arr);
        for(int i=0; i<len; i++){
            int j=1;
            while((i+j)<len && arr[i] == arr[i+j]){
                if(j==1){
                    duplicate_value += (arr[i]) + " ";
                }
                j++;3
                        q
            }
            i+=(j-1);
        }
        System.out.println(duplicate_value);
    }
}