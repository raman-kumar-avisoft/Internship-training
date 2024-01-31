import java.util.Scanner;

public class Decimal_to_binary_Conversion{
    public static void main(String[] args) {
               shortcut
       int num = -16;
       System.out.println(Integer.toBinaryString(num));

//        Implementation of logic
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter the decimal Number");
//        int decimal_num = sc.nextInt();
//        int num = decimal_num, i=0;
//        int binary_number=0;
//        while(num>0){
//            if((num&1) == 1){
//                binary_number += 1*Math.pow(10,i);
//            }
//            num = num>>1;
//            i++;
//        }
//        System.out.println(binary_number);

// //        For -ve number
//         String ans = "";
//         Scanner sc = new Scanner(System.in);
//         System.out.println("Enter the decimal Number");
//         int decimal_num = sc.nextInt();
//         int num = decimal_num, i=0;
//         int binary_number=0;
//         while(i<32){
//             if((num&1) == 1){
//                 ans += 1;
//             }else{
//                 ans += 0;
//             }
//             num = num>>1;
//             i++;
//         }
//         System.out.println(ans);
//         char[] ch = new char[32];
//         ch = ans.toCharArray();
//         String ans2 = "";
//         int j=31;
//         for(; j>=0; j--){
//             if(ch[i] == '1'){
//                 ans2 += "1";
//                 break;
//             }else{
//                 ch[j] = '1';
//                 ans2 += '1';
//             }
//         }
//         while(j>=0){
//             ans2 += ch[j];
//             j--;
//         }
//         System.out.println(ans2);
    }
}
