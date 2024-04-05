import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TripletSumClosestToVar {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);

        int maxWrongTries = 3;
        int arrayListSize = -1;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the size of the arraylist: ");
                int value = scannerInteger.nextInt();
                if( value <= 0 ){
                    throw new InputMismatchException();
                }
                arrayListSize = value;
                break;
            }catch (InputMismatchException inputMismatchException){
                System.out.println("NOT A VALID ARRAYLIST SIZE");
                scannerInteger.nextLine();
            }
        }

        System.out.println("Enter the element in the arrayList: ");
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int arrayListIndex=0; arrayListIndex < arrayListSize; arrayListIndex++){
            maxWrongTries=3;
            while(--maxWrongTries >= 0){
                try{
                    int value = scannerInteger.nextInt();
                    arrayList.add(value);
                    break;
                }catch(InputMismatchException inputMismatchException){
                    System.out.println("NOT A VALID NUMBER **");
                    scannerInteger.nextLine();
                }

            }
            if(maxWrongTries < 0){
                System.out.println("TOO MANY WRONG TRIES **");
                System.exit(0);
            }
        }

        maxWrongTries = 3;
        int sumClosestTo = -1;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the expected triplet sum of the arraylist: ");
                int value = scannerInteger.nextInt();
                if( value <= 0 ){
                    throw new InputMismatchException();
                }
                sumClosestTo = value;
                break;
            }catch (InputMismatchException inputMismatchException){
                System.out.println("NOT A VALID ARRAYLIST SIZE");
                scannerInteger.nextLine();
            }
        }
        int answer = findClosestTripletSum(arrayList, sumClosestTo);
        System.out.println("ANSWER IS: " + answer);
    }
    static int findClosestTripletSum(ArrayList<Integer> arrayList, int sumClosestTo){

        Collections.sort(arrayList);
        int closestSum = Integer.MAX_VALUE;
        int minDiff = Integer.MAX_VALUE;

        for (int arrayListIndex = 0; arrayListIndex < arrayList.size() - 2; arrayListIndex++) {
            int left = arrayListIndex + 1;
            int right = arrayList.size() - 1;

            while (left < right) {
                int sum = arrayList.get(arrayListIndex) + arrayList.get(left) + arrayList.get(right);
                int diff = Math.abs(sum - sumClosestTo);

                if (diff < minDiff) {
                    minDiff = diff;
                    closestSum = sum;
                }

                if (sum < sumClosestTo)
                    left++;
                else
                    right--;
            }
        }

        return closestSum;


//        BRUTE FORCE
//        int smallestAnswer = Integer.MIN_VALUE;
//        int largestAnswer = Integer.MAX_VALUE;
//        for(int firstElementIndex = 0; firstElementIndex < arrayList.size(); firstElementIndex++){
//            for(int secondElementIndex = firstElementIndex+1; secondElementIndex < arrayList.size(); secondElementIndex++){
//                for(int thirdElementIndex = secondElementIndex+1; thirdElementIndex < arrayList.size(); thirdElementIndex++){
//                    int sum = arrayList.get(firstElementIndex) + arrayList.get(secondElementIndex) + arrayList.get(thirdElementIndex);
//                    if(sum <= sumClosestTo){
//                        smallestAnswer = Math.max(smallestAnswer, sum);
//                    }else{
//                        largestAnswer = Math.min(largestAnswer, sum);
//                    }
//                }
//            }
//        }
//        smallestAnswer = sumClosestTo - smallestAnswer;
//        largestAnswer = largestAnswer - sumClosestTo;
//
//        return smallestAnswer<largestAnswer ? smallestAnswer+sumClosestTo : largestAnswer + sumClosestTo;
    }
}