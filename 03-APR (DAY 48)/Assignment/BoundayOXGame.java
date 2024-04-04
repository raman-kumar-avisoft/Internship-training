import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BoundayOXGame {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        Scanner scannerString = new Scanner(System.in);

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

        ArrayList<ArrayList<Character>> maze = new ArrayList<>();
        System.out.println("INSERTING THE ELEMENTS IN THE MAZE: ");
        for(int rowIndex=0; rowIndex < rows; rowIndex++){
            ArrayList<Character> row = new ArrayList<>();
            for(int colIndex=0; colIndex < cols; colIndex++){
                maxWrongTries = 3;
                while(--maxWrongTries >= 0){
                    try{
                        System.out.print("Enter the element at the ["+rowIndex+"]["+colIndex+"]: ");
                        char value = scannerString.next().charAt(0);
                        if(value != '0' && value != 'X'){
                            throw new InputMismatchException();
                        }
                        row.add(value);
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
            maze.add(row);
        }

        solve(maze);
    }
    static public void solve(ArrayList<ArrayList<Character>> A) {
        if (A == null || A.size() == 0) return;

        int rows = A.size();
        int cols = A.get(0).size();

        // Traverse the first and last rows
        for (int col = 0; col < cols; col++) {
            if (A.get(0).get(col) == 'O') {
                dfs(A, 0, col);
            }
            if (A.get(rows - 1).get(col) == 'O') {
                dfs(A, rows - 1, col);
            }
        }

        // Traverse the first and last columns
        for (int row = 0; row < rows; row++) {
            if (A.get(row).get(0) == 'O') {
                dfs(A, row, 0);
            }
            if (A.get(row).get(cols - 1) == 'O') {
                dfs(A, row, cols - 1);
            }
        }

        // Flip 'O' to 'X' and restore '#' to 'O'
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (A.get(row).get(col) == 'O') {
                    A.get(row).set(col, 'X');
                } else if (A.get(row).get(col) == '#') {
                    A.get(row).set(col, 'O');
                }
            }
        }
    }

    static void dfs(ArrayList<ArrayList<Character>> A, int row, int col) {
        if (row < 0 || row >= A.size() || col < 0 || col >= A.get(0).size() || A.get(row).get(col) != 'O') {
            return;
        }

        A.get(row).set(col, '#'); // Mark 'O' as visited

        // Perform DFS in all four directions
        dfs(A, row + 1, col);
        dfs(A, row - 1, col);
        dfs(A, row, col + 1);
        dfs(A, row, col - 1);
    }
}