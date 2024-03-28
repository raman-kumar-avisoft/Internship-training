import java.util.*;

public class TrampolineJumps {

    static class Pair {
        int shop;
        int jumps;

        Pair(int shop, int jumps) {
            this.shop = shop;
            this.jumps = jumps;
        }
    }

    public static int minTrampolineJumps(int[] arr) {
        int n = arr.length;
        Queue<Pair> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(new Pair(0, 0)); // Start from shop 0
        visited[0] = true; // Mark shop 0 as visited

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            int shop = current.shop;
            int jumps = current.jumps;

            if (shop == n - 1) { // If reached the last shop, return the number of jumps
                return jumps;
            }

            // Explore all possible jumps from the current shop
            for (int i = shop + 1; i <= Math.min(shop + arr[shop], n - 1); i++) {
                if (!visited[i]) {
                    queue.offer(new Pair(i, jumps + 1));
                    visited[i] = true;
                }
            }
        }

        return -1; // If it's impossible to reach the last shop
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = 0; // Size of the array

        boolean validInputN = false;
        int attemptsN = 0;
        while (!validInputN && attemptsN < 3) {
            try {
                System.out.print("Enter the size of array: ");
                N = scanner.nextInt();
                if (N > 0) {
                    validInputN = true;
                } else {
                    System.out.println("Invalid input! Please enter a positive integer value.");
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Invalid input! Please enter an integer value.");
                scanner.nextLine(); // Consume the invalid input
            }
            attemptsN++;
        }

        if (validInputN) {
            int[] Arr = new int[N]; // Array representing the shops

            System.out.print("Enter the elements of array: ");
            for (int i = 0; i < N; i++) {
                boolean validInputArr = false;
                int attemptsArr = 0;
                while (!validInputArr && attemptsArr < 3) {
                    try {
                        Arr[i] = scanner.nextInt();
                        validInputArr = true;
                    } catch (InputMismatchException | NumberFormatException e) {
                        System.out.println("Invalid input! Please enter an integer value.");
                        scanner.nextLine(); // Consume the invalid input
                    }
                    attemptsArr++;
                }
            }

            int minJumps = minTrampolineJumps(Arr);
            System.out.println("Result: " + minJumps);
        }
    }
}
