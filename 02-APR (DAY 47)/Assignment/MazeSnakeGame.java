import java.util.InputMismatchException;
import java.util.Scanner;

public class MazeSnakeGame {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);

        int rows = -1, cols = -1;
        int maxWrongTries = 3;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the number of rows: ");
                rows = scannerInteger.nextInt();
                System.out.print("Enter the number of cols: ");
                cols = scannerInteger.nextInt();
                if(rows <= 0 || cols <= 0){
                    throw new InputMismatchException();
                }
                break;
            }
            catch (InputMismatchException inputMismatchException){
                System.out.println("NOT A VALID ROW AND COL **");
                scannerInteger.nextLine();
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }

        int maze[][] = new int[rows][cols];
        System.out.println("INSERTING THE ELEMENTS IN THE MAZE: ");
        for(int rowIndex=0; rowIndex < rows; rowIndex++){
            for(int colIndex=0; colIndex < cols; colIndex++){
                maxWrongTries = 3;
                while(--maxWrongTries >= 0){
                    try{
                        System.out.print("Enter the element at the ["+rowIndex+"]["+colIndex+"]: ");
                        int value = scannerInteger.nextInt();
                        if(value != 0 && value != -1){
                            throw new InputMismatchException();
                        }
                        maze[rowIndex][colIndex] = value;
                        break;
                    }catch (InputMismatchException inputMismatchException){
                        System.out.println("NOT A VALID MAZE VALUE **");
                    }
                }
                if(maxWrongTries < 0){
                    System.out.println("TOO MANY WRONG TRIES **");
                    System.exit(0);
                }
            }
        }

        int uniquePaths = findUniquePaths(maze, 0, 0, rows, cols);
        System.out.println("Unique Paths are: " + uniquePaths);
    }
    static int findUniquePaths(int maze[][], int rowIndex, int colIndex, int rows, int cols){
        if(rowIndex >= rows || colIndex >= cols || maze[rowIndex][colIndex] == -1){
            return 0;
        }
        if(rowIndex == rows-1 && colIndex == cols-1 && maze[rowIndex][colIndex] != -1){
            return 1;
        }

        return findUniquePaths(maze, rowIndex+1, colIndex, rows, cols) + findUniquePaths(maze, rowIndex, colIndex+1, rows, cols);
    }
}