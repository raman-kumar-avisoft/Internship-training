import java.util.Scanner;

public class Pattern12 {
    public static void main(String[] args) {
        System.out.println("Enter the number of rows: ");
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        for(int i=0; i<row; i++){
            for (int j=0; j<row; j++){
                if (j==row-i-1){
                    System.out.print("*");
                }else {
                    System.out.print(row-j);
                }
            }
            System.out.println();
        }
    }
}