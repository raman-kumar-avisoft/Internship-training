import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AutomatedTellerMachine {
    static ArrayList<Account> accountArrayList = new ArrayList<Account>();

    public static void main(String[] args) {
        Scanner scannerInt = new Scanner(System.in);
        boolean cond = true;
        int choice = -1;

        while(cond){
            try{
                displayMainMenu();
                System.out.print("Enter the option out of the above: ");
                choice = scannerInt.nextInt();

                switch (choice){
                    case 1:
                        System.out.println("Adding an account");
                        addAccount();
                        break;
                    case 2:
                        System.out.println("Depositing Money");
                        depositMoney();
                        break;
                    case 3:
                        System.out.println("Withdrawing Money");
                        withdrawnMoney();
                        break;
                    case 4:
                        System.out.println("Balance Enquiry");
                        balanceEnquiry();
                        break;
                    case 5:
                        System.out.println("Transfer Money from one account to Another");
                        transferFromOneAcToAnother();
                        break;
                    case 6:
                        System.out.println("Thank You !!");
                        cond = false;
                        break;
                    default:
                        throw new InputMismatchException();
                }
            }catch (InputMismatchException e){
                System.out.println("enter a correct option --");
            }
        }
    }
    static void transferFromOneAcToAnother() {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerString = new Scanner(System.in);

        int maxWrongTries = 2;
        String cardNumberPrimary = null, cardNumberSecondary = null, pin = null;
        int indexPrimary = -1, indexSecondary = -1;
        while (maxWrongTries > 0) {
            try {
                System.out.println("Enter the CardNumber for Primary Account: ");
                cardNumberPrimary = scannerString.next();

                if (cardNumberPrimary.length() != 8) {
                    throw new InputMismatchException("CardNumber length has to be 8");
                }
                for (int i = 0; i < 8; i++) {
                    if (!(cardNumberPrimary.charAt(i) >= '0' && cardNumberPrimary.charAt(i) <= '9')) {
                        throw new InputMismatchException("Only Digits are allowed");
                    }
                }

                System.out.println("Enter the PIN: ");
                pin = scannerString.next();

                if (pin.length() != 4) {
                    throw new InputMismatchException("PIN length has to be 4");
                }
                for (int i = 0; i < 4; i++) {
                    if (pin.charAt(i) >= '0' != pin.charAt(i) <= '9') {
                        throw new InputMismatchException("Only Digits are allowed");
                    }
                }

                indexPrimary = findAccount(cardNumberPrimary);
                if (indexPrimary == -1) {
                    throw new InputMismatchException("No account found with this card number ");
                } else {
                    if (!accountArrayList.get(indexPrimary).getPin().equals(pin)) {
                        throw new InputMismatchException("Pin doesn't match with card number");
                    }
                }

                maxWrongTries = -1;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage() + " tries left: " + --maxWrongTries);
            }
        }
        if (maxWrongTries == 0) {
            System.out.println("Too many tries --");
            System.exit(0);
        }

        maxWrongTries = 2;
        while(maxWrongTries > 0) {
            try {
                System.out.println("Enter the CardNumber for Secondary Account: ");
                cardNumberSecondary = scannerString.next();

                if (cardNumberSecondary.length() != 8) {
                    throw new InputMismatchException("CardNumber length has to be 8");
                }
                for (int i = 0; i < 8; i++) {
                    if (!(cardNumberSecondary.charAt(i) >= '0' && cardNumberSecondary.charAt(i) <= '9')) {
                        throw new InputMismatchException("Only Digits are allowed");
                    }
                }

                indexSecondary = findAccount(cardNumberSecondary);
                if (indexPrimary == -1) {
                    throw new InputMismatchException("No account found with this card number ");
                } else if (indexPrimary == indexSecondary) {
                    throw new InputMismatchException("Primary and Secondary Account cannot be same");
                }

                System.out.println("Enter the amount you want to deposit");
                int amount = scannerInt.nextInt();

                accountArrayList.get(indexPrimary).withdrawal(amount); // here we are setting the balance.
                accountArrayList.get(indexSecondary).deposit(amount);

                maxWrongTries = -1;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage() + " tries left: " + --maxWrongTries);
            }
        }
        if (maxWrongTries == 0) {
            System.out.println("Too many tries --");
            System.exit(0);
        }
        System.out.println("Money transferred from one account to another successfully");
    }
    static void addAccount(){
        Account account = new Account();
        int index = findAccount(account.getCardNumber());
        if(index != -1){
            System.out.println("Cannot create Account as it's already exists !!");
        }else{
            accountArrayList.add(account);
            System.out.println("Account created Successfully");
        }
    }
    static void balanceEnquiry() {
        Scanner scannerString = new Scanner(System.in);
        int maxWrongTries = 2;
        while (maxWrongTries > 0) {
            try {
                System.out.println("Enter the CardNumber: ");
                String cardNumber = scannerString.next();

                if (cardNumber.length() != 8) {
                    throw new InputMismatchException("CardNumber length has to be 8");
                }
                for (int i = 0; i < 8; i++) {
                    if (!(cardNumber.charAt(i) >= '0' && cardNumber.charAt(i) <= '9')) {
                        throw new InputMismatchException("Only Digits are allowed");
                    }
                }

                System.out.println("Enter the PIN: ");
                String pin = scannerString.next();

                if (pin.length() != 4) {
                    throw new InputMismatchException("PIN length has to be 4");
                }
                for (int i = 0; i < 4; i++) {
                    if (pin.charAt(i) >= '0' != pin.charAt(i) <= '9') {
                        throw new InputMismatchException("Only Digits are allowed");
                    }
                }

                int index = findAccount(cardNumber);
                if (index == -1) {
                    throw new InputMismatchException("No account found with this card number ");
                } else {
                    if (!accountArrayList.get(index).getPin().equals(pin)) {
                        throw new InputMismatchException("Pin doesn't match with card number");
                    }
                }

                System.out.println("Balance for Card Number " + accountArrayList.get(index).getCardNumber() + ": " + accountArrayList.get(index).getBalance());
                maxWrongTries = -1;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage() + " tries left: " + --maxWrongTries);
            }
        }
        if(maxWrongTries == 0){
            System.out.println("Too many wrong tries --");
            System.exit(0);
        }
    }

    static void depositMoney(){
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerString = new Scanner(System.in);

        int maxWrongTries = 2;
        while(maxWrongTries>0){
            try{
                System.out.println("Enter the CardNumber: ");
                String cardNumber = scannerString.next();

                if(cardNumber.length() != 8){
                    throw new InputMismatchException("CardNumber length has to be 8");
                }
                for(int i=0; i<8; i++){
                    if(!(cardNumber.charAt(i) >= '0' && cardNumber.charAt(i) <= '9')){
                        throw new InputMismatchException("Only Digits are allowed");
                    }
                }

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

                System.out.println("Enter the amount you want to deposit");
                int amount = scannerInt.nextInt();

                int index = findAccount(cardNumber);
                if (index == -1) {
                    throw new InputMismatchException("No account found with this card number ");
                } else {
                    if (!accountArrayList.get(index).getPin().equals(pin)) {
                        throw new InputMismatchException("Pin doesn't match with card number");
                    }
                }

                accountArrayList.get(index).deposit(amount); // here we are setting the balance.

                maxWrongTries = -1;
            }catch(InputMismatchException e){
                System.out.println(e.getMessage() + " tries left: " + --maxWrongTries);
            }
        }
        System.out.println("Money Deposited Successfully");
    }
    static void withdrawnMoney(){
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerString = new Scanner(System.in);

        int maxWrongTries = 2;
        while(maxWrongTries>0){
            try{
                System.out.println("Enter the CardNumber: ");
                String cardNumber = scannerString.next();

                if(cardNumber.length() != 8){
                    throw new InputMismatchException("CardNumber length has to be 8");
                }
                for(int i=0; i<8; i++){
                    if(!(cardNumber.charAt(i) >= '0' && cardNumber.charAt(i) <= '9')){
                        throw new InputMismatchException("Only Digits are allowed");
                    }
                }

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

                System.out.println("Enter the amount you want to withdraw");
                int amount = scannerInt.nextInt();

                int index = findAccount(cardNumber);
                if (index == -1) {
                    throw new InputMismatchException("No account found with this card number ");
                } else {
                    if (!accountArrayList.get(index).getPin().equals(pin)) {
                        throw new InputMismatchException("Pin doesn't match with card number");
                    }
                }

                accountArrayList.get(index).withdrawal(amount); // here we are setting the balance.

                maxWrongTries = -1;
            }catch(InputMismatchException e){
                System.out.println(e.getMessage() + " tries left: " + --maxWrongTries);
            }
        }
        System.out.println("Money Withdrawn Successfully");
    }
    static int findAccount(String cardNumber){
        for(int accountArrayListIndex = 0; accountArrayListIndex<accountArrayList.size(); accountArrayListIndex++){
            if(cardNumber.equals(accountArrayList.get(accountArrayListIndex).getCardNumber())){
                return accountArrayListIndex;
            }
        }
        return -1;
    }
    static void displayMainMenu(){
        System.out.println("1. Add a Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdrawn Money");
        System.out.println("4. Balance Enquiry");
        System.out.println("5. Transfer Money from one account to another");
        System.out.println("6. Exit");
    }
}
