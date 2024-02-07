import java.util.Scanner;

public class Find_longest_subsequence_thats_sorted_using_recursion {
    static int solve(int[] arr, int index, int ans){
//        base condition
        if(arr.length == 0){
            return 0;
        }else if(index == arr.length-1){
            return 1;
        }
        ans = solve(arr, index+1, ans);
        if(ans == 0){
            ans = 1;
        }else{
            if(arr[index] <= arr[index+1]){
                ans = ans + 1;
            }else{
                ans = 1;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        int len = sc.nextInt();

        int arr[] = new int[len];
        System.out.println("Enter the elements in the array");
        for (int i=0 ; i<len; i++){
            arr[i] = sc.nextInt();
//            System.out.println(arr[i]);
        }

        int ans = solve(arr, 0, 0);
        System.out.println("Answer is: " + ans);
    }
}