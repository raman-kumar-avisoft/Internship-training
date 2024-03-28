import java.util.InputMismatchException;
import java.util.Scanner;

public class UniquePaths {
    static public int findUniquePaths(int m, int n) {
        return uniquePathsRecursive(0, 0, m, n);
    }

    static private int uniquePathsRecursive(int i, int j, int m, int n) {
        // Base case: If we reach the bottom-right cell, return 1
        if (i == m - 1 && j == n - 1) {
            return 1;
        }

        // If we reach the boundary, return 0
        if (i >= m || j >= n) {
            return 0;
        }

        // Recursive cases: Move right or down
        return uniquePathsRecursive(i + 1, j, m, n) + uniquePathsRecursive(i, j + 1, m, n);
    }
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);

        int row = -1, col =-1;
        int maxWrongTries = 3;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the number of Rows: ");
                row = scannerInteger.nextInt();

                if(row <= 0){
                    throw new InputMismatchException();
                }

                break;
            }catch (InputMismatchException inputMismatchException){
                scannerInteger.nextLine();
                System.out.println("NOT A VALID ROW COUNT **");
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }

        maxWrongTries = 3;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the number of Rows: ");
                col = scannerInteger.nextInt();

                if(col <= 0){
                    throw new InputMismatchException();
                }

                break;
            }catch (InputMismatchException inputMismatchException){
                scannerInteger.nextLine();
                System.out.println("NOT A VALID COLUMN COUNT **");
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }

        int arr[][] = new int[row][col];
        System.out.println("Enter the element in the Matrix: ");
        for(int rowIndex = 0; rowIndex < row; rowIndex++){
            for(int colIndex = 0; colIndex < col; colIndex++){
                maxWrongTries = 2;
                while(--maxWrongTries >= 0){
                    try{
                        System.out.println("Enter the element at position ["+rowIndex+"]"+"["+colIndex+"]");
                        int value = scannerInteger.nextInt();
                        arr[rowIndex][colIndex] = value;
                    }catch (InputMismatchException inputMismatchException){
                        scannerInteger.nextLine();
                        System.out.println("NOT A VALID NUMBER **");
                    }
                }
                if(maxWrongTries < 0){
                    System.out.println("TOO MANY WRONG TRIES **");
                    System.exit(0);
                }
            }
        }

        int answer = findUniquePaths(row, col);
        System.out.println("ANSWER IS: " + answer);
    }
}