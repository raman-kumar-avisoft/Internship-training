import java.util.HashMap;
import java.util.Scanner;

public class Check_Permutation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first String: ");
        String str1 = sc.nextLine();
        char[] ch = new char[str1.length()];
        ch = str1.toCharArray();

        System.out.println("Enter the second String: ");
        String str2 = sc.nextLine();
        char[] ch2 = new char[str2.length()];
        ch2 = str2.toCharArray();

        if(str1.length() != str2.length()){
            System.out.println("Unsuccessful Operation");
        }else{
            HashMap<Character, Integer> m = new HashMap<>();
            for(int i=0; i<str1.length(); i++){
                if(m.containsKey(ch[i])){
                    m.put(ch[i],m.get(ch[i])+1);
                }else{
                    m.put(ch[i],1);
                }
            }
            boolean per = true;
            for (int i=0; i<str1.length(); i++){
                if(m.containsKey(ch2[i])){
                    m.put(ch[i],m.get(ch[i])-1);
                }else{
                    per = false;
                }
            }
            if(per){
                System.out.println("permutation");
            }else{
                System.out.println("not permutation");
            }

        }
    }
}