import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class ShortesUniquePrefix {
    public static void main(String[] args) {
        Scanner scannerString = new Scanner(System.in);
        ArrayList<String> stringArrayList = new ArrayList<>();
        System.out.println("Enter the Strings in the arraylist");
        String string = "@";
        while (true) {
            string = scannerString.next();
            if (string.equals("-1")) {
                break;
            }
            stringArrayList.add(string);
        }

        Collections.sort(stringArrayList);
        for (int stringArrayListIndex = 0; stringArrayListIndex < stringArrayList.size(); stringArrayListIndex++) {
            System.out.println(stringArrayList.get(stringArrayListIndex));
        }

        HashSet<String> ansList = new HashSet<>();
        for(int stringArrayListIndex = 0; stringArrayListIndex < stringArrayList.size(); stringArrayListIndex++){
            boolean cond = true;
            String prefix = "";
            int prefixIndex = 0;

//            this loop is for checking basically for each prefix substring that it should not be already contained in ansList and another prefix substring should not be present
            while(cond){
                if(prefixIndex >= stringArrayList.get(stringArrayListIndex).length()){
                    ansList.add(null);
                    break;
                }
                prefix += stringArrayList.get(stringArrayListIndex).charAt(prefixIndex);
                int stringArrayListIndex2 = -1;
                for(stringArrayListIndex2 = 0; stringArrayListIndex2 < stringArrayList.size(); stringArrayListIndex2++){
                    if((prefixIndex) >= stringArrayList.get(stringArrayListIndex2).length() || stringArrayListIndex == stringArrayListIndex2){
                        continue;
                    }
                    else if(prefix.equals(stringArrayList.get(stringArrayListIndex2).substring(0,prefixIndex+1)) || ansList.contains(prefix)){
                        break;
                    }
                }
                if(stringArrayListIndex2 == stringArrayList.size()){
                    ansList.add(prefix);
                    cond = false;
                }
                prefixIndex++;
            }
        }
        System.out.println(ansList);
    }
}