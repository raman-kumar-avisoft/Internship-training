import java.util.*;

public class RodCutting {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        int maxWrongTries = 3;

        int rodlength = -1;
        while (--maxWrongTries >= 0) {
            try {
                System.out.print("Enter the length of the rod: ");
                rodlength = scannerInteger.nextInt();
                if (rodlength <= 0) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException inputMismatchException) {
                scannerInteger.nextLine();
                System.out.println("NOT A VALID ROD LENGTH **");
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }

        int cuts = -1;
        while (--maxWrongTries >= 0) {
            try {
                System.out.print("Enter the number of weak points: ");
                cuts = scannerInteger.nextInt();
                if (cuts <= 0 || cuts >= rodlength) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException inputMismatchException) {
                scannerInteger.nextLine();
                System.out.println("NOT A VALID CUTS VALUE **");
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }

        ArrayList<Integer> weakPoints = new ArrayList<>();
        for(int weakPointIndex=0; weakPointIndex < cuts; weakPointIndex++){
            maxWrongTries = 3;
            while(--maxWrongTries >= 0){
                try{
                    System.out.print("Enter the value of weak Point at index [" + weakPointIndex + "]: ");
                    int value = scannerInteger.nextInt();
                    weakPoints.add(value);
                    break;
                }catch (InputMismatchException inputMismatchException){
                    scannerInteger.nextLine();
                    System.out.println("NOT A VALID ELEMENT VALUE **");
                }
            }
            if(maxWrongTries < 0){
                System.out.println("TOO MANY WRONG TRIES **");
                System.exit(0);
            }
        }

        ArrayList<Integer> answer = rodCut(rodlength, weakPoints);
        for (int value: answer){
            System.out.print(value+" ");
        }
    }
    public static ArrayList<Integer> rodCut(int A, ArrayList<Integer> B) {
        // Sort the weak points in lexicographically smallest order
        Collections.sort(B);

        // Memoization table to store minimum costs
        HashMap<String, ArrayList<Integer>> memo = new HashMap<>();

        // Recursive function to find minimum cost
        ArrayList<Integer> result = solve(0, A, B, memo);

        return result;
    }

    private static ArrayList<Integer> solve(int start, int end, ArrayList<Integer> B, HashMap<String, ArrayList<Integer>> memo) {
        ArrayList<Integer> result = new ArrayList<>();
        // If the segment has no weak points, return empty result
        if (B.isEmpty()) {
            return result;
        }

        String key = start + "-" + end;
        if (memo.containsKey(key)) {
            return new ArrayList<>(memo.get(key));
        }

        int minCost = Integer.MAX_VALUE;
        ArrayList<Integer> minCut = new ArrayList<>();

        // Iterate through each weak point to find the optimal cut
        for (int p : B) {
            // Calculate the cost of making a cut at p
            int cost = end - start;

            // Calculate the cost of making cuts in the left and right segments
            ArrayList<Integer> leftCuts = solve(start, p, new ArrayList<>(B.subList(0, B.indexOf(p))), memo);
            ArrayList<Integer> rightCuts = solve(p, end, new ArrayList<>(B.subList(B.indexOf(p) + 1, B.size())), memo);

            // Calculate the total cost
            int totalCost = cost + leftCuts.stream().mapToInt(Integer::intValue).sum() + rightCuts.stream().mapToInt(Integer::intValue).sum();

            // Update minimum cost and cut sequence
            if (totalCost < minCost) {
                minCost = totalCost;
                minCut.clear();
                minCut.add(p);
                minCut.addAll(leftCuts);
                minCut.addAll(rightCuts);
            }
        }

        memo.put(key, new ArrayList<>(minCut));
        return minCut;
    }

}
