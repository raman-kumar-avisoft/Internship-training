import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ArrowsToPopBallons {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);

        int maxWrongTries = 3;
        boolean continueLooping = true;
        int sz = -1;
        while(continueLooping){
            try{
                System.out.print("Enter the size of the array: ");
                sz = scannerInteger.nextInt();
                if(sz <= 0){
                    throw new InputMismatchException();
                }
                break;
            }catch(InputMismatchException inputMismatchException){
                sz = -1;
                if(maxWrongTries-- == 0){
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }
        if(sz == -1){
            System.exit(0);
        }

        int[] balloonHeight = new int[sz];
        System.out.println("Enter the elements in the balloonHeight -");
        maxWrongTries = 3;
        for(int index=0; index<sz; index++){
            try{
                System.out.print("Enter the element: ");
                int value = scannerInteger.nextInt();
                balloonHeight[index] = value;
            }catch (InputMismatchException inputMismatchException){
                index--;
                scannerInteger.nextLine();
                maxWrongTries--;
                if(maxWrongTries == 0){
                    System.out.println("TOO MANY WRONG TRIES **");
                    System.exit(0);
                }
            }
        }
        int answer = burstingBalloons(balloonHeight, sz);
        System.out.println("ANSWER IS: " + answer);
    }
    public static int burstingBalloons(int[] arr, int n) {

        // base case
        if(arr.length == 1){
            return 1;
        }

        HashMap<Integer, Integer> hm = new HashMap<>();
        // setting hashMap values.
        for(int value: arr){
            if(hm.containsKey(value)){
                hm.put(value, hm.get(value)+1);
            }else{
                hm.put(value, 1);
            }
        }

        int arrowCount = 0;

        arrowCount++;
        int arrowHeight = arr[0];
        hm.put(arrowHeight, hm.get(arrowHeight)-1);
        if(hm.get(arrowHeight) == 0){
            hm.remove(arrowHeight);
        }

        for(int ballonIndex = 1; ballonIndex< arr.length; ballonIndex++){
            while(hm.containsKey(--arrowHeight)){
                hm.put(arrowHeight, hm.get(arrowHeight)-1);
                if(hm.get(arrowHeight) == 0){
                    hm.remove(arrowHeight);
                }
            }
            if(hm.containsKey(arr[ballonIndex])){
                arrowCount++;
                arrowHeight = arr[ballonIndex];
                hm.put(arr[ballonIndex], hm.get(arr[ballonIndex])-1);
                if(hm.get(arr[ballonIndex]) == 0){
                    hm.remove(arr[ballonIndex]);
                }
            }
        }
        return arrowCount;
    }
}