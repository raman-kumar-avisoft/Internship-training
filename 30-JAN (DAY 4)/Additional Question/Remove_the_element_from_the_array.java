import java.util.Scanner;

public class Remove_the_element_from_the_array {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array");
        int len = sc.nextInt();

        int[] arr = new int[len];
        System.out.println("Insert the elem in array");
        for(int i=0; i<len; i++){
            arr[i] = sc.nextInt();
        }

        System.out.println("Enter the element you want to remove from the array");
        int elm = sc.nextInt();
        int index = -1;
        for (int i=0; i<len; i++){
            if(arr[i]==elm){
                index = i;
                break;
            }
        }
        for(int i=index; i<len-1; i++){
            arr[i] = arr[i+1];
        }

        int end = index==-1?len:len-1;
        System.out.println("After removing the array answer is: ");
        for (int i=0; i<end; i++){
            System.out.print(arr[i]+" ");
        }
    }
}