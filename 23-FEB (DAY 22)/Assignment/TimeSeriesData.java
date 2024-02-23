import java.util.InputMismatchException;
import java.util.Scanner;

public class TimeSeriesData {
    public static void main(String[] args) {

//        TAKING INPUT VALUE OF ALL VALUES:
        Scanner sc = new Scanner(System.in);
        int sz = -1;
        int count = 3;
        while(count>0){
            try{
                System.out.println("Enter the size: ");
                sz = sc.nextInt();
                count = -1;
            }catch (InputMismatchException e){
                System.out.println("Enter a valid input value !!");
            }finally {
                sc.nextLine();
                count--;
                if(count == 0){
                    System.out.println("Too Many Tries !!");
                    System.exit(0);
                }
            }
        }

        System.out.println("Enter the input vector: ");
        String str = "";
        for(int i=0; i<sz; i++){
            try{
                char ch = sc.nextLine().charAt(0);
                if(ch >=48 && ch <= 57){
                    str+= ch;
                }else{
                    throw new Exception("Not a number !!");
                }
            }catch(Exception e){
                i--;
                System.out.println("Enter a valid number");
            }
        }
        System.out.println(str);

        int windowSize = -1;
        count = 3;
        while(count>0){
            try{
                System.out.print("Enter the window size: ");
                windowSize = sc.nextInt();
                if(windowSize<0 || windowSize>sz){
                    throw new Exception("Between the size of input value");
                }
                count = -1;
            }catch (Exception e){
                System.out.println("Enter a valid number");
            }finally{
                sc.nextLine();
                count--;
                if(count == 0){
                    System.out.println("Too many tries");
                    System.exit(0);
                }
            }
        }

//        passing these values to the function to calculate output vector:
        String output_vector = calc_mov_avg(str, windowSize);
        System.out.println("Answer is : " + output_vector + " with size: " + output_vector.length());
    }
    static String calc_mov_avg(String str, int windowSize){
        if(windowSize == 0){
            return "";
        }
        int s = windowSize;
        String outputVector = "";
        int sum = 0;
        boolean first = true;
        for(int i=0; i<str.length(); i++){
            int value = (int)str.charAt(i) - 48;
            if(windowSize>1){
                sum += value;
                windowSize--;
            }else{
                if(first){
                    sum += value;
                    outputVector += sum/s;
                    first = false;
                }else{
                    sum += value;
                    int value2 = (int)str.charAt(i-s) - 48;
                    sum -= value2;
                    outputVector += sum/s;
                }
            }
        }
        return outputVector;
    }
}