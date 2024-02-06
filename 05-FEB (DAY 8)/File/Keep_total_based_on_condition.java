import java.util.Scanner;

public class Keep_total_based_on_condition {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int len = sc.nextInt();

//        add 1 point for every even number in the array
//        add 3 point for every odd number except 5
//        add 5 point if we encounter 5

        int[] arr = new int[len];
        System.out.println("Enter the value in the array: ");
        for(int i=0; i<len; i++){
            arr[i] = sc.nextInt();
        }

        int total = 0;
        for(int i=0; i<len; i++){
            if(arr[i]%2 == 0){
                total += 1;
            }
            else{
                if(arr[i] == 5){
                    total += 5;
                }else{
                    total += 3;
                }
            }
        }
        System.out.println("Answer is: "+ total);
    }
}