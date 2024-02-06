import java.util.Scanner;

public class Merge_Sort_without_any_Extra_Space {
    static void merge(int[] arr, int s, int mid, int e){
        int i = s;
        int j = mid+1;
//        int k = s;
        while(i < j && j < e){
            if(arr[j] < arr[i]){
                int elm = arr[j];
//                shifting
                int k = j;
                for(; k>i; k--){
                    arr[k] = arr[k-1];
                }
                arr[k] = elm;
                i++;
                j++;
            }else{
                i++;
            }
        }
//        while(i<=mid)
    }
    static void solve(int[] arr, int s, int e){
//        base condition
        if(s>=e){
            return;
        }
        int mid = (e-s)/2 + s;
        solve(arr, s, mid);
        solve(arr, mid+1, e);
        merge(arr, s, mid, e);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        int len = sc.nextInt();

        int[] arr = new int[len];
        System.out.println("Enter the elements in the array: ");
        for(int i=0; i<len; i++){
            arr[i] = sc.nextInt();
        }

        solve(arr, 0, arr.length-1);

        System.out.println("Elements in array after merge sort are: ");
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i]);
        }
    }
}