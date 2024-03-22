import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class AnagramsTogether {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the length of array of string");
        int len = sc.nextInt();

        String[] arr = new String[len];
        System.out.println("Enter all the Strings: ");
        for(int i=0; i<len; i++){
            arr[i] = sc.next();
        }

        System.out.println("Elements entered in Array of String: ");
        for(int i=0; i<len; i++) {
            System.out.print(arr[i]+ " ");
        }


//        Creating a HashSet so that no duplicate value of anagram is repeated in hashset of hashmap (mapping all the string with chars and their counts)
        HashSet<HashMap<Character, Integer>> ans2 = new HashSet<HashMap<Character, Integer>>();
        for(int i=0; i<len; i++) {
            String word = arr[i];
            HashMap<Character, Integer> mp = new HashMap<Character, Integer>();
            for (int j = 0; j < word.length(); j++) {
                if (mp.containsKey(arr[i].charAt(j))) {
                    mp.put(arr[i].charAt(j), mp.get(arr[i].charAt(j)) + 1);
                } else {
                    mp.put(arr[i].charAt(j), 1);
                }
            }
            ans2.add(mp);
        }

//        now traversing hashset and for each value in hashset checking the value of its hashmap for respective string
//        to add the Anagram string together in one space
        for(var k: ans2){
            for(int i=0; i<len; i++) {
                String word = arr[i];
                HashMap<Character, Integer> mp = new HashMap<Character, Integer>();
                for (int j = 0; j < word.length(); j++) {
                    if (mp.containsKey(arr[i].charAt(j))) {
                        mp.put(arr[i].charAt(j), mp.get(arr[i].charAt(j)) + 1);
                    } else {
                        mp.put(arr[i].charAt(j), 1);
                    }
                }
                if(k.equals(mp) == true){
                    System.out.print(arr[i]+ " ");
                }
            }
            System.out.println();
        }
    }
}