import java.util.HashMap;
import java.util.Scanner;

public class Sum_of_Zeroes {
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

//        logic
        int ans = 0;
        for (int i=0; i<rows; i++){
            for (int j=0; j<cols; j++){
                if(arr[i][j] == 0){
//                    left adjacent
                    if((j-1)>=0 && arr[i][j-1] == 1){
                        ans++;
                    }
//                    right adjacent
                    if((j+1)<cols && arr[i][j+1] == 1){
                        ans++;
                    }
//                    top adjacent
                    if((i-1)>=0 && arr[i-1][j] == 1){
                        ans++;
                    }
//                    bottom adjacent
                    if((i+1)<rows && arr[i+1][j] == 1){
                        ans++;
                    }
                }
            }
        }

//        answer is
        System.out.println("Answer of the following question after manipulation is: " + ans);
    }
}