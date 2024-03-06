import java.util.InputMismatchException;
import java.util.Scanner;

public class MaxSumIn2XN_Matrix {
    public static void main(String[] args) {
        Scanner scannerInt = new Scanner(System.in);

        int maxWrongTries = 3;
        int numberOfCols = -1;
        while(maxWrongTries > 0){
            try{
                System.out.println("Enter the Number of Column in the 2XN Matrix");
                numberOfCols = scannerInt.nextInt();

                if(numberOfCols < 1 || numberOfCols > 20000){
                    throw new InputMismatchException("Number of colums must be in range of 1<= Number of columns <= 20000");
                }
                maxWrongTries = -1;
            }catch(InputMismatchException inputMismatchException){
                System.out.println(inputMismatchException.getMessage());
                maxWrongTries--;
            }
        }
        if(maxWrongTries == 0){
            System.out.println("too many wrong inputs --");
            System.exit(0);
        }

        int[][] mat = new int[2][numberOfCols];

        while(maxWrongTries > 0){
            for(int columnIndex=0; columnIndex < numberOfCols ; columnIndex++){
                maxWrongTries = 2;
                try{
                    System.out.print("Enter the data for upper row: ");
                    int upperRowData = scannerInt.nextInt();
                    if(upperRowData < 0 || upperRowData > 2000){
                        throw new InputMismatchException("The Data can only be range 1<= Data <= 2000");
                    }
                    mat[0][columnIndex] = upperRowData;

                    System.out.print("Enter the data for lower row: ");
                    int lowerRowData = scannerInt.nextInt();
                    if(lowerRowData < 0 || lowerRowData > 2000){
                        throw new InputMismatchException("The Data can only be range 1<= Data <= 2000");
                    }
                    mat[1][columnIndex] = lowerRowData;
                    maxWrongTries = -1;
                }catch (InputMismatchException inputMismatchException){
                    System.out.println(inputMismatchException.getMessage());
                    maxWrongTries--;
                }
                if(maxWrongTries == 0){
                    System.out.println("too many wrong tries !!");
                    System.exit(0);
                }
            }
        }

        int answerSum = calculateMaxSum(numberOfCols, mat);
        System.out.println("Answer is: " + answerSum);
    }

    static int calculateMaxSum(int numberOfCols, int arr[][]){
        displayArray(arr);

        arr[0][0] = Math.max(arr[0][0], arr[1][0]);
        if(numberOfCols == 1){
            return arr[0][0];
        }
        arr[0][1] = Math.max(arr[0][0], Math.max(arr[0][1], arr[1][1]));
        for(int i=2; i<numberOfCols; i++){
            arr[0][i] = Math.max(arr[0][i-1], Math.max(arr[0][i], arr[1][i]) + arr[0][i-2]);
        }

        return arr[0][numberOfCols-1];
    }
    static void displayArray(int arr[][]){
        for(int i=0; i<arr[0].length; i++){
            System.out.print(arr[0][i] + " ");
        }
        System.out.println();
        for(int i=0; i<arr[0].length; i++){
            System.out.print(arr[1][i] + " ");
        }
        System.out.println();
    }
}