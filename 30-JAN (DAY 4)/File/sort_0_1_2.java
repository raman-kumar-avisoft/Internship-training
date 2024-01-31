import java.util.Scanner;

public class sort_0_1_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        int len = sc.nextInt();

        int j =0;
        int k = len-1;
        int[] arr = new int[len];
        System.out.println("Enter the elements of the array: ");
        for (int i=0; i<len; i++){
            arr[i] = sc.nextInt();
        }

        for(int i=0; i<len; i++){
            if(arr[i] == 0){
//                swap i, j
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
                System.out.println(i);
            }else if(arr[i] == 2 && k>=i){
//                swap i, k
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
                k--;
                if(arr[i] == 0){
                    int temp2 = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp2;
                    j++;
                }
            }
        }
        System.out.println("AFTER SORTING");
        for(int i=0; i <len; i++){
            System.out.println(arr[i]);
        }
    }
}