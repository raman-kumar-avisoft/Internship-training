import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ElementPresentInEachRow {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        int maxWrongTries = 3;
        int row = -1, col = -1;
        while(maxWrongTries-- > 0){
            try{
                System.out.println("Enter the number of rows: ");
                row = scannerInteger.nextInt();
                if(row <= 0){
                    throw new InputMismatchException();
                }
                break;
            }catch (InputMismatchException inputMismatchException){
                row = -1;
                scannerInteger.nextLine();
            }
        }
        maxWrongTries = 3;
        while(maxWrongTries-- > 0){
            try{
                System.out.println("Enter the number of cols: ");
                col = scannerInteger.nextInt();
                if(row <= 0){
                    throw new InputMismatchException();
                }
                break;
            }catch (InputMismatchException inputMismatchException){
                col = -1;
                scannerInteger.nextLine();
            }
        }

        if(row == -1 || col == -1){
            System.out.println("NOT VALID ROWS AND COLUMNS **");
        }else{
            ArrayList<ArrayList<Integer>> mat = new ArrayList<>();
            for(int rowIndex = 0; rowIndex < row; rowIndex++){
                ArrayList<Integer> rowData = new ArrayList<Integer>();
                for(int colIndex = 0; colIndex < col; colIndex++){
                    int value = -1;
                    maxWrongTries = 2;
                    while(maxWrongTries-- > 0){
                        try{
                            System.out.print("Enter the value for "+rowIndex+" row and "+colIndex+" col: ");
                            value = scannerInteger.nextInt();
                            rowData.add(value);
                            break;
                        }catch(InputMismatchException inputMismatchException){
                            value = -1;
                            scannerInteger.nextLine();
                        }
                    }
                    if(maxWrongTries == 0){
                        System.out.println("TOO MANY WRONG TRIES **");
                        System.exit(0);
                    }
                }
                mat.add(rowData);
            }
            ArrayList<Integer> answer = commonInRows(mat);
            System.out.print("Duplicate values in Matrix are: ");
            for(int value: answer){
                System.out.print(value+" ");
            }
        }
    }
    static ArrayList<Integer> commonInRows(ArrayList<ArrayList<Integer>> mat){
            HashMap<Integer, Integer> hm = new HashMap<>();
            for(int rowIndex = 0; rowIndex < mat.size(); rowIndex++){
                HashMap<Integer, Integer> hm2 = new HashMap<>();

                for(int colIndex = 0; colIndex < mat.get(rowIndex).size(); colIndex++){
                    hm2.put(mat.get(rowIndex).get(colIndex), 1);
                }
                for(int value: hm2.keySet()){
                    if(hm.containsKey(value)){
                        hm.put(value, hm.get(value)+1);
                    }else{
                        hm.put(value, 1);
                    }
                }
            }

            ArrayList<Integer> answer = new ArrayList<>();
            for(int value: hm.keySet()){
                if(hm.get(value) == mat.size()){
                    answer.add(value);
                }
            }
            if(answer.size() == 0){
                answer.add(-1);
            }
            return answer;
    }
}