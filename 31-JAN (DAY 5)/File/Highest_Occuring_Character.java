import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Highest_Occuring_Character {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string: ");
        String str = sc.nextLine();

        HashMap<Character, Integer> hm = new HashMap<>();
        for(int i=0; i<str.length(); i++){
            if(hm.containsKey(str.charAt(i))){
                hm.put(str.charAt(i), hm.get(str.charAt(i))+1);
            }else{
                hm.put(str.charAt(i), 1);
            }
        }
        int occ = 0;
        char ch = ' ';
        for(Map.Entry<Character, Integer> i : hm.entrySet()){
            if(occ < i.getValue()){
                occ = i.getValue();
                ch = i.getKey();
            }
        }
        if(ch == ' '){
            System.out.println("Blank String");
        }else{
            System.out.println("Maximum occurrence of char: "+ ch+ " with occurrence: "+occ);
        }
    }
}