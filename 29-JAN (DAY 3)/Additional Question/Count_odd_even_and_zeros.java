import java.util.ArrayList;
import java.util.Scanner;

public class Count_odd_even_and_zeros {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the ArrayList");
        int si = sc.nextInt();


        System.out.println("Enter the elements in the array: ");
        for (int i=0; i<si; i++){
            arr.add(sc.nextInt());
        }

        int zero = 0, odd = 0, even =0;
        for(int i=0; i<arr.size(); i++){
            if(arr.get(i) == 0){
                zero++;
            }
            else if(arr.get(i)%2==0){
                even++;
            }else{
                odd++;
            }
        }
        System.out.println("The number of odd, even and zeroes are: " + odd + " " + even + " " + zero);
    }
}