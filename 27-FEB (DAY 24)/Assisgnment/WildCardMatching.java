import java.util.Scanner;

public class WildCardMatching {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first String: ");
        String str1 = sc.next();
        System.out.println("Enter Second String: ");
        String str2 = sc.next();

        boolean result = solve(str1, str2, 0, 0);
        if(result == true){
            System.out.println("Both Strings are matching using wildcard characters");
        }else{
            System.out.println("Both are not matching by adding wildcard characters as well");
        }
    }

    private static boolean solve(String A, String B, int i, int j) {
        // If both strings are exhausted, they match
        if (i == A.length() && j == B.length()) {
            return true;
        }

        // If only one of the strings is exhausted, they don't match
        if (i == A.length() || j == B.length()) {
            return false;
        }

        // If current characters match or B has a wildcard '*'
        if (A.charAt(i) == B.charAt(j) || B.charAt(j) == '?') {
            return solve(A, B, i + 1, j + 1);
        } else if (B.charAt(j) == '*') {
            // '*' can match zero or more characters
            return solve(A, B, i + 1, j + 1) || solve(A, B, i + 1, j) || solve(A, B, i, j + 1);
        } else {
            // Current characters don't match
            return false;
        }
    }
}
