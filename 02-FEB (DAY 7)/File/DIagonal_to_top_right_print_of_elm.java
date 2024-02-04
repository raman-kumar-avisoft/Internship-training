import java.util.Scanner;

public class DIagonal_to_top_right_print_of_elm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows or cols: ");
        int row = sc.nextInt();

        int[][] arr = new int[row][row];
        for(int i=0; i<row; i++){
            System.out.println("Enter the element for "+i+"th row");
            for(int j=0; j<row; j++){
                arr[i][j] = sc.nextInt();
            }
        }

//        logic
        for(int i=0; i<row; i++){
            int k=0;
            for(int j=i; j<row; j++){
                System.out.print(arr[k][j]+" ");
                k++;
            }
            System.out.println();
        }

    }
}