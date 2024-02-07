import java.util.*;

public class find_triplet_with_sum_k_in_an_array {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter the size of an array");
        int len = 7;

        int[] arr = new int[]{1,4,1,2,3,4,1};
//        System.out.print("Enter the values in the array: ");

//        for (int i=0; i<len; i++){
//            arr[i] = sc.nextInt();
//        }

//        sorting an array using library function
        Arrays.sort(arr);

        System.out.println("Enter the sum of the triplet you want to check in array: ");
        int k = 6;

        HashSet<ArrayList<Integer>> ans = new HashSet<ArrayList<Integer>>();

        for(int i=0; i<len-2; i++){
            int f, s, t;
//            two pointer approach
            int start = i + 1;
            int end = len - 1;
            f = arr[i];
            while(start < end){
                ArrayList<Integer> arrlist = new ArrayList<>(3);
                s = arr[start];
                t = arr[end];
                if((f+s+t) == k){
                    arrlist.add(f);
                    arrlist.add(s);
                    arrlist.add(t);
                    if(!ans.contains(arrlist)){
                        ans.add(arrlist);
                    }
                    start++;
                    end--;
                }else if((f+s+t) > k){
                    end--;
                }else{
                    start++;
                }
            }
        }
//        Printing the answer
        System.out.println(ans.size());
        for(var i: ans){
            System.out.println(i);
        }
    }
}
