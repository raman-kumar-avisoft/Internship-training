import java.util.Scanner;

public class Merge_sort {
    static void merge(int[] arr, int s, int mid, int e){
        int[] arr2 = new int[e+1];
        int i = s;
        int j = mid+1;
        int k=0;

        while(i<=mid && j<=e){
            if(arr[i]<=arr[j]){
                arr2[k] = arr[i];
                i++;
            }else{
                arr2[k] = arr[j];
                j++;
            }
            k++;
        }
        while(i<=mid){
            arr2[k] = arr[i];
            i++;
            k++;
        }
        while(j<=e){
            arr2[k] = arr[j];
            j++;
            k++;
        }
//        copying elements from arr2 to arr
        int z=0;
        for(int m=s; m<=e; m++,z++){
            arr[m] = arr2[z];
        }
    }
    static void mergesort(int[] arr, int s, int e){
        if(s>=e){
            return;
        }
        int mid = (e-s)/2 + s;
        mergesort(arr, s, mid);
        mergesort(arr, mid+1, e);
        merge(arr, s, mid, e);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        int leng = sc.nextInt();

        int[] arr = new int[leng];
        System.out.println("Enter the elements in the array: ");
        for (int i=0; i<leng; i++){
            arr[i] = sc.nextInt();
        }

        System.out.println("Array before merge sort");
        for(int i=0; i<leng; i++){
            System.out.print(arr[i]+" ");
        }
        mergesort(arr, 0, leng-1);
        System.out.println("\nArray after merge sort");
        for(int i=0; i<leng; i++){
            System.out.print(arr[i]+" ");
        }
    }
}