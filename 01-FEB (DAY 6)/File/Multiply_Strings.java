import java.util.Scanner;

public class Multiply_Strings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the first number: ");
        StringBuilder str1 = new StringBuilder(sc.nextLine());

        System.out.println("Enter the second number: ");
        StringBuilder str2 = new StringBuilder(sc.nextLine());

        int carry = 0;
        int pp = 0;
        StringBuilder ans = new StringBuilder("");
        for(int i= str1.length()-1; i>=0; i--){
            StringBuilder line = new StringBuilder("");
            for(int z=0; z<pp; z++){
                line.append('0');
            }
            for(int j = str2.length()-1; j>=0; j--){
                int prod = (str2.charAt(j)-48) * (str1.charAt(i)-48) + carry;
                int digit = prod%10;
                prod /= 10;
                carry = prod;
                if(j==0){
                    line.append(digit);
                    if(prod != 0){
                        line.append(prod);
                    }
                }else{
                    line.append(digit);
                }
            }
//            System.out.println(line);

            if(i==str1.length()-1){
                ans = line;
                System.out.println("yup");
            }
            else{
                StringBuilder ans2 = new StringBuilder("");
                int carry2 = 0;
                int j=0;
                for(int k=0; k<ans.length(); k++){
                    int first = (ans.charAt(k)-48);
                    int second = (line.charAt(j)-48);
                    int sum = first + second + carry2;

//                    System.out.println(sum%10);
                    ans2.append(sum%10);
                    carry2 = sum/10;
                    j++;
                }
                while(j<line.length()){
                    ans2.append(((line.charAt(j)-48)+carry2)%10);
//                    System.out.println((line.charAt(j)+carry2)%10);
                    carry2 = ((line.charAt(j)-48)+carry2)/10;
                    j++;
                }
                if(carry2!=0){
//                    System.out.println(carry2);
                    ans2.append(carry2);
                }
//                System.out.println(ans2);
                ans = ans2;
//                System.out.println(ans);
                carry2 = 0;
            }
//            System.out.println(ans);
            carry = 0;
            pp++;
            System.out.println(ans);
        }
        System.out.println("Answer is: " + ans.reverse());
    }
}