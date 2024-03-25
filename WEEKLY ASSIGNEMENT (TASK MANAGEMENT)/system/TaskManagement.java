package task.management.system;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskManagement {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);
        int maxWrongTries = 3;
        while(--maxWrongTries >= 0){
            try{
                displayTaskManagement();
                System.out.print("Select the option out of the following: ");
                int choice = scannerInteger.nextInt();

                switch (choice){
                    case 1:
                        Admin admin = new Admin();
                        break;
                    case 2:
                        User user = new User();
                        break;
                    default:
                        System.out.println("NOT A VALID OPTION **");
                        break;
                }
            }catch(InputMismatchException inputMismatchException){
                scannerInteger.nextLine();
                System.out.println("NOT A VALID NUMBER **");
            }
        }
        if(maxWrongTries < 0 ){
            System.out.println("TOO MANY WRONG TRIES **");
        }
        System.out.println("THANK YOU !!");
    }
    static void displayTaskManagement(){
        System.out.println("1. Admin");
        System.out.println("2. User");
        System.out.println("3. Exit");
    }
}
