import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TotalPairWIthKSum {
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
        int count = 0;
        for(int elm: arrayList){
            if(arrayList.contains(elm+K) || arrayList.contains(elm-K)){
                count++;
            }
        }
        return count/2;
    }
}