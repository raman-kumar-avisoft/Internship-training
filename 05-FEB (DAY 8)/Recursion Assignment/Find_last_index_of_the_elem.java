import java.util.Scanner;

public class Find_last_index_of_the_elem {
    static int solve(int[] arr, int index, int elm){
        int ans = -1;
        if(index == arr.length){
            return ans;
        }
        ans = solve(arr, index+1, elm);
        if(arr[index] == elm && ans == -1){
            ans = index;
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of element in the array: ");

        int len = sc.nextInt();
        int[] arr = new int[len];
        System.out.println("Enter the element in the array: ");
        for(int i=0; i<len; i++){
            arr[i] = sc.nextInt();
        }

        System.out.println("Enter the element you want to search: ");
        int elm = sc.nextInt();
        int ans = solve(arr,0,elm);
        System.out.println("Element's last index is: "+ ans);
    }
}