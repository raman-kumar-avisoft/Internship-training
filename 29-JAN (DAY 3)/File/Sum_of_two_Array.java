import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Sum_of_two_Array {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter size of first array: ");
        int sz1 = sc.nextInt();
        ArrayList<Integer> al1 = new ArrayList<>();
        System.out.println("Enter the element of first arraylist digit wise");
        for(int i=0; i<sz1; i++){
            al1.add(sc.nextInt());
        }
        ArrayList<Integer> al2 = new ArrayList<>();
        System.out.println("Enter size of second array: ");
        int sz2 = sc.nextInt();
        System.out.println("Enter the element of second arraylist digit wise");
        for(int i=0; i<sz2; i++){
            al2.add(sc.nextInt());
        }

        ArrayList<Integer> ans = new ArrayList<>();

        Collections.reverse(al1);
        Collections.reverse((al2));
        int carry = 0;
        int i=0;
        while(i<al1.size() && i<al2.size()){
            int sum = al1.get(i)+al2.get(i)+carry;
            ans.add((sum)%10);
            carry = (sum)/10;
            i++;
        }
        while(i<al2.size()){
            int sum = al2.get(i) + carry ;
            ans.add((sum)%10);
            carry = (sum)/10;
            i++;
        }
        while(i<al1.size()){
            int sum = al1.get(i) + carry ;
            ans.add((sum)%10);
            carry = (sum)/10;
            i++;
        }
        if(carry != 0){
            ans.add(carry);
        }
        Collections.reverse(ans);
        for(int j=0; j<ans.size(); j++){
            System.out.print(ans.get(j));
        }

    }
}