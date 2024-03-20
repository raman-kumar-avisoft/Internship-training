import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SumOfFourElementMakesTarget {
    static class Pair {

        int index1;
        int index2;

        // Constructor
        Pair(int x, int y)
        {
            index1 = x;
            index2 = y;
        }
    }
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
        boolean answer = solve(arrayList, K);
        System.out.println("ANSWER FOUND: " + answer);
    }
    static boolean solve(ArrayList<Integer> arrayList, int K){                                                                 // O(N^2) * log(N)
        HashMap<Integer, ArrayList<Pair> > hm = new HashMap<>();

//        Stores all the quadruple in this hashmap with sum as primary key.
        for(int i=0; i<arrayList.size(); i++){
            for(int j = i+1; j<arrayList.size(); j++){
                int sum = arrayList.get(i) + arrayList.get(j);
                if(hm.containsKey(sum)){
                    ArrayList<Pair> pairArrayList = hm.get(sum);
                    Pair pair = new Pair(arrayList.get(i), arrayList.get(j));
                    pairArrayList.add(pair);
                    hm.put(sum,pairArrayList);
                }else{
                    Pair pair = new Pair(arrayList.get(i), arrayList.get(j));
                    ArrayList<Pair> pairArrayList = new ArrayList<>();
                    pairArrayList.add(pair);
                    hm.put(sum, pairArrayList);
                }
            }
        }

        for(int i=0; i<arrayList.size(); i++){
            for(int j = i+1; j<arrayList.size(); j++){
                int sum = K - arrayList.get(i) + arrayList.get(j);
                if(hm.containsKey(sum)){
                    ArrayList<Pair> pairArrayList = hm.get(sum);
                    for(var pair: pairArrayList){
                        if(pair.index1 != i && pair.index2 != j && pair.index1 != i && pair.index2 != j){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}