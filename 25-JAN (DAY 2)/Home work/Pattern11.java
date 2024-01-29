import java.util.Scanner;

public class Pattern11 {
    public static void main(String[] args) {
        System.out.println("Enter the number of rows");
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String ans = "";
        int nn=0;
        for(int i=1; i<=n; i++){
//            int j = i;
            if(i==1){
                ans += Integer.toString(i);
            }else{
                ans +=  '+' + Integer.toString(i);
            }

            nn += i;
            System.out.println( ans + '=' + nn );
        }
    }
}