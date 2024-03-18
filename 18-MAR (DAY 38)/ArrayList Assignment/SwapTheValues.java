import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SwapTheValues {
    public static void main(String[] args) {
        Scanner scannerString = new Scanner(System.in);

        ArrayList<String> arrayList = new ArrayList<>();
        System.out.println("Enter the element in the ArrayList (-1 to finish Inserting)");
        int maxWrongTries = 3;
        boolean continueLooping = true;
        while(continueLooping){
            try{
                System.out.print("Enter the element: ");
                String value = scannerString.next();
                if(value.equals("-1")){
                    continueLooping = false;
                    continue;
                }
                arrayList.add(value);
            } catch (InputMismatchException inputMismatchException){
                maxWrongTries--;
            } finally {
                scannerString.nextLine();
                if(maxWrongTries == 0){
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }

        if(arrayList.size() > 1){
            maxWrongTries = 3;
            continueLooping = true;
            int index = -1;
            while(continueLooping){
                try{
                    System.out.println("Enter the index of the arrayList you want to replace the ");
                    index = scannerString.nextInt();
                    if(index > 0 && index < arrayList.size()){
                        String secondValue = arrayList.get(1);
                        arrayList.set(1,arrayList.get(index));
                        arrayList.set(index, secondValue);
                        displayArrayList(arrayList);
                        continueLooping = false;
                    }else{
                        throw new InputMismatchException();
                    }
                } catch(InputMismatchException inputMismatchException){
                    maxWrongTries--;
                } finally {
                    if(maxWrongTries == 0){
                        System.out.println("TOO MANY WRONG TRIES **");
                        continueLooping = false;
                    }
                }
            }
        }else{
            System.out.println("CANNOT SWAP THE SECOND ELEMENT AS THERE IS NO SECOND ELEMENT **");
        }

    }
    static void displayArrayList(ArrayList<String> arrayList){
        System.out.println("Elements in the Array: ");
        for(String value: arrayList){
            System.out.print(value+" ");
        }
    }
}