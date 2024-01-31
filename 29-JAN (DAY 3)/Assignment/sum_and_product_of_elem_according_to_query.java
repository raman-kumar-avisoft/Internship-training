import java.util.Scanner;

public class sum_and_product_of_elem_according_to_query {
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

        System.out.println("Enter your query: ");
        int query = sc.nextInt();

        if(query == 1){
//            sum code
            int sum = 0;
            for (int i=0; i<len; i++){
                sum+=arr[i];
            }
            System.out.println("Answer is: "+sum);
        }else if(query == 2){
//            product code
            int product = 1;
            for(int i=0; i<len; i++){
                product *= arr[i];
            }
            System.out.println("Answer is: "+product);
        }else{
            System.out.println("Wrong query selected!!");
        }

    }
}