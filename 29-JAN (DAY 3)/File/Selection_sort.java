import java.util.Scanner;

public class Selection_sort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the length of array");
        int leng = sc.nextInt();

        int[] arr = new int[leng];
        System.out.println("Insert the element in the array");
        for(int i=0; i<leng; i++){
            arr[i] = sc.nextInt();
        }

//        main logic

        for(int i=0; i<leng; i++){
            int mini = arr[i];
            int index = i;
            for(int j=i+1; j<leng; j++){
                if(mini>arr[j]){
                    mini = arr[j];
                    index = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }

        System.out.println("After sorting array elements are: ");
        for(int i=0; i<leng; i++){
            System.out.println(arr[i]);
        }
    }
}