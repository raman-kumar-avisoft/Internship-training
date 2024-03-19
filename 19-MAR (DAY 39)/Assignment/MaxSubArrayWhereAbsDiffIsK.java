import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MaxSubArrayWhereAbsDiffIsK {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        ArrayList<Integer> arrayList = new ArrayList<>();

        System.out.println("Enter the element in the arrayList (-1 to skip)");
        int maxWrongTries = 3;
        boolean continueLooping = true;

        while(continueLooping){
            try{
                System.out.print("Enter the element: ");
                int value = scannerInteger.nextInt();
                if(value == -1){
                    break;
                }
                arrayList.add(value);
            }catch(InputMismatchException inputMismatchException){
                scannerInteger.nextLine();
                System.out.println("NOT A VALID NUMBER --");
            }finally {
                if(maxWrongTries == 0){
                    System.out.println("TOO MANY WRONG TRIES --");
                    continueLooping = false;
                }
            }
        }

        continueLooping = true;
        maxWrongTries = 3;

        int K = -1;
        while(continueLooping){
            try{
                System.out.print("Enter the value of K: ");
                K = scannerInteger.nextInt();
                break;
            }catch(InputMismatchException inputMismatchException){
                System.out.println("NOT A VALID INPUT --");
                maxWrongTries--;
                K = -1;
            }finally {
                if(maxWrongTries == 0){
                    System.out.println("TOO MANY WRONG TRIES --");
                    continueLooping = false;
                }
            }
        }
        if(K != -1){
            int answer = maxSubArrayDistance(arrayList, K);
            if(answer == -1){
                System.out.println("NO SUB ARRAY WITH DIFFERENCE "+K+" IS FOUND IN THE ARRAYLIST --");
            }else{
                System.out.println("THE DIFFERENCE IS: " + answer);
            }
        }else{
            System.out.println("NOT A VALID K VALUE --");
        }
    }
    static int maxSubArrayDistance(ArrayList<Integer> arrayList, int K){
        Collections.sort(arrayList);
        int i = 0;
        int j = arrayList.size()-1;
        while(i<j){
            if(arrayList.get(i)+arrayList.get(j) == K){
                return j-i+1;
            }else if(arrayList.get(i)+arrayList.get(j) < K){
                i++;
            }else{
                j--;
            }
        }
        return -1;
    }
}