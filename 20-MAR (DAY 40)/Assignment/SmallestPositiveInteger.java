import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SmallestPositiveInteger {
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

        int[] arr = new int[arrayList.size()+1];

        for(int arrIndex = 0; arrIndex< arrayList.size(); arrIndex++){
            arr[arrIndex] = 0;
        }

        for(int elm: arrayList){
            if(elm>0){
                arr[elm] = 1;
            }
        }

        for(int arrIndex=0; arrIndex<arr.length; arrIndex++){
            if(arr[arrIndex] != 1){
                System.out.println("LOWEST ANSWER IS: " + arrIndex);
                break;
            }
        }
    }
}