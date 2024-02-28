import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class SlidingWindowMaxElement{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int count = 3;
        while(count>1){
            try{
                System.out.print("Enter the size of the array: ");
                int arrSize = sc.nextInt();
                if(arrSize <= 0){
                    throw new InputMismatchException();
                }

                int[] array = new int[arrSize];
                System.out.println("Enter the elements in the array");
                for(int i=0; i<arrSize; i++){
                    array[i] = sc.nextInt();
                }

                System.out.println("Enter the sliding window Size: ");
                int slidingWindowSize = sc.nextInt();
                if(slidingWindowSize <= 0){
                    throw new InputMismatchException();
                }

                ArrayList<Integer> output = maxElementInSlidingWindow(array, arrSize, slidingWindowSize);

//              Displaying Answer
                System.out.print("Answer is: ");
                for(int i=0; i<output.size(); i++){
                    System.out.print(output.get(i) + " ");
                }
                count = -1;
            }
            catch (InputMismatchException e){
                System.out.println("Enter a valid input value");
            }finally {
                count--;
            }
        }
    }
    static ArrayList<Integer> maxElementInSlidingWindow(int[] arr, int arrSize, int slidingWindowSize){
        ArrayList<Integer> answer = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int slidingWindowIndex = 0;
        for(int arrIndex=0; arrIndex<arrSize; arrIndex++){
            addElementToStack(stack, arr[arrIndex]);
            if(slidingWindowSize>=1){
                if(slidingWindowSize == 1){
                    answer.add(stack.peek());
                }
                slidingWindowSize--;
            }else{
                removeElementToStack(stack, arr[slidingWindowIndex]);
                slidingWindowIndex++;
                answer.add(stack.peek());
            }
        }

//        if the size of the slidingWindowSize > the size of the array the return the greatest element in the whole array.
        if(slidingWindowSize>=1){
                answer.add(stack.peek());
        }
        return answer;
    }
    static void displayStack(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }

        int value = stack.pop();
        System.out.println(value);
        displayStack(stack);
        stack.push(value);
    }
    static void addElementToStack(Stack<Integer> stack, int elem){
        if(stack.isEmpty() || stack.peek() <= elem){
            stack.push(elem);
            return;
        }

        int value = stack.pop();
        addElementToStack(stack, elem);
        stack.push(value);
    }
    static void removeElementToStack(Stack<Integer> stack, int elem){
        if(stack.isEmpty()){
            return;
        }
        if(stack.peek() == elem){
            stack.pop();
            return;
        }

        int value = stack.pop();
        addElementToStack(stack, elem);
        stack.push(value);
    }
}