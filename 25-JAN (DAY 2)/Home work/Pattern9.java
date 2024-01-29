import java.util.Scanner;

public class Pattern9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the total rows for the following pattern: ");

        int row = sc.nextInt();
        int space = row -1;
        for(int i=0; i<row; i++){
//            for spaces
            for(int j = 0; j<space; j++){
                System.out.print(" ");
            }
//            for first triangular pattern
            for (int j = 0; j<=i; j++){
                System.out.print(i+j+1);
            }

//            for second triangular pattern
            for (int j=0; j<i; j++){
                System.out.print((i*2)-j);
            }
            space--;
            System.out.println();
        }
    }
}