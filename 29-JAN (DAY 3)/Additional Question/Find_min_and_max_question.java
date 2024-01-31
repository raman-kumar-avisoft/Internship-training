import java.util.Scanner;

public class Find_min_and_max_question {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of array: ");
        int row = sc.nextInt();

        System.out.println("Enter the elements of the array");
        int[] arr = new int[row];
        for(int i=0; i<row; i++){
            arr[i] = sc.nextInt();
        }
        int mini = Integer.MAX_VALUE;
        int maxi = Integer.MIN_VALUE;
        for (int i=0; i<row; i++){
            mini = Integer.min(mini, arr[i]);
            maxi =Integer.max(maxi,arr[i]);
        }
        System.out.println("Minimum value is: "+mini+" and Maximum is: "+maxi);
    }
}