import java.util.Scanner;

public class Pattern2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the rows: ");
        int row  = sc.nextInt();
        for (int i=1; i<=row; i++){
            for (int j=i; j>0; j--){
                System.out.print(j);
            }
            System.out.println();
        }
    }
}