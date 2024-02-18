class Account{
    static int id = 1;
    private int uniqueId;
    private String cardNumber;
    private boolean blocked;
    private int pin;

    Account(){

    }
    void setUniqueId(){
        this.uniqueId = id++;
    }
    void setCardNumber(String cardNumber){

    }
    void setBlocked(boolean cond){
        blocked = cond;
    }
}