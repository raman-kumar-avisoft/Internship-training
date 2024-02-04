import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Sorted_arrays {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows in the matrix: ");
        int rows = sc.nextInt();

        System.out.println("Enter the number of cols in the matrix: ");
        int cols = sc.nextInt();

        int[][] arr = new int[rows][cols];
        System.out.println("Enter the elements in the array: ");
        for (int i=0; i<rows; i++){
            System.out.print("Enter for the "+i+"th row: ");
            for (int j=0; j<cols; j++){
                arr[i][j] = sc.nextInt();
            }
        }

        int[] arr2 = new int[rows*cols];

//        OPTIMAL APPROACH
        PriorityQueue<Integer> pr = new PriorityQueue<>();
        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++){
                pr.add(arr[i][j]);
            }
        }

        for(var i : pr){
            System.out.println(i.intValue());
        }

//        NOT OPTIMAL APPROACH
//        int count = 0;
//        for (int i=0; i<rows; i++){
//            for (int j=0; j<cols; j++){
//                arr2[count] = arr[i][j];
//                count++;
//            }
//        }
//
//        Arrays.sort(arr2);
//        for(int i=0; i<rows*cols; i++){
//            System.out.println(arr2[i]);
//        }

    }
}