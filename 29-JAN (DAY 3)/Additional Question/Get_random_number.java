import java.util.Random;

public class Get_random_number {
    public static void main(String[] args) {
//        double num = Math.random();
//        System.out.println(num);

        Random rn = new Random();
        int num2 = rn.nextInt(10);
        System.out.println(num2);
    }
}