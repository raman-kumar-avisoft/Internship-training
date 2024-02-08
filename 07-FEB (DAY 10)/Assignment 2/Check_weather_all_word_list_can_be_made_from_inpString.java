import java.util.Scanner;

public class Check_weather_all_word_list_can_be_made_from_inpString {

    static boolean solve(String str, String[] inp, int i){
        if(i == inp.length){
            return true;
        }

        if(str.contains(inp[i])){
            str.replaceFirst(inp[i], "");
        }else{
            return false;
        }
        return solve(str,inp,++i);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the input String: ");
        String inpStr = sc.next();

        System.out.println("Enter the size of the wordList");
        int len = sc.nextInt();

        String[] arr = new String[len];
        System.out.println("Enter the words in wordList");
        for(int i=0; i<len; i++){
            arr[i] = sc.next();
            sc.nextLine();
        }

        boolean ans = solve(inpStr, arr, 0);
        System.out.println("Answer is: "+ans);
    }
}
