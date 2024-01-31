import java.util.Scanner;

public class Find_unique_elem_among_double_element {
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

//        traversing bitwise array/list
        int sol=0;
        for(int i=0; i<len; i++){
            sol ^= arr[i];
        }
        System.out.println("ANSWER IS: "+sol);
    }
}