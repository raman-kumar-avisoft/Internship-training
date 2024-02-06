import java.util.Scanner;

public class Sum_of_Array_Recussively {
    static int solve(int[] arr, int index){
        if(index == arr.length){
            return 0;
        }
        return arr[index] + solve(arr, index+1);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        int len = sc.nextInt();

        int[] arr = new int[len];
        System.out.println("Enter the elements in the array");
        for(int i=0; i<len; i++){
            arr[i] = sc.nextInt();
        }
        int ans = solve(arr, 0);
        System.out.println("Answer is: "+ans);
    }
}