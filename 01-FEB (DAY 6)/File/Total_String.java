import java.util.Scanner;

public class Total_String {
    static void dis(char[] arr){
        for (int i=0; i<arr.length; i++){
            System.out.print(arr[i]);
        }
        System.out.println();
    }
    static void emty(char[] arr, int index){
        for(int i=index; i<arr.length; i++){
            arr[i] = 'z';
        }
    }

    static int solve(char[] arr, int b, int c, int index){
//        base condition
        if(b<0 || b>1){
            return 0;
        }else if(c<0 || c>2){
            return 0;
        }else if(arr[0] != 'z' && arr[1] != 'z' && arr[2] != 'z'){
            dis(arr);
            return 1;
        }

        int count = 0;
        arr[index] = 'a';
        count += solve(arr, b, c, index+1);
        emty(arr,index+1);

        arr[index] = 'b';
        count += solve(arr, b+1, c, index+1);
        emty(arr,index+1);

        arr[index] = 'c';
        count += solve(arr, b, c+1, index+1);
        emty(arr,index+1);

        return count;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the string: ");
        int n = sc.nextInt();
        char[] arr = new char[n];
        for(int i=0; i<3; i++){
            arr[i] = 'z';
        }
        int ans = solve(arr, 0, 0, 0);
        System.out.println("Answer is: " + ans);
    }
}