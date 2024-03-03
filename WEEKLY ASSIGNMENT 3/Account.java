import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Account{
    static int id = 1;
    private int uniqueId;
    private String cardNumber;
    private boolean blocked;
    private String pin;
    private int balance;

    Account(){
        Scanner scannerString = new Scanner(System.in);

        int maxWrongTries = 2;
        while(maxWrongTries > 0) {
            try{
                System.out.println("Enter the CardNumber: ");
                String cardNumber = scannerString.next();

                if(cardNumber.length() != 8){
                    throw new InputMismatchException("CardNumber length has to be 8");
                }
                for(int i=0; i<8; i++){
                    if(cardNumber.charAt(i) >= 48 != cardNumber.charAt(i) <= 57){
                        throw new InputMismatchException("Only Digits are allowed");
                    }
                }
                setCardNumber(cardNumber);
                maxWrongTries = -1;
            }catch(InputMismatchException e){
                System.out.println(e.getMessage() + " tries left: " + --maxWrongTries);
            }
        }
        if(maxWrongTries == 0){
            System.out.println("Too many wrong Tries --");
            System.exit(0);
        }

        maxWrongTries = 2;
        while(maxWrongTries > 0) {
            try{
                System.out.println("Enter the PIN: ");
                String pin = scannerString.next();

                if(pin.length() != 4){
                    throw new InputMismatchException("PIN length has to be 4");
                }
                for(int i=0; i<4; i++){
                    if(pin.charAt(i) >= '0' != pin.charAt(i) <= '9'){
                        throw new InputMismatchException("Only Digits are allowed");
                    }
                }
                setPin(pin);
                maxWrongTries = -1;
            }catch(InputMismatchException e){
                System.out.println(e.getMessage() + " tries left: " + --maxWrongTries);
            }
        }
        if(maxWrongTries == 0){
            System.out.println("Too many wrong Tries --");
            System.exit(0);
        }

        setUniqueId();
        setBlocked(false);
        setBalance(0);
    }
    boolean withdrawal(int amount){
        if(!getBlocked()){
            if(amount >100000){
                System.out.println("Amount is greater than the Limit");
                System.out.println("Your account is blocked !!");
                setBlocked(true);
            }else{
                if(getBalance() < amount){
                    System.out.println("Amount you are trying to withdraw is greater than account balance !!");
                }else if(getBalance() - amount < 500){
                    System.out.println("The Remaining Balance should not be less than 500 rupees");
                }else{
                    setBalance(getBalance()-amount);
                    return true;
                }
            }
        }else{
            System.out.println("Your Account is blocked cannot Withdrawn money");
        }
        return false;
    }
    boolean deposit(int amount){
        if(!getBlocked()){
            if(amount > 100000){
                System.out.println("Amount is greater than the Limit");
                System.out.println("Your account is blocked !!");
                setBlocked(true);
            }else{
                setBalance(getBalance()+amount);
                return true;
            }
        }else{
            System.out.println("Your Account is blocked cannot Deposit money");
        }
        return false;
    }
    boolean moneyTransfer(Account second, int amount){
        if(getBlocked()) {
            System.out.println("Cannot Transfer money as the primary account is blocked");
        } else if(second.getBlocked()){
            System.out.println("Cannot Transfer money as the secondary account is blocked");
        } else if(amount > 100000) {
            System.out.println("Transfer amount cannot be more than 1,00,000");
        } else if(amount == 0){
            System.out.println("Amount cannot be zero");
        } else if(amount < 0){
            System.out.println("Amount cannot be negative");
        }else{
            boolean withdrawnSuccessful = withdrawal(amount);
            boolean depositSuccessful = deposit(amount);
            if(withdrawnSuccessful && depositSuccessful){
                return true;
            }
        }
        return false;
    }

    void setBalance(int balance){
        this.balance = balance;
    }
    void setPin(String pin){
        this.pin = pin;
    }
    void setUniqueId(){
        this.uniqueId = id++;
    }
    void setCardNumber(String cardNumber){
        this.cardNumber = cardNumber;
    }
    void setBlocked(boolean cond){
        blocked = cond;
    }

    int getUniqueId(){
        return this.uniqueId;
    }
    String getCardNumber(){
        return this.cardNumber;
    }
    Boolean getBlocked(){
        return this.blocked;
    }
    int getBalance(){ return this.balance; }
    String getPin(){ return this.pin; }
}
