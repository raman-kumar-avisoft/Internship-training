import java.util.Scanner;

public class binary_to_decimal {
    public static void main(String[] args) {
//        String str = "111111101";
//        int decimal = Integer.parseInt(str, 2);
////        radix 2 = binary, 8 = octal, 10 = decimal, 16 = hexadecimal
//        System.out.println(decimal);

//        Proper Implementation
        System.out.println("Enter the string: ");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        int sum = 0;
        char[] ch = new char[str.length()];
        ch = str.toCharArray();
        for(int i=0; i<str.length(); i++){
            if(ch[i] == '1'){
                sum+= Math.pow(2, str.length()-i-1);
            }
        }
        System.out.println(sum);
    }
}
