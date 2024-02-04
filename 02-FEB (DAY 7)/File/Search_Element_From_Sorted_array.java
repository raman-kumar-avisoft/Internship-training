import java.util.Scanner;

public class Search_Element_From_Sorted_array {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows of sorted array: ");
        int row = sc.nextInt();

        System.out.println("Enter the number of cols of sorted array: ");
        int col = sc.nextInt();

        int[][] arr = new int[row][col];
        System.out.println("Enter the element in the array");
        for(int i=0; i<row; i++){
            for(int j = 0; j<col; j++){
                arr[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter the element you want to search: ");
        int elem = sc.nextInt();
        boolean found = false;
        for (int i=0; i<row && found == false; i++){
            int s = 0;
            int e = col-1;

            while(s<=e){
                int mid = (e-s)/2 + s;
                if(arr[i][mid] == elem){
                    found = true;
                    break;
                }else if(arr[i][mid] <elem){
                    s++;
                }else{
                    e--;
                }
            }

        }
        if(found){
            System.out.println("Successful");
        }else{
            System.out.println("Unsuccessful");
        }
    }
}