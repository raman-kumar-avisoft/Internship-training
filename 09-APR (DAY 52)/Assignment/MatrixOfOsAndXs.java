import java.util.InputMismatchException;
import java.util.Scanner;

public class MatrixOfOsAndXs {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        Scanner scannerString = new Scanner(System.in);

        int maxWrongTries = 3;
        int rows = -1, cols = -1;
        while (--maxWrongTries >= 0) {
            try {
                System.out.print("Enter the number of rows in the matrix: ");
                rows = scannerInteger.nextInt();
                System.out.print("Enter the number of cols in the matrix: ");
                cols = scannerInteger.nextInt();


                if (rows <= 0) {
                    throw new InputMismatchException();
                }
                if (cols <= 0) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("NOT A VALID ROWS AND COLUMNS VALUE **");
                scannerInteger.nextLine();
            }
        }
        if (maxWrongTries < 0) {
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }

        char[][] matrix = new char[rows][cols];
        // Insert elements into the matrix
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            for (int colIndex = 0; colIndex < cols; colIndex++) {
                maxWrongTries = 3;
                while (--maxWrongTries >= 0){
                    try {
                        System.out.print("Enter the value for indices [" + rowIndex + "][" + colIndex + "]: ");
                        char character = scannerString.next().charAt(0);
                        if (character != 'O' && character != 'X') {
                            throw new InputMismatchException();
                        }

                        matrix[rowIndex][colIndex] = character;
                        break;
                    } catch (InputMismatchException inputMismatchException) {
                        scannerString.nextLine();
                        System.out.println("NOT A VALID MATRIX VALUE **");
                    }
                }
                if (maxWrongTries < 0){
                    System.out.println("TOO MANY WRONG TRIES **");
                    System.exit(0);
                }
            }
        }
        int answer = countShapes(matrix);
        System.out.println("Number of shapes are: " + answer);

    }
    static int countShapes(char[][] matrix){
        int shapesCount = 0;
        for(int rowIndex=0; rowIndex < matrix.length; rowIndex++){
            for(int colIndex=0; colIndex <matrix[rowIndex].length; colIndex++){
                if(matrix[rowIndex][colIndex] == 'X'){
                    shapesCount++;
                    solve(matrix, rowIndex, colIndex);
                }
            }
        }
        return shapesCount;
    }
    static void solve(char[][] matrix, int rowIndex, int colIndex){
        matrix[rowIndex][colIndex] = 'O';

        // left
        if(rowIndex-1>=0 && matrix[rowIndex-1][colIndex] == 'X'){
            solve(matrix,rowIndex-1,colIndex);
        }
        // right
        if(rowIndex+1 < matrix.length && matrix[rowIndex+1][colIndex] == 'X'){
            solve(matrix,rowIndex+1,colIndex);
        }
        // up
        if(colIndex-1>=0 && matrix[rowIndex][colIndex-1] == 'X'){
            solve(matrix,rowIndex,colIndex-1);
        }
        // down
        if(colIndex+1 < matrix[rowIndex].length && matrix[rowIndex][colIndex+1] == 'X'){
            solve(matrix,rowIndex,colIndex+1);
        }
    }
}