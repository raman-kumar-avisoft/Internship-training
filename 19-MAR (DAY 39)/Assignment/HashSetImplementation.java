import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HashSetImplementation {
    static class HashSet{
        private ArrayList<Integer> arrayList = null;
        HashSet(){
            arrayList = new ArrayList<>();
        }
        public void addElement(int value){
            if(contains(value)){
                System.out.println("NOT ADDING THE ELEMENT AS ELEMENT IS ALREADY PRESENT --");
            }else{
                arrayList.add(value);
            }
        }
        boolean contains(int value){
            for(int elm:arrayList){
                if(elm == value){
                    return true;
                }
            }
            return false;
        }
        boolean remove(int value){
            int index = -1;
            for(int arrayListIndex = 0; arrayListIndex < arrayList.size(); arrayListIndex++){
                if(arrayList.get(arrayListIndex) == value){
                    index = arrayListIndex;
                    break;
                }
            }
            if(index != -1){
                arrayList.remove(index);
                return true;
            }else{
                return false;
            }
        }
        void display(){
            Collections.shuffle(arrayList);
            System.out.println("Elements in the hashset are: ");
            for(int elm: arrayList){
                System.out.print(elm+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        HashSet hashSet = new HashSet();
        boolean continueLooping = true;
        int maxWrongTries = 3;
        while(continueLooping){
            try{
                displayMenu();
                System.out.print("Enter the option out of the following: ");
                int choice = scannerInteger.nextInt();

                int element = -1;
                switch (choice){
                    case 1:
                        System.out.print("Enter the element you want to add: ");
                        element = scannerInteger.nextInt();
                        hashSet.addElement(element);
                        break;
                    case 2:
                        System.out.print("Enter the element you want to remove: ");
                        element = scannerInteger.nextInt();
                        hashSet.remove(element);
                        break;
                    case 3:
                        System.out.print("Enter the element you want to check if it is there or not: ");
                        element = scannerInteger.nextInt();
                        if(hashSet.contains(element)){
                            System.out.println("Element is present in the HashSet");
                        }else{
                            System.out.println("Element is absent in the HashSet");
                        }
                        break;
                    case 4:
                        hashSet.display();
                        break;
                    case 5:
                        System.out.println("Thank You --");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("OUT OF RANGE NUMBER --");
                        break;
                }
            }catch(InputMismatchException inputMismatchException){
                System.out.println("NOT A VALID NUMBER --");
                maxWrongTries--;
                scannerInteger.nextLine();
            }finally {
                if(maxWrongTries == 0){
                    System.out.println("TOO MANY WRONG TRIES --");
                    continueLooping = false;
                }
            }
        }
    }
    static void displayMenu(){
        System.out.println("1. Add an element in the Hashset");
        System.out.println("2. Remove an element from the Hashset");
        System.out.println("3. Contains an element in the Hashset");
        System.out.println("4. Display Hashset");
        System.out.println("5. Exit");
    }
}