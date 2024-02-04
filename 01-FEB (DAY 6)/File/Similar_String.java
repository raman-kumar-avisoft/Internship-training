import java.util.Scanner;

public class Similar_String {
    static boolean solve(String a, String b, int s, int e){
        // base case
        if(a.equals(b)){
            return true;
        }
        if(a.length()%2 != 0){
            return false;
        }

        boolean ans = false;
        int mid = (e-s)/2 +s;
        String a1 = a.substring(s,mid+1);
        String a2 = a.substring(mid+1,e+1);
        String b1 = b.substring(s,mid+1);
        String b2 = b.substring(mid+1,e+1);
        if(a1.equals(b1)){
            if(a2.equals(b2)){
                return true;
            }
            else{
                ans = solve(a2, b2, 0, mid);
            }
        }else if(a1.equals(b2)){
            if(a2.equals(b1)){
                return true;
            }
            else{
                ans = solve(a2, b1, 0, mid);
            }
        }else if(a2.equals(b1)){
            if(b2.equals(a1)){
                return true;
            }
            else{
                ans = solve(a1, b2, 0, mid);
            }
        }else {
            ans = false;
        }

        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first String");
        String a = sc.nextLine();

        System.out.println("Enter the second String");
        String b = sc.nextLine();
        boolean ans = false;
        if(a.length() != b.length()){
            ans = false;
        }else{
            ans = solve(a, b, 0, a.length()-1);
        }
        if(ans == true){
            System.out.println("SIMILAR STR");
        }else{
            System.out.println("NOT SIMILAR STR");
        }
    }
}