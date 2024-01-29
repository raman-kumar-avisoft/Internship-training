import java.util.Scanner;

public class Pattern7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the rows: ");
        int row  = sc.nextInt();

        for (int i=0; i<=row; i++) {
//            for spaces
          for (int j = i; j < row+1; j++) {
              System.out.print(" ");
          }
//            for pattern
          for (int k = 1; k <= i + 1; k++) {
              System.out.print(k);
          }
          System.out.println();
        }
    }
}