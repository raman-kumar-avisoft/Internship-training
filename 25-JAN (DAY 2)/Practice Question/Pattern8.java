import java.util.Scanner;

public class Pattern8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the rows: ");
        int row  = sc.nextInt();

        int count = 1;
        for (int i=1; i<=row; i++){
            if(count>7){
                count = 1;
            }
            int count2 = count;
            for(int j=1; j<=row; j++){
                if(count2>7){
                    count2 = 1;
                }
                System.out.print(count2);
                count2+=2;
            }
            System.out.println();
            count += 2;
        }
    }
}