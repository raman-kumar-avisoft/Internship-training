import java.util.Scanner;

public class CircularGasProblem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        int n = 2;
        System.out.println("Enter the size of array: ");
        int n = sc.nextInt();
        int A[] = new int[n];
        int B[] = new int[n];

        System.out.println("Enter the values in first Array: ");
        for(int i=0; i<n; i++){
            A[i] = sc.nextInt();
        }
        System.out.println("Enter the values in the second Array: ");
        for(int i=0; i<n; i++){
            B[i] = sc.nextInt();
        }

        int ans = -1;
        int tank;
        int i;
        for(i=0; i<n; i++){
            int k=i;
            int j;
            tank = 0;
            for(j=0; j<n; j++){
                tank += A[k%n];
                if(tank<B[k%n]){
                    break;
                }
                tank -= B[k%n];
                k++;
            }
            if(j==n && tank>=0){
                ans = i;
                break;
            }
        }
        if(ans == -1){
            System.out.println("No way found for circular path");
        }else{
            System.out.println("Circular path found at: "+i);
        }

    }
}