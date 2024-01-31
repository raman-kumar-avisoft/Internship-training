import java.util.Scanner;

public class find_single_element_from_an_array_of_triplets {
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

//        displaying
        int[] bitwise = new int[16];
        for(int i=0; i<len; i++){
            int num = arr[i];
            int j=0;
            while(num>0){
                if((num&1) == 1){
                    bitwise[j] += 1;
                }
                num = num>>1;
                j++;
            }
        }

//        traversing bitwise array/list
        int sol=0;
        for(int i=0; i<16; i++){
            if(bitwise[i] % 3 != 0){
                sol += Math.pow(2,i);
            }
        }
        System.out.println("ANSWER IS: "+sol);
    }
}