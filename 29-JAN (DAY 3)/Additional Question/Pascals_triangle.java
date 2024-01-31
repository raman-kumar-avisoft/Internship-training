import java.util.ArrayList;
import java.util.Scanner;

public class Pascals_triangle {
    public static void main(String[] args) {
        System.out.println("Enter the number of rows you want to print in the pattern");
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();

        ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>(row);
        for (int i=0; i<row; i++){
            ArrayList<Integer> arr2 = new ArrayList<>(i+1);
            for (int j=0; j<=i; j++){
                int first , second;
                if ((i-1) < 0){
                    first = 0;
                    second = 1;
                }else if(j-1<0){
                    first = 0;
                    second = arr.get(i-1).get(j);
                }else if(j==i){
                    first = arr.get(i-1).get(j-1);
                    second = 0;
                }else{
                    first = arr.get(i-1).get(j-1);
                    second = arr.get(i-1).get(j);
                }
                arr2.add(first+second);
            }
            arr.add(arr2);
        }

        for(int i=0; i<arr.size(); i++){
            for(int j=0; j<arr.get(i).size(); j++){
                System.out.print(arr.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}