import java.util.Scanner;

public class Pattern3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the rows: ");
        int row = sc.nextInt();
        char ch = 'A';
        for(int i=0; i<row; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.printf("%c", ch + i);
            }
            System.out.println();
        }
    }
}