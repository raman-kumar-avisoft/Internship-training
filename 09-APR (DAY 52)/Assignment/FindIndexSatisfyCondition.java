import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FindIndexSatisfyCondition {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        int maxWrongTries = 3;
        int arraySize = -1;

        while (--maxWrongTries >= 0) {
            try {
                System.out.print("Enter the size of the array: ");
                arraySize = scannerInteger.nextInt();
                if (arraySize <= 0) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException inputMismatchException) {
                scannerInteger.nextLine();
                System.out.println("NOT A VALID ARRAY SIZE **");
            }
        }
        if (maxWrongTries < 0) {
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }


        int array[] = new int[arraySize];
        for(int arrayIndex = 0; arrayIndex < arraySize; arrayIndex++){
            maxWrongTries = 3;
            while(--maxWrongTries >= 0){
                try{
                    System.out.print("Enter the value at index ["+arrayIndex+"]: ");
                    array[arrayIndex] = scannerInteger.nextInt();
                    break;
                }catch (InputMismatchException inputMismatchException){
                    scannerInteger.nextLine();
                }
            }
            if(maxWrongTries < 0){
                System.out.println("TOO MANY WRONG TRIES **");
                System.exit(0);
            }
        }

//        findIndexes(array, arraySize);
        ArrayList<Integer> answer = findIndexes(array, arraySize);

        // Display answer
        for(int value: answer){
            System.out.println(value+" ");
        }
    }
    static ArrayList<Integer> findIndexes(int[] array, int arraySize){

        HashMap<Integer, ArrayList<Integer>> sumMap = new HashMap<>();
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < arraySize; i++) {
            for (int j = i + 1; j < arraySize; j++) {
                int sum = array[i] + array[j];
                if (sumMap.containsKey(sum)) {

                    ArrayList<Integer> indices = sumMap.get(sum);
                    // Check the conditions for lexicographically smallest solution
                    if (indices.get(0) < i && indices.get(1) != i && indices.get(1) != j) {
                        result.addAll(indices);
                        result.add(i);
                        result.add(j);

                        return result;
                    }
                } else {
                    ArrayList<Integer> indices = new ArrayList<>();
                    indices.add(i);
                    indices.add(j);
                    sumMap.put(sum, indices);
                }
            }
        }
        return result;
        // BRUTE FORCE -> O(N^4)

//        int indices[] = new int[4];
//        for(int indicesIndex=0; indicesIndex<4; indicesIndex++){
//            indices[indicesIndex] = -1;
//        }

//        for(int i=0; i<arraySize; i++){
//            for(int j=i+1; j<arraySize; j++){
//                for(int k=0; k<arraySize; k++){
//                    for (int l=0; l<arraySize; l++){
//                        if (i != k && i != l && j != k && j != l) {
//                            if (array[i] + array[j] == array[k] + array[l]) {
//                                if (indices[0] == -1 ||
//                                        indices[0] > i || (indices[0] == i && indices[1] > j) ||
//                                        (indices[0] == i && indices[1] == j && indices[2] > k) ||
//                                        (indices[0] == i && indices[1] == j && indices[2] == k && indices[3] > l)) {
//                                    indices[0] = i;
//                                    indices[1] = j;
//                                    indices[2] = k;
//                                    indices[3] = l;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        if(indices[0] == -1){
//            System.out.println("NO PAIRS ARE FOUND **");
//        }
//        else{
//            System.out.println("INDICES ARE: ");
//            for(int value: indices){
//                System.out.println(value + " ");
//            }
//        }
    }
}