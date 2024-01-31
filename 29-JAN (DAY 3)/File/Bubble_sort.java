import java.util.Scanner;

public class Bubble_sort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the length of array");
        int leng = sc.nextInt();

        int[] arr = new int[leng];
        System.out.println("Insert the element in the array");
        for(int i=0; i<leng; i++){
            arr[i] = sc.nextInt();
        }

        for (int i = 0 ; i<leng; i++){
            for(int j = 1; j <leng-i; j++){
                if(arr[j-1]>arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }

        for(int i=0; i<leng; i++){
            System.out.println(arr[i]);
        }
    }
}