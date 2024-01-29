import java.util.Scanner;

public class Pattern10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter odd number of rows");
        int od = sc.nextInt();
        int divided_row = od / 2;
        int spaces = divided_row;
//        for upper triangle
        for(int i=1 ; i<=divided_row; i++){
//            for spaces
            for(int j=0; j<spaces; j++){
                System.out.print(" ");
            }

//            for pattern
            for(int j=1; j<i*2; j++){
                System.out.print("*");
            }

            System.out.println();
            spaces--;
        }
//        main line
        for(int i=1; i<=od; i++){
            System.out.print("*");
        }
        System.out.println();
//        for lower triangle
        for(int i=1 ; i<=divided_row; i++){
            spaces++;
//            for spaces
            for(int j=0; j<spaces; j++){
                System.out.print(" ");
            }

//            for pattern
            for(int j=0; j<=((divided_row-i)*2); j++){
                System.out.print("*");
            }

            System.out.println();
//            spaces++;
        }
    }
}