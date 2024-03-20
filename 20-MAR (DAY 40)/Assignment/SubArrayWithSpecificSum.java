import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SubArrayWithSpecificSum {
    public static void main(String[] args) {
        Scanner scannerString = new Scanner(System.in);
        ArrayList<Integer> arrayList = new ArrayList<>();
        int maxWrongTries = 3;
        while(true){
            try{
                System.out.print("Enter the element (-1 to skip): ");
                int value = scannerString.nextInt();
                if(value == -1){
                    break;
                }
                arrayList.add(value);
            }catch(InputMismatchException inputMismatchException){
                maxWrongTries--;
                if(maxWrongTries == 0){
                    System.out.println("TOO MANY WRONG INPUT **");
                    break;
                }
            }
        }
        int K=-1;
        maxWrongTries = 3;
        while(true){
            try{
                System.out.print("Enter the value of K: ");
                K = scannerString.nextInt();
                if(K>=0){
                    break;
                }else{
                    throw new InputMismatchException();
                }
            }catch(InputMismatchException inputMismatchException){
                maxWrongTries--;
                System.out.println("NOT A VALID INPUT **");
                if(maxWrongTries == 0){
                    System.out.println("TOO MANY WRONG INPUT **");
                    break;
                }
            }
        }
        int count = 0;
        for(int arrayListFirstIndex = 0; arrayListFirstIndex<arrayList.size(); arrayListFirstIndex++){
            int sum = arrayList.get(arrayListFirstIndex);
            for(int arrayListLastIndex = arrayListFirstIndex+1; arrayListLastIndex<arrayList.size(); arrayListLastIndex++){
                sum += arrayList.get(arrayListLastIndex);
                if(sum == K){
                    count++;
                }
            }
        }

        System.out.println("ANSWER IS: " + count);
    }
}