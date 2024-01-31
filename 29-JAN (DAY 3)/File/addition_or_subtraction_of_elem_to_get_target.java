import java.util.Scanner;

public class addition_or_subtraction_of_elem_to_get_target {
    static int fact(int num){
        if(num<=0){
            return 1;
        }
        return num*fact(num-1);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the element");
        int len = sc.nextInt();

        System.out.println("Enter the element you want in array");
        int value = sc.nextInt();

        System.out.println("Enter the element you want in get after manipulation");
        int target = sc.nextInt();

        int sum = len*value;
        int minuses = sum-target-value;
//        int minuses = pattern/value;

        int ans = fact(len)/(fact(minuses)*fact(len-minuses));
        System.out.println(ans);
    }
}