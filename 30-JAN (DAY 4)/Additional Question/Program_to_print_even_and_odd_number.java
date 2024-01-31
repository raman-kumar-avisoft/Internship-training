import java.util.ArrayList;
import java.util.Scanner;

public class Program_to_print_even_and_odd_number {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int len = sc.nextInt();

        int[] arr = new int[len];
//        initialize the array
        System.out.println("Enter the element in an array");
        for (int i=0; i<len; i++){
            arr[i] = sc.nextInt();
        }

        ArrayList<Integer> al = new ArrayList<>();
        ArrayList<Integer> al2 = new ArrayList<>();

        for(int i=0; i<len; i++){
            if(arr[i]%2 == 0){
                al.add(arr[i]);
            }else{
                al2.add(arr[i]);
            }
        }
        System.out.println("Even number in the array are: ");
        for(var i: al){
            System.out.print(i + " ");
        }
        System.out.println("Odd number in the array are: ");
        for(var i: al2){
            System.out.print(i + " ");
        }
    }
}