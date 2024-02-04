import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Set_Matrix_zero {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows in the matrix: ");
        int rows = sc.nextInt();

        System.out.println("Enter the number of cols in the matrix: ");
        int cols = sc.nextInt();

        HashMap<Integer, Integer> row = new HashMap<>();
        HashMap<Integer, Integer> col = new HashMap<>();

        int[][] arr = new int[rows][cols];
        System.out.println("Enter the elements in the array: ");
        for (int i=0; i<rows; i++){
            System.out.print("Enter for the "+i+"th row: ");
            for (int j=0; j<cols; j++){
                arr[i][j] = sc.nextInt();
            }
        }

//        logic
        for (int i=0; i<rows; i++){
            System.out.print("Enter for the "+i+"th row: ");
            for (int j=0; j<cols; j++){
                if(arr[i][j] == 0){
                    if(!row.containsKey(i)){
                        row.put(i,1);
                    }
                    if(!col.containsKey(i)){
                        col.put(j,1);
                    }
                }
            }
        }

//        traversing the array
        for (int i=0; i<rows; i++){
            System.out.print("Enter for the "+i+"th row: ");
            for (int j=0; j<cols; j++){
                if(row.containsKey(i)){
                    arr[i][j] = 0;
                }
                if(col.containsKey(j)){
                    arr[i][j] = 0;
                }
            }
        }

//        answer is
        System.out.println("Answer of the following question after manipulation is: ");
        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
}