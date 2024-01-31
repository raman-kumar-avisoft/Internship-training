import java.util.Scanner;

public class Add_all_boundary_and_daigonal_element_in_an_array {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows and cols in an array");
        int row = sc.nextInt();

        int[][] arr = new int[row][row];
        int sum = 0;

        System.out.println("Enter the elements in the 2D matrix");
        for (int i=0; i<row; i++){
            System.out.println((i+1)+" rowth elements");
            for (int j=0; j<row; j++){
                arr[i][j] = sc.nextInt();
            }
        }
//        for boundary elm
//        for top row
        for(int i=0; i<row; i++){
            sum += arr[0][i];
        }
        for(int i=0; i<row; i++){
            sum += arr[i][0];
        }
        for(int i=0; i<row; i++){
            sum += arr[i][row-1];
        }
        for(int i=0; i<row; i++){
            sum += arr[row-1][i];
        }
        sum -= (arr[0][0] + arr[row-1][row-1] + arr[0][row-1] + arr[row-1][0]);

//        diagonal
        for(int i=1; i<row-1; i++){
            sum += arr[i][i];
        }

        for(int i=row-2; i>0; i--){
            sum += arr[row-i-1][i];
        }

        sum-= arr[row/2][row/2];
        System.out.println("Sum is: "+sum);
    }
}