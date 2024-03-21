import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class KTimes {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        int maxWrongTries = 3;
        boolean continueLooping = true;

        ArrayList<Integer> arrayList = new ArrayList<>();
        System.out.println("Enter the elements in the arrayList -");
        while(continueLooping){
            try{
                System.out.print("Enter the element (-1 to skip): ");
                int value = scannerInteger.nextInt();
                if(value == -1){
                    break;
                }
                arrayList.add(value);
            }catch (InputMismatchException inputMismatchException){
                scannerInteger.nextLine();
                maxWrongTries--;
                if(maxWrongTries == 0){
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }

        maxWrongTries = 3;
        continueLooping = true;
        int K = -1;
        while(continueLooping){
            try{
                System.out.print("Enter the value of K: ");
                K = scannerInteger.nextInt();
                if(K<=0){
                    throw new InputMismatchException();
                }
                continueLooping = false;
            }catch (InputMismatchException inputMismatchException){
                K = -1;
                scannerInteger.nextLine();
                maxWrongTries--;
                if(maxWrongTries == 0){
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }

        if(K == -1 || K > arrayList.size()){
            System.out.println("WRONG INPUTTED VALUE **");
        }else{
            ArrayList<Integer> answer = times(arrayList, arrayList.size()/K);
            System.out.print("ANSWER IS: ");
            for(int value: answer){
                System.out.print(value+" ");
            }
        }
    }

    static ArrayList<Integer> times(ArrayList<Integer> arrayList, int repeated){
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int value: arrayList){
            if(hm.containsKey(value)){
                hm.put(value,hm.get(value)+1);
            }else{
                hm.put(value, 1);
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();
        for(int value: hm.keySet()){
            if(hm.get(value) >= repeated){
                answer.add(value);
            }
        }
        if(answer.size() == 0){
            answer.add(-1);
        }
        return answer;
    }
}