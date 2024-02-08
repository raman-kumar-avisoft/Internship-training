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
        String inpStr = "jammukashmirjammu";
        String[] wordsList = new String[]{"jammu", "kashmir"};

        boolean ans = solve(inpStr, wordsList, 0);
        System.out.println("Answer is: "+ans);
    }
}