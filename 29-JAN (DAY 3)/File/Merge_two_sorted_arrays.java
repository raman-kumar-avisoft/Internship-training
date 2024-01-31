import java.util.ArrayList;
import java.util.Scanner;

public class Merge_two_sorted_arrays {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(("Enter the size of first array"));
        int s1 = sc.nextInt();
        ArrayList<Integer> al1 = new ArrayList<>();

        System.out.println("Enter the elements of the first sorted array: ");
        for(int i=0; i<s1; i++){
            al1.add(sc.nextInt());
        }

        System.out.println(("Enter the size of second array"));
        int s2 = sc.nextInt();
        ArrayList<Integer> al2 = new ArrayList<>();

        System.out.println("Enter the elements of the second sorted array: ");
        for(int i=0; i<s2; i++){
            al2.add(sc.nextInt());
        }

        ArrayList<Integer> ans = new ArrayList<>();
//        MAIN LOGIC OF MERGING
        int i=0, j=0;
        while(i<al1.size() && j<al2.size()){
            if(al1.get(i)>=al2.get(j)){
                ans.add(al2.get(j));
                j++;
            }else{
                ans.add(al1.get(i));
                i++;
            }
        }
        while(i<al1.size()){
            ans.add(al1.get(i));
            i++;
        }
        while(j<al2.size()){
            ans.add(al2.get(j));
            j++;
        }

        System.out.println("The answer is: ");
        for(int k = 0; k < ans.size(); k++){
            System.out.print(ans.get(k)+" ");
        }
    }
}