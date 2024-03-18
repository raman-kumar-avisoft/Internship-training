import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class RemoveDuplicatesFromLinkedList {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        int maxWrongTries = 3;
        boolean continueLooping = true;

        LinkedList<Integer> linkedList = new LinkedList();
        System.out.println("Enter the data in the Linked List (-1 if you want to skip)");
        while(continueLooping){
            try{
                System.out.print("Enter the Node Data: ");
                int nodeData = scannerInteger.nextInt();
                if(nodeData == -1){
                    continueLooping = false;
                    continue;
                }
                linkedList.add(nodeData);
            }catch(InputMismatchException inputMismatchException){
                System.out.println("NOT A VALID NUMBER **");
                maxWrongTries--;
            }finally {
                scannerInteger.nextLine();
                if(maxWrongTries == 0){
                    System.out.println("TOO MANY TRIES **");
                    continueLooping = false;
                }
            }
        }
        System.out.println(linkedList);
//        mapping
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        LinkedList<Integer> linkedListResult = new LinkedList();
        for(int value:linkedList){
            if(!hashMap.containsKey(value)){
                linkedListResult.add(value);
                hashMap.put(value,1);
            }
        }
        System.out.println(linkedListResult);
    }
}