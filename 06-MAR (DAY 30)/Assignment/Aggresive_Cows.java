import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Aggresive_Cows {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);

        int numberOfStalls = -1;
        int maxWrongTries = 2;
        while(maxWrongTries>0){
            try{
                System.out.print("Enter the Number of Stalls: ");
                numberOfStalls = scannerInteger.nextInt();

                if(numberOfStalls < 2){
                    throw new InputMismatchException();
                }
                maxWrongTries = -1;
            }catch (InputMismatchException inputMismatchException){
                maxWrongTries--;
                if(maxWrongTries==0){
                    System.out.println("too many wrong tries !!");
                    System.exit(0);
                }
                System.out.println("Enter a valid number of stalls");
            }
        }

//        insert the stall position
        int[] stalls = new int[numberOfStalls];

        maxWrongTries = 2;
        System.out.println("Enter the Stalls Position of stalls sequentially: ");
        for(int stallsIndex = 0; stallsIndex < stalls.length; stallsIndex++){
            try{
                int value = scannerInteger.nextInt();
                stalls[stallsIndex] = value;
                if(value < 0){
                    throw new InputMismatchException();
                }
            }catch (InputMismatchException inputMismatchException){
                stallsIndex--;
                maxWrongTries--;
                if(maxWrongTries==0){
                    System.out.println("too many wrong tries !!");
                    System.exit(0);
                }
                System.out.println("Enter the valid stall position !!");
            }
        }

        maxWrongTries = 2;
        int numberOfAggressiveCows = -1;
        while(maxWrongTries>0){
            try{
                System.out.print("Enter the Number of Aggressive Cows: ");
                numberOfAggressiveCows = scannerInteger.nextInt();

                if(numberOfAggressiveCows < 2 || numberOfAggressiveCows >numberOfStalls){
                    throw new InputMismatchException();
                }
                maxWrongTries = -1;
            }catch (InputMismatchException inputMismatchException){
                maxWrongTries--;
                if(maxWrongTries==0){
                    System.out.println("too many wrong tries !!");
                    System.exit(0);
                }
                System.out.println("Enter a valid number of Aggressive cows");
            }
        }

//        MAIN LOGIC OF THE PROBLEM
        int answer = maxDistanceBwAggressiveCows(stalls, numberOfAggressiveCows);
        System.out.println("Maximum Possible distance between aggressive cows are: " + answer);
    }
    static int maxDistanceBwAggressiveCows(int[] stalls, int numberOfAggressiveCows){
        Arrays.sort(stalls);

        if(numberOfAggressiveCows == 2){
            return stalls[stalls.length - 1] - stalls[0];
        }

        int maxDistance = 0;
        int s = 0;
        int e = stalls[stalls.length - 1] - stalls[0];

        while(s<=e){
            int mid = (e-s)/2 + s;

            boolean possible = checkPossibleAnswer(mid, numberOfAggressiveCows, stalls);
            if(possible){
                maxDistance = mid;
                s = mid+1;
            }else{
                e = mid -1;
            }
        }
        return maxDistance;
    }
    static boolean checkPossibleAnswer(int mid, int numberOfAggressiveCows, int[] stalls){
        int selectedStall = stalls[0];
        for(int stallsIndex = 1; stallsIndex < stalls.length; stallsIndex++){
            if(stalls[selectedStall]-selectedStall >= mid){
                selectedStall = stalls[selectedStall];
                numberOfAggressiveCows--;
                if(numberOfAggressiveCows == 0){
                    return true;
                }
            }
        }
        return false;
    }
}