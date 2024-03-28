package online.voting.system;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OnlineVotingSystem {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);

        int maxWrongTries = 2;
        boolean continueLooping = true;
        while(continueLooping){
            try{
                displayMainMenu();
                System.out.print("Enter the option out of following: ");
                int choice = scannerInteger.nextInt();

                switch (choice){
                    case 1:
                        Admin admin = new Admin();                                                                      // goes to admin class.
                        break;

                    case 2:
                        User user = new User(true);                                                         // goes to user class.
                        break;

                    case 3:
                        System.out.println("THANK YOU FOR YOUR TIME **");
                        maxWrongTries = 1;
                        continueLooping = false;
                        break;
                }

            }catch (InputMismatchException inputMismatchException){
                System.out.println("NOT A VALID NUMBER **");
            }finally {
                if(maxWrongTries-- == 0){
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }
    }
    static void displayMainMenu(){
        System.out.println("1. Admin Module");
        System.out.println("2. User Module");
        System.out.println("3. Exit Online Voting System");
    }
}
