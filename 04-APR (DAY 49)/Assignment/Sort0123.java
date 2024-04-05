import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Sort0123 {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        System.out.println("Enter the objects in the array Where red is 0, white is 1 and blue is 2");

        ArrayList<Integer> arrayListObjects = new ArrayList<>();
        boolean condition = true;
        while(condition){
            int maxWrongTries = 3;
            while(--maxWrongTries >=0 ){
                try{
                    System.out.print("Enter the object: (-1 to finish): ");
                    int value = scannerInteger.nextInt();
                    if(value == -1){
                        condition = false;
                        break;
                    }
                    if((value != 0) && (value != 1) && (value != 2)){
                        throw new InputMismatchException();
                    }
                    arrayListObjects.add(value);
                    break;
                }catch (InputMismatchException inputMismatchException){
                    System.out.println("NOT A VALID Object");
                }
            }
            if(maxWrongTries < 0){
                System.out.println("TOO MANY WRONG TRIES **");
                System.exit(0);
            }
        }
        display(arrayListObjects);
        System.out.println();
        arrangeObjects(arrayListObjects);
        display(arrayListObjects);
    }
    static void display(ArrayList<Integer> arrayList){
        for(Integer value: arrayList){
            System.out.print(value+" ");
        }
    }
    static void arrangeObjects(ArrayList<Integer> arrayListObject){
//           we will use two pointer approach to solve this problem
        int index0 = 0;
        int index2 = arrayListObject.size()-1;

        for(int arrayListObjectIndex = 0; arrayListObjectIndex < index2; arrayListObjectIndex++){
            if(arrayListObject.get(arrayListObjectIndex) == 0){
                int temp = arrayListObject.get(index0);
                arrayListObject.set(index0, 0);
                arrayListObject.set(arrayListObjectIndex, temp);
                index0++;
            }else if(arrayListObject.get(arrayListObjectIndex) == 2){
                int temp = arrayListObject.get(index2);
                arrayListObject.set(index2, 2);
                arrayListObject.set(arrayListObjectIndex, temp);
                index2--;
            }
        }
    }
}