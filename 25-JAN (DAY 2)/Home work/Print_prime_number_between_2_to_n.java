public class Print_prime_number_between_2_to_n {
    public static void main(String[] args) {
        int n = 14;
        for(int i=2; i<=n; i++){
            int ch = 2;
            boolean prime = false;
            while(ch<i){
                if(i%ch == 0){
                    prime = true;
                }
                ch++;
            }
            if(!prime){
                System.out.println(i);
            }
        }
    }
}