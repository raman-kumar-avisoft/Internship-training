public class Decimal_to_binary_Conversion{
    public static void main(String[] args) {
        //        shortcut
        int num = 16;
        System.out.println(Integer.toBinaryString(num));

//        other way
//        int org = 16;
//        int copy = 0;
//        int num = 1;
//        int count = 0;
//        if(org==1){
//            count++;
//        }
//        boolean check = false;
//        while(num<org){
//            num *= 2;
//            count++;
//            check = true;
//        }
//
//        if(check){
//            num/=2;
//        }
//
//        String ans = "";
//
//        while(count>0){
//            if((copy + num) <= org){
//                ans += '1';
//                copy+=num;
//            }else{
//                ans += '0';
//            }
//            num/=2;
//            count--;
//        }
//        System.out.println(ans);
    }
}