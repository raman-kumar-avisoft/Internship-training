import java.util.ArrayList;
import java.util.Scanner;

public class OccurenceOfStringInString {
    public static void main(String[] args) {
        Scanner scannerString = new Scanner(System.in);
        System.out.println("Enter the TEXT string: ");
        String text = scannerString.next();

        System.out.println("Enter the PATTERN string: ");
        String pattern = scannerString.next();

        ArrayList<Integer> answer = patternInTextString(text, pattern);
        for(int index: answer){
            System.out.print(index+" ");
        }
    }
    static ArrayList<Integer> patternInTextString(String text, String pattern){
        ArrayList<Integer> answer = new ArrayList<>();
        for(int textIndex=0; textIndex<text.length(); textIndex++){
            if(textIndex+pattern.length() > text.length()){
                break;
            }else if(pattern.equals(text.substring(textIndex,textIndex+pattern.length()))){
                answer.add(textIndex+1);
            }
        }
        if(answer.size() == 0){
            answer.add(-1);
        }
        return answer;
    }
}