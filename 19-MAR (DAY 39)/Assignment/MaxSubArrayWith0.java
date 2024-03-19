import java.util.HashMap;

public class MaxSubArrayWith0 {
    public static void main(String[] args) {
        int arr[] = {95, -97, -387, -435, -5, -70, 897, 127, 23, 284};
        int answer = maxLen(arr, 10);
        System.out.println(answer);
    }
    static int maxLen(int arr[], int n)
    {
        // Your code here
        int sum = 0;
        int max_len = 0;
        int i = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(sum, -1);
        while(i < n)
        {
            sum = sum + arr[i];
            if(hm.containsKey(sum))
            {
                int temp_len = i - hm.get(sum);
                max_len = temp_len > max_len ? temp_len : max_len;
            }
            else
            {
                hm.put(sum, i);
            }
            i = i + 1;
        }
        return max_len;
    }
}