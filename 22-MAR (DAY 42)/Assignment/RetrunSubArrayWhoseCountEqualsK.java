import java.util.InputMismatchException;
import java.util.Scanner;

public class RetrunSubArrayWhoseCountEqualsK {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        int maxWrongTries = 3;
        int sz = -1;
        while(maxWrongTries-- > 0){
            try{
                System.out.print("Enter the size of the array: ");
                sz = scannerInteger.nextInt();
                if(sz<=0){
                    throw new InputMismatchException();
                }
                break;
            }catch (InputMismatchException inputMismatchException){
                sz = -1;
                scannerInteger.nextInt();
            }
        }
        if(sz == -1){
            System.out.println("TOO MANY WRONG TRIES**");
            System.exit(0);
        }
        int[] arr = new int[sz];
        maxWrongTries = 3;
        for(int arrIndex = 0; arrIndex < arr.length; arrIndex++){
            try{
                System.out.print("Enter the element: ");
                arr[arrIndex] = scannerInteger.nextInt();

            } catch(InputMismatchException inputMismatchException){
                scannerInteger.nextLine();
                maxWrongTries--;
                arrIndex--;
                if(maxWrongTries == 0){
                    System.out.println("TOO MANY TRIES**");
                    break;
                }
            }
        }

        maxWrongTries = 3;
        int K = -1;
        while(maxWrongTries-- > 0){
            try{
                System.out.print("Enter the value of K: ");
                K = scannerInteger.nextInt();
                if(K <= 0){
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException inputMismatchException){
                scannerInteger.nextLine();
            }
        }
        if(maxWrongTries == 0){
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }
        displayArray(arr);

        int answer = subArrayCount(arr, K);
        System.out.println("\nANSWER IS: " + answer);
    }
    static void displayArray(int[] arr){
        System.out.print("Elements in the array: ");
        for(int value: arr){
            System.out.print(value+" ");
        }
    }
    static int subArrayCount(int[] arr, int k){
        int n = arr.length;
        int[] countPrefixSum = new int[n + 1];
        countPrefixSum[0] = 1; // To handle the case when count1s - k = 0

        int count1s = 0;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            count1s += arr[i];

            if (count1s >= k) {
                ans += countPrefixSum[count1s - k];
            }

            countPrefixSum[count1s]++;
        }

        return ans;
    }
}