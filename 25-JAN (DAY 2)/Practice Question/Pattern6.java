import java.util.Scanner;

public class Pattern6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the rows: ");
        int row  = sc.nextInt();
        for (int i=0; i<row; i++) {
//                for spaces
            for (int k = 0; k <= i; k++) {
                System.out.print(" ");
            }
            for (int j = 0; j < row; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}