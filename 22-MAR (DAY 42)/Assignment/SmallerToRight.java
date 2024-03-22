import java.util.InputMismatchException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SmallerToRight {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        int maxWrongTries = 3;
        int sz = -1;
        while(maxWrongTries-- > 0){
            try{
                System.out.print("Enter the size of the array: ");
                sz = scannerInteger.nextInt();
                if(sz<=0){
                    throw new InputMismatchException();
                }
                break;
            }catch (InputMismatchException inputMismatchException){
                sz = -1;
                scannerInteger.nextInt();
            }
        }
        if(sz == -1){
            System.out.println("TOO MANY WRONG TRIES**");
            System.exit(0);
        }
        int[] arr = new int[sz];
        maxWrongTries = 3;
        for(int arrIndex = 0; arrIndex < arr.length; arrIndex++){
            try{
                System.out.print("Enter the element: ");
                arr[arrIndex] = scannerInteger.nextInt();

            } catch(InputMismatchException inputMismatchException){
                scannerInteger.nextLine();
                maxWrongTries--;
                arrIndex--;
                if(maxWrongTries == 0){
                    System.out.println("TOO MANY TRIES**");
                    break;
                }
            }
        }
        displayArray(arr);
        int[] answer = arrayWithSmallerRightSideValues(arr);
        System.out.println("\nANSWER ARRAY IS: ");
        displayArray(answer);
    }
    static void displayArray(int[] arr){
        System.out.print("Elements in the array: ");
        for(int value: arr){
            System.out.print(value+" ");
        }
    }
    static int[] arrayWithSmallerRightSideValues(int[] arr){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int[] answer = new int[arr.length];
        answer[arr.length-1] = 0;
        priorityQueue.add(arr[arr.length-1]);
        for(int arrIndex = arr.length-2; arrIndex >= 0; arrIndex--){
            answer[arrIndex] = solve(priorityQueue, arr[arrIndex]);
            priorityQueue.add(arr[arrIndex]);
        }
        return answer;
    }
    static int solve(PriorityQueue<Integer> pr, int value){
        if(pr.isEmpty() || pr.peek() < value){
            return pr.size();
        }
        int elem = pr.remove();
        int ans = solve(pr,value);
        pr.add(elem);
        return ans;
    }
}