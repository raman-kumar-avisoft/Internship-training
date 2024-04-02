import java.util.InputMismatchException;
import java.util.Scanner;

public class MinimumMultiplication {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        int maxWrongTries = 3;
        int matrices = -1;
        while(--maxWrongTries >= 0){
            try {
                System.out.print("Enter the number of matrices: ");
                matrices = scannerInteger.nextInt();
                if(matrices <= 1){
                    throw new InputMismatchException();
                }
                break;
            }catch (InputMismatchException inputMismatchException){
                scannerInteger.nextLine();
                System.out.println("NOT A VALID MATRICES SIZE **");
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }

        int[] matricesDimension = new int[matrices];
        System.out.println("INSERT THE DIMENSTIONS OF N-1 MATRICES");
        for(int matricesDimensionIndex = 0; matricesDimensionIndex < matrices; matricesDimensionIndex++){
            maxWrongTries = 3;
            while(--maxWrongTries >= 0){
                try{
                    matricesDimension[matricesDimensionIndex] = scannerInteger.nextInt();
                    if(matricesDimension[matricesDimensionIndex] <= 0){
                        throw new InputMismatchException();
                    }
                    break;
                }catch (InputMismatchException inputMismatchException){
                    System.out.println("NOT A VALID DIMENSION");
                }
            }
            if(maxWrongTries < 0){
                System.out.println("TOO MANY WRONG TRIES **");
                System.exit(0);
            }
        }
        int answer = matrixMultiplication(matrices, matricesDimension);
    }
    static int solve (int i, int j, int matricesDimesion[]){
        if(j<=i){
            return 0;
        }
        int ans= Integer.MAX_VALUE;
        for(int k=i;k<j;k++){
            int temp=matricesDimesion[i-1]*matricesDimesion[k]*matricesDimesion[j]+solve(i,k,matricesDimesion)+solve(k+1,j,matricesDimesion);
            ans= Math.min(ans,temp);
        }
        return ans;
    }
    static int matrixMultiplication(int matrices, int matricesDimension[])
    {
        return solve(1,matrices-1,matricesDimension);
    }
}