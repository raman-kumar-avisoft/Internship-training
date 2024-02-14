import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class BankAccount{
    private int accountNumber;
    private double balance;

    BankAccount(ArrayList<BankAccount> bankAccountArrayList){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the details of the Account: ");
        boolean cond;
        int count = 0;
        do{
            try {
                System.out.print("Enter the Bank Account Number: ");
                setAccountNumber(sc.nextInt());
                for(int i=0; i<bankAccountArrayList.size(); i++){
                    if(getAccountNumber() == bankAccountArrayList.get(i).getAccountNumber()){
                        throw new BankException("Account Already Present in DB !!");
                    }
                }
                cond = false;
            }catch (InputMismatchException e){
                System.out.println("PLEASE ENTER A INTEGER VALUE !!");
                cond = true;
            }catch (BankException e){
                System.out.println(e.getMessage());
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
                System.out.print("Enter the Balance in the Account Number: ");
                setBalance(sc.nextDouble());
                if(this.getBalance()>=0){
                    cond = false;
                }else{
                    throw new BankException("Balance can't be negative");
                }
            }catch (InputMismatchException e){
                System.out.println("PLEASE ENTER A VALID BALANCE !!");
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
    void getDetails(){
        System.out.println("\nBank Account Number: " + getAccountNumber());
        System.out.println("Balance: " + getBalance());
    }
    void setAccountNumber(int data){
        this.accountNumber = data;
    }
    void setBalance(double data){
        this.balance = data;
    }
    int getAccountNumber(){
        return this.accountNumber;
    }
    double getBalance(){
        return this.balance;
    }
}