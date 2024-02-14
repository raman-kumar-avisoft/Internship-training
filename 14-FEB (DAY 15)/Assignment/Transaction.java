import java.util.InputMismatchException;
import java.util.Scanner;

class Transaction{
    private static int id = 1;
    private int transactionId;
    private String transactionType;
    private BankAccount selfTransactionAc;
    private BankAccount firstBankAc;
    private BankAccount secondBankAc;

    private double amount;
    Transaction(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Details of the Transaction");
        boolean cond = true;
        int count = 0;
        System.out.println("1. Withdrawal");
        System.out.println("2. Deposit");
        System.out.println("3. Transfer b/w Accounts");
        int ch;
        do{
            try {
                System.out.println("Choose a option out of following: ");
                ch = sc.nextInt();
                if(ch >=1 && ch<=3) {
                    if(ch == 1){
                        setTransactionType("Withdrawal");
                    }else if(ch == 2){
                        setTransactionType("Deposit");
                    }else{
                        setTransactionType("Transfer");
                    }
                    cond = false;
                }
            }catch (InputMismatchException e){
                System.out.println("PLEASE ENTER A VALID VALUE !!");
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

        do{
            try {
                System.out.print("Enter the Amount: ");
                setAmount(sc.nextDouble());
                if(this.getAmount()>=0){
                    cond = false;
                }else{
                    throw new BankException("Amount must be in Positive");
                }
            }catch (InputMismatchException e){
                System.out.println("PLEASE ENTER A VALID AMOUNT !!");
                cond = true;
            }
            catch (BankException e){
                System.out.println(e.getMessage());
                cond = true;
            }
            finally {
                sc.nextLine();
                count++;
            }
        }while(cond && count<=3);
        if(count == 4){
            System.out.println("TOO MANY WRONG INPUTTED VALUE !!");
            System.exit(1);
        }
    }
    void setWithdrawalDetails(BankAccount obj){
        this.selfTransactionAc = obj;
    }
    void setDepositDetails(BankAccount obj){
        this.selfTransactionAc = obj;
    }
    void setFirstAccount(BankAccount obj){
        this.firstBankAc = obj;
    }
    void setSecondAccount(BankAccount obj){
        this.secondBankAc = obj;
    }
    void setTransactionId(int data){
        this.transactionId = id++;
    }
    void setTransactionType(String data){
        this.transactionType = data;
    }
    void setAmount(double data){
        this.amount = data;
    }
    BankAccount getSelfTransactionAccount(){
        return this.selfTransactionAc;
    }
    BankAccount getFirstTransactionAccount(){
        return this.firstBankAc;
    }
    BankAccount getSecondTransactionAccount(){
        return this.secondBankAc;
    }
    String getTransactionType(){
        return this.transactionType;
    }
    double getAmount(){
        return this.amount;
    }
}