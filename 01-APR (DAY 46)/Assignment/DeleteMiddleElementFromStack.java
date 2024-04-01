import java.util.*;

public class DeleteMiddleElementFromStack {

    public static void deleteMiddleElement(int[] arr) {
        int n = arr.length;
        int midIndex = (n - 1) / 2; // Index of the middlemost element

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (i != midIndex) { // Skip the middlemost element
                stack.push(arr[i]);
            }
        }

        // Print the remaining elements in the stack
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                int n = scanner.nextInt(); // Size of the stack
                if (n < 1) {
                    throw new IllegalArgumentException("Stack size must be at least 1.");
                }

                int[] arr = new int[n + 1]; // Create an array to store the stack elements

                // Input stack elements
                for (int j = 0; j <= n; j++) {
                    arr[j] = scanner.nextInt();
                }

                // Delete the middlemost element from the stack and print the resulting stack
                deleteMiddleElement(arr);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid integers.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
