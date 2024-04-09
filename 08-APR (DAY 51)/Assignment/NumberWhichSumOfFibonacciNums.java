import java.util.InputMismatchException;
import java.util.Scanner;

public class NumberWhichSumOfFibonacciNums {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);

        int maxWrongTries = 3;
        int givenNumber = -1;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the Number whose sum is equal to fibonacci number: ");
                givenNumber = scannerInteger.nextInt();

                if(givenNumber <= 1){
                    throw new InputMismatchException();
                }
                break;
            }catch (InputMismatchException inputMismatchException){
                System.out.println("NOT A VALID NUMBER **");
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }

        int answer = findMinFibonacciNumbers(givenNumber);
        System.out.println("ANSWER IS: " + answer);
    }
    public static int findMinFibonacciNumbers(int givenNumber) {
        int count = 0;
        int fib1 = 1, fib2 = 1;

        // Find Fibonacci numbers less than or equal to A
        while (fib1 <= givenNumber) {
            int temp = fib1 + fib2;
            fib1 = fib2;
            fib2 = temp;
        }

        // Greedy approach: Select Fibonacci numbers until A becomes 0
        while (givenNumber > 0) {
            while (fib1 > givenNumber) {
                int temp = fib2 - fib1;
                fib2 = fib1;
                fib1 = temp;
            }
            givenNumber -= fib1;
            count++;
        }

        return count;
    }
}