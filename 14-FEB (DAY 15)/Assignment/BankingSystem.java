import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BankingSystem {
    private static ArrayList<BankAccount> bankAccountList = new ArrayList<>();
    private static ArrayList<Transaction> transactionsList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Banking System !!");
        boolean cond = true;
        while(cond){
            displayMainMenu();
            System.out.println("Select the option out of the following: ");
            int ch;
            try{
                ch = sc.nextInt();
                sc.nextLine();
                switch (ch){
                    case 1:
                        addAccount();
                        break;
                    case 2:
                        addTransaction();
                        break;
                    case 3:
                        displayAccount();
                        break;
                    case 4:
                        displayTransaction();
                        break;
                    case 5:
                        cond = false;
                        break;
                    default:
                        System.out.println("Enter a valid inPUT !!");
                }
            }catch(Exception e){
                System.out.println("here");
                System.out.println("ENTER A VALID INPUT");
            }

        }
    }
    static void displayMainMenu(){
        System.out.println();
        System.out.println("1. Add a Account");
        System.out.println("2. Add a Transaction");
        System.out.println("3. Display Accounts");
        System.out.println("4. Display Transaction");
        System.out.println("5. Exit");
    }
    static void addAccount(){
        bankAccountList.add(new BankAccount(bankAccountList));
    }
    static void displayAccount(){
        System.out.println("All Account present in the Bank -- ");
        for(int i=0; i<bankAccountList.size(); i++){
            bankAccountList.get(i).getDetails();
        }
    }
    static void withdrawMoney(int acNumber, Transaction obj, String type){
        for(int i=0; i<bankAccountList.size(); i++){
            if(acNumber == bankAccountList.get(i).getAccountNumber()){
                if(bankAccountList.get(i).getBalance() < obj.getAmount()){
                    System.out.println("Cannot perform Transaction as amount is greater than Account Balance");
                }else{
                    if(type.equals("Withdrawal")){
                        bankAccountList.get(i).setBalance(bankAccountList.get(i).getBalance()-obj.getAmount());
                        obj.setWithdrawalDetails(bankAccountList.get(i));
                    }else{
                        bankAccountList.get(i).setBalance(bankAccountList.get(i).getBalance()-obj.getAmount());
                        obj.setFirstAccount(bankAccountList.get(i));
                    }
                }
            }
        }
    }
    static void depositMoney(int acNumber, Transaction obj, String type){
        for(int i=0; i<bankAccountList.size(); i++){
            if(acNumber == bankAccountList.get(i).getAccountNumber()){
                if(type.equals("Deposit")){
                    bankAccountList.get(i).setBalance(bankAccountList.get(i).getBalance()+obj.getAmount());
                    obj.setDepositDetails(bankAccountList.get(i));
                }else{
                    bankAccountList.get(i).setBalance(bankAccountList.get(i).getBalance()+obj.getAmount());
                    obj.setSecondAccount(bankAccountList.get(i));
                }
            }
        }
    }
    static boolean checkAccount(int account){
        for(int i=0; i<bankAccountList.size(); i++){
            if(bankAccountList.get(i).getAccountNumber() == account){
                return true;
            }
        }
        return false;
    }
    static void addTransaction(){
        Scanner sc = new Scanner(System.in);
        Transaction obj = new Transaction();
        if(obj.getTransactionType().equals("Withdrawal")){
            System.out.println("Code for Withdrawal from the account");
            boolean cond = true;
            int count = 0;
            int acNumber1 = -1;
            do{
                try {
                    System.out.print("Enter the Bank Account Number: ");
                    acNumber1 = sc.nextInt();
                    boolean ac = checkAccount(acNumber1);
                    if(ac){
                        cond = false;
                    }else{
                        System.out.println("No A/C with this Account Number is present in the DB !!");
                    }
                }catch (InputMismatchException e){

                    System.out.println("PLEASE ENTER A INTEGER VALUE !!");
                    cond = true;
                }finally {
                    sc.nextLine();
                    count++;
                }
            }while(cond && count<=3);
            if(count == 4){
                System.out.println("TOO MANY WRONG INPUTTED VALUE !!");
                System.exit(1);
            }
            withdrawMoney(acNumber1, obj, "Withdrawal");
        }else if(obj.getTransactionType().equals("Deposit")){
            System.out.println("Code for Deposit int the account");
            boolean cond = true;
            int count = 0;
            int acNumber1 = -1;
            do{
                try {
                    System.out.print("Enter the Bank Account Number: ");
                    acNumber1 = sc.nextInt();
                    boolean ac = checkAccount(acNumber1);
                    if(ac){
                        cond = false;
                    }else{
                        System.out.println("No A/C with this Account Number is present in the DB !!");
                    }
                }catch (InputMismatchException e){
                    System.out.println("PLEASE ENTER A INTEGER VALUE !!");
                    cond = true;
                }finally {
                    sc.nextLine();
                    count++;
                }
            }while(cond && count<=3);
            if(count == 4){
                System.out.println("TOO MANY WRONG INPUTTED VALUE !!");
                System.exit(1);
            }
            depositMoney(acNumber1, obj, "Deposit");
        }else{
            System.out.println("FROM ONE ACCOUNT TO ANOTHER");
            boolean cond = true;
            int count = 0;
            int acNumber1 = -1;
            System.out.println("Enter the Withdrawing Account");
            do{
                try {
                    System.out.print("Enter the Bank Account Number: ");
                    acNumber1 = sc.nextInt();
                    boolean ac = checkAccount(acNumber1);
                    if(ac){
                        cond = false;
                    }else{
                        System.out.println("No A/C with this Account Number is present in the DB !!");
                    }
                }catch (InputMismatchException e){
                    System.out.println("PLEASE ENTER A INTEGER VALUE !!");
                    cond = true;
                }finally {
                    sc.nextLine();
                    count++;
                }
            }while(cond && count<=3);
            if(count == 4){
                System.out.println("TOO MANY WRONG INPUTTED VALUE !!");
                System.exit(1);
            }

            count = 0;
            System.out.println("Enter the Deposit Account");
            int acNumber2 = -1;
            do{
                try {
                    System.out.print("Enter the Bank Account Number: ");
                    acNumber2 = sc.nextInt();
                    boolean ac = checkAccount(acNumber2);
                    if(ac){
                        cond = false;
                    }else{
                        System.out.println("No A/C with this Account number in present in DB !!");
                    }
                }catch (InputMismatchException e){
                    System.out.println("PLEASE ENTER A INTEGER VALUE !!");
                    cond = true;
                }finally {
                    sc.nextLine();
                    count++;
                }
            }while(cond && count<=3);
            if(count == 4){
                System.out.println("TOO MANY WRONG INPUTTED VALUE !!");
                System.exit(1);
            }

            if(acNumber1 == acNumber2){
                System.out.println("BOTH ACCOUNT CANNOT BE SAME TRY AGAIN !!");
            }else{
                withdrawMoney(acNumber1, obj, "Transfer");
                depositMoney(acNumber2, obj, "Transfer");
            }
        }
        transactionsList.add(obj);
    }
    static void displayWithdrawnAc(Transaction obj){
        System.out.println("\nType: WITHDRAWAL");
        System.out.println("ACCOUNT NUMBER: " + obj.getSelfTransactionAccount().getAccountNumber());
        System.out.println("AMOUNT WITHDRAWN: " + obj.getAmount());
    }
    static void displayDepositAc(Transaction obj){
        System.out.println("\nType: DEPOSIT");
        System.out.println("ACCOUNT NUMBER: " + obj.getSelfTransactionAccount().getAccountNumber());
        System.out.println("AMOUNT CREDITED: " + obj.getAmount());
    }
    static void displayTransferAc(Transaction obj){
        System.out.println("\nType: TRANSFER FROM ONE A/C to ANOTHER A/C");
        System.out.println("ACCOUNT NUMBER FROM WHICH MONEY IS DEDUCTED: " + obj.getFirstTransactionAccount().getAccountNumber());
        System.out.println("AMOUNT NUMBER TO WHICH MONEY IS CREDITED: " + obj.getSecondTransactionAccount().getAccountNumber());
        System.out.println("AMOUNT TRANSFERRED: " + obj.getAmount());
    }
    static void displayTransaction(){
        System.out.println("Total Transaction are: "+transactionsList.size());
        for(int i=0; i<transactionsList.size(); i++){
            System.out.println("CHECK");
            if(transactionsList.get(i).getTransactionType().equals("Withdrawal")){
                displayWithdrawnAc(transactionsList.get(i));
            }else if(transactionsList.get(i).getTransactionType().equals("Deposit")){
                displayDepositAc(transactionsList.get(i));
            }else{
                displayTransferAc(transactionsList.get(i));
            }
        }
    }
}