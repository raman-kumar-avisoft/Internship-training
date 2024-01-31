import java.util.Arrays;
import java.util.Scanner;

public class Second_min_and_max_elem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array");
        int len = sc.nextInt();

        int[] arr = new int[len];
        System.out.println("Insert the elem in array");
        for(int i=0; i<len; i++){
            arr[i] = sc.nextInt();
        }

//        easy way may have higher complexity
        Arrays.sort(arr);
        int sec_lar = (len-2)>0?arr[len-2]:-1;
        int sec_sma = len>=2?arr[1]:-1;
        System.out.println("The second largest and smallest is: "+sec_lar+" "+sec_sma);

//        optimal for second min and max
//        int first_sm = Integer.MAX_VALUE, second_sm=Integer.MAX_VALUE;
//        int first_la = Integer.MIN_VALUE, second_la=Integer.MIN_VALUE;
//        for(int i=0; i<len; i++){
//            if(i==0){
//                first_sm = arr[i];
//                first_la = arr[i];
//                continue;
//            }
//            if(arr[i]>=first_la){
//                second_la = first_la;
//                first_la = arr[i];
//            }else if(arr[i]<=first_la && arr[i]>=second_la){
//                second_la = arr[i];
//            }
//
//            if(arr[i]<=first_sm){
//                second_sm = first_sm;
//                first_sm = arr[i];
//            }else if(arr[i]>=first_sm && arr[i]<=second_sm){
//                second_sm = arr[i];
//            }
//        }
//        if(second_sm == Integer.MAX_VALUE && second_la == Integer.MIN_VALUE){
//            System.out.println("No second smallest and largest elem found");
//        }
//        else{
//            System.out.println("Second smallest and largest elem is: "+second_sm +" "+second_la);
//        }
    }
}