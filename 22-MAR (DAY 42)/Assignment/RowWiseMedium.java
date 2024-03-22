import java.util.InputMismatchException;
import java.util.Scanner;

public class RowWiseMedium {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        int R = -1, C = -1;
        int maxWrongTries = 3;
        while(maxWrongTries-- > 0){
            try{
                System.out.print("Enter the Number of Rows: ");
                R = scannerInteger.nextInt();

                System.out.print("Enter the Number of Columns: ");
                C = scannerInteger.nextInt();

                break;
            } catch (InputMismatchException inputMismatchException){
                R = -1;
                C = -1;
                scannerInteger.nextLine();
            }
        }
        if(maxWrongTries == 0){
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }

        int[][] arr = new int[R][C];
        for(int rowIndex = 0; rowIndex < R; rowIndex++){
            for(int colIndex = 0; colIndex < C; colIndex++){
                maxWrongTries = 2;
                while(maxWrongTries-- > 0){
                    try{
                        System.out.print("ENTER THE ELEMENT FOR " + rowIndex + " ROW AND " + colIndex + " COLUMN: ");
                        int value = scannerInteger.nextInt();
                        arr[rowIndex][colIndex] = value;
                        break;
                    } catch (InputMismatchException inputMismatchException){
                        scannerInteger.nextLine();
                        System.out.println("NOT A INTEGER VALUE **");
                    }
                }
                if(maxWrongTries == 0){
                    System.out.println("TOO MANY WRONG TRIES **");
                    System.exit(0);
                }
            }
        }
        int answer = median(arr, R, C);
        System.out.println("ANSWER IS : " + answer);
    }
    static int median(int[][] matrix, int R, int C){
        // code here
        int s = 1;
        int e = 2000;

        int mid;
        int ans;

        while(s<=e){
            mid = (s+e)>>1;
            ans = 0;
            for(int i=0; i<R; i++){
                int s1 = 0;
                int e1 = C-1;
                int mid1;
                while(s1<=e1){
                    mid1 = (s1+e1)>>1;
                    if(matrix[i][mid1] <= mid){
                        s1 = mid1+1;
                    }else{
                        e1 = mid1-1;
                    }
                }
                ans += s1;
            }
            // ans = cnt;
            if(ans <= (C*R)>>1){
                s = mid + 1;
            }else{
                e = mid - 1;
            }
        }
        return s;
    }
}