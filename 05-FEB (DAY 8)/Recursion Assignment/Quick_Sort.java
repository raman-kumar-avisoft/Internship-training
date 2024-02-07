import java.util.Scanner;

public class Quick_Sort {
    static int partition(int arr[], int s, int e){
        int pivot = arr[e];

//        move the array sorted with respect to pivot element
        int j=s;
        for(int i=s; i<e; i++){
            if(arr[i]<=pivot){
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                j++;
            }
        }
        int temp = arr[e];
        arr[e] = arr[j];
        arr[j] = temp;
        return j;
    }
    static void quickSort(int arr[], int s, int e){
        if(s>=e){
            return;
        }

        int pivot = partition(arr, s, e);
        quickSort(arr, s , pivot-1);
        quickSort(arr, pivot+1, e);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of element you want to insert in the array: ");
        int len = sc.nextInt();

        int arr[] = new int[len];
        System.out.println("Enter all elements in the array");
        for (int i=0; i<len; i++){
            arr[i] = sc.nextInt();
        }

        System.out.println("Before applying quick sort elements in the array is: ");
        for(int i=0; i<len; i++){
            System.out.println(arr[i] + " ");
        }

        quickSort(arr, 0 , arr.length - 1);

        System.out.println("Array after applying quick sort is: ");
        for(int i=0; i<len; i++){
            System.out.println(arr[i] + " ");
        }
    }
}
