import java.util.ArrayList;
import java.util.Scanner;

abstract class User{
    ArrayList<Electronics> cartElectronics = new ArrayList<>();
    ArrayList<Clothing> cartClothings = new ArrayList<>();
    private int userId;
    private String userName;

    public void setUserId(int data){
        this.userId = data;
    }
    public void setUserName(String data){
        this.userName = data;
    }
    public int getUserId(){
        return this.userId;
    }
    public String getUserName(){
        return this.userName;
    }
    abstract void displayDetails();
}
class Guest extends User{
    Scanner sc = new Scanner(System.in);
    public Guest(){
        System.out.println("ENTER THE DETAILS FOR GUEST ");
        System.out.print("Enter the User ID: ");
        setUserId(sc.nextInt());
        sc.nextLine();
        System.out.print("Enter the User Name: ");
        setUserName(sc.nextLine());
    }
    void displayDetails(){
        System.out.println("User ID: " + getUserId());
        System.out.println("Name: " + getUserName());
        double electronicPrice = 0;
        double clothingPrice = 0;
        boolean check =false;
        if(cartElectronics.size() > 0 || cartClothings.size() > 0){
            System.out.println("Items in the Cart are: ");
            check = true;
        }
        if(cartElectronics.size() > 0){
            System.out.println("Electronics Items in the Cart");
            for(int i=0; i<cartElectronics.size(); i++){
                System.out.println("Name is: " + cartElectronics.get(i).getName() + " & Price is: " + cartElectronics.get(i).getPrice());
                electronicPrice += cartElectronics.get(i).getPrice();
            }
        }
        if(cartClothings.size() > 0){
            System.out.println("\nClothing Items");
            for(int i=0; i<cartClothings.size(); i++){
                System.out.println("Name is: " + cartClothings.get(i).getName() + " & Price is: " + cartClothings.get(i).getPrice());
                clothingPrice += cartClothings.get(i).getPrice();
            }
        }
        if (check){
            System.out.println("Total Item Price: " + (clothingPrice+electronicPrice));
        }
    }

    void setCartElectronics(Electronics obj){
        cartElectronics.add(obj);
    }
    void setCartClothing(Clothing obj){
        cartClothings.add(obj);
    }
    ArrayList<Electronics> getCartElectronics(){
        return this.cartElectronics;
    }
    ArrayList<Clothing> getCartClothing(){
        return this.cartClothings;
    }
}
class RegisteredUser extends User{
    Scanner sc = new Scanner(System.in);
    ArrayList<Electronics> orderElectronics = new ArrayList<>();
    ArrayList<Clothing> orderClothing = new ArrayList<>();
    public RegisteredUser(){
        System.out.println("ENTER THE DETAILS FOR GUEST ");
        System.out.print("Enter the User ID: ");
        setUserId(sc.nextInt());
        sc.nextLine();
        System.out.print("Enter the User Name: ");
        setUserName(sc.nextLine());
    }
    void displayDetails(){
        System.out.println("User ID: " + getUserId());
        System.out.println("Name: " + getUserName());
        boolean check = false;
        if(cartElectronics.size() > 0 || cartClothings.size() > 0){
            System.out.println("Items in the Cart are: ");
            check = true;
        }
        double electronicsPrice = 0;
        if(cartElectronics.size() > 0){
            System.out.println("Electronics Items in the Cart");
            for(int i=0; i<cartElectronics.size(); i++){
                System.out.println("Name : " + cartElectronics.get(i).getName() + " & Price is: " + cartElectronics.get(i).getPrice());
                electronicsPrice += cartElectronics.get(i).getPrice();
            }
        }
        double clothingPrice = 0;
        if(cartClothings.size() > 0) {
            System.out.println("Clothing Items");
            for (int i = 0; i < cartClothings.size(); i++) {
                System.out.println("Name : " + cartClothings.get(i).getName() + " & Price is: " + cartClothings.get(i).getPrice());
                clothingPrice += cartClothings.get(i).getPrice();
            }
        }
        if(check){
            System.out.println("Total is: " + (electronicsPrice + clothingPrice));
        }
        if(orderElectronics.size() > 0 || orderClothing.size() > 0){
            System.out.println("\nOrder history");
        }
        if(orderElectronics.size() > 0){
            System.out.println("Electronics Product Names are: ");
            for(int i=0; i<orderElectronics.size(); i++){
                System.out.println(orderElectronics.get(i).getName());
            }
        }
        if(orderClothing.size() > 0){
            System.out.println("Clothing Product Names are: ");
            for(int i=0; i<orderClothing.size(); i++){
                System.out.println(orderClothing.get(i).getName());
            }
        }
    }

    void setCartElectronics(Electronics obj){
        cartElectronics.add(obj);
    }
    void setCartClothing(Clothing obj){
        cartClothings.add(obj);
    }
    void setOrderClothing(Clothing obj){
        orderClothing.add(obj);
    }
    void setOrderElectronics(Electronics obj){
        orderElectronics.add(obj);
    }
    ArrayList<Electronics> getCartElectronics(){
        return this.cartElectronics;
    }
    ArrayList<Clothing> getCartClothing(){
        return this.cartClothings;
    }
    ArrayList<Electronics> getOrderElectronics(){
        return this.orderElectronics;
    }
    ArrayList<Clothing> getOrderClothing(){
        return this.orderClothing;
    }
}
abstract class Product{
    private int productId;
    private String name;
    private double price;

    public void setProductId(int data){
        this.productId = data;
    }
    public void setName(String data){
        this.name = data;
    }
    public void setPrice(double data){
        this.price = data;
    }
    public int getProductId(){
        return this.productId;
    }
    public String getName(){
        return this.name;
    }
    public double getPrice(){
        return this.price;
    }
    abstract void display();
}
class Electronics extends Product{
    Scanner sc = new Scanner(System.in);
    private int powerSaving;
    private double warrantyPeriod;

    public Electronics(){
        System.out.println("Enter the details of the product");
        System.out.print("Enter the Electronic Product ID: ");
        setProductId(sc.nextInt());
        sc.nextLine();
        System.out.print("Enter the name of the Electronic Product: ");
        setName(sc.nextLine());
        System.out.print("Enter the price of the Electronic Product: ");
        setPrice(sc.nextDouble());
        System.out.print("Enter the Power Rating of Electronic Product: ");
        setPowerSaving(sc.nextInt());
        System.out.print("Enter the Warranty of Electronic Product: ");
        setWarrantyPeriod(sc.nextDouble());
    }
    public void setPowerSaving(int data) {
        this.powerSaving = data;
    }
    public void setWarrantyPeriod(double data){
        this.warrantyPeriod = data;
    }
    public int getPowerSaving(){
        return this.powerSaving;
    }
    public double getWarrantyPeriod(){
        return this.warrantyPeriod;
    }
    public void display(){
        System.out.println("Display of electronic");
    }
}
class Clothing extends Product{
    Scanner sc = new Scanner(System.in);
    private int size;
    private String color;

    public Clothing(){
        System.out.println("Enter the details of the product");
        System.out.print("Enter the Clothing Product ID: ");
        setProductId(sc.nextInt());
        sc.nextLine();
        System.out.print("Enter the name of the Clothing Product: ");
        setName(sc.nextLine());
        System.out.print("Enter the price of the Clothing Product: ");
        setPrice(sc.nextDouble());
        System.out.print("Enter the Size of the Clothing Product: ");
        setSize(sc.nextInt());
        sc.nextLine();
        System.out.print("Enter the Color of the Clothing Product: ");
        setColor(sc.nextLine());
    }
    public void setSize(int data) {
        this.size = data;
    }
    public void setColor(String data){
        this.color = data;
    }
    public int getSize(){
        return this.size;
    }
    public String getColor(){
        return this.color;
    }
    public void display(){
        System.out.println("Display of clothing");
    }
}

public class OnlineShoppingSystem {

    static ArrayList<Electronics> a1 = new ArrayList<>();
    static ArrayList<Clothing> a2 = new ArrayList<>();
    static ArrayList<Guest> a3 = new ArrayList<>();
    static ArrayList<RegisteredUser> a4 = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("WELCOME TO ONLINE SHOPPING SYSTEM");
        boolean end = true;

        while(end){
            mainMenu();
            System.out.print("Choose one to explore: ");
            int option = sc.nextInt();

            switch (option){
                case 1:
                    addProduct();
                    break;
                case 2:
                    addMember();
                    break;
                case 3:
                    System.out.println("1. Display Product");
                    System.out.println("2. Display Members");
                    System.out.println("3. Other Operations");
                    System.out.print("Enter the option you want to explore: ");
                    int op = sc.nextInt();

                    if(op == 1){
                        displayProducts();
                    }else if(op == 2){
                        displayMembers();
                    }else if(op == 3){
                        cartAndOrderOperations();
                    }else{
                        System.out.println("Wrong inputted value !!");
                    }
                    break;
                case 4:
                    end = false;
                    break;
                default:
                    System.out.println("Enter valid input!!");
                    break;
            }
        }
    }
    public static void addMember(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Enter a Guest");
        System.out.println("2. Enter a Registered Member");
        System.out.println("Enter the option you want to explore");
        int op = sc.nextInt();
        if(op == 1){
            a3.add(new Guest());
        }else if(op == 2){
            a4.add(new RegisteredUser());
        }else{
            System.out.println("Wrong value entered !!");
        }
    }

    public static int checkRegisterUser(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Registered User ID: ");
        int uid = sc.nextInt();
        for(int i=0; i<a4.size(); i++){
            if(a4.get(i).getUserId() == uid){
                return i;
            }
        }
        return -1;
    }
    public static int checkGuestUser(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Guest User ID: ");
        int uid = sc.nextInt();
        for(int i=0; i<a3.size(); i++){
            System.out.println("LLLLLL");
            if(a3.get(i).getUserId() == uid){
                return i;
            }
        }
        return -1;
    }
    public static int checkElectronicsId(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the product ID");
        int pid = sc.nextInt();
        for(int i=0; i<a1.size(); i++){
            if(a1.get(i).getProductId() == pid){
                return i;
            }
        }
        return -1;
    }
    public static int checkClothingId(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the product ID");
        int pid = sc.nextInt();
        for(int i=0; i<a2.size(); i++){
            if(a2.get(i).getProductId() == pid){
                return i;
            }
        }
        return -1;
    }

    public static void cartAndOrderOperations(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Add to Cart Operation");
        System.out.println("2. Place Order Operation");
        System.out.print("Enter the option you want to explore: ");
        int opt = sc.nextInt();

        if(opt == 1){
            System.out.println("1. Registered User");
            System.out.println("2. Guest User");
            System.out.println("3. Not a User");
            System.out.print("Enter the option: ");
            int opti = sc.nextInt();
            if(opti == 1){
                int i = checkRegisterUser();
                if(i==-1){
                    System.out.println("Wrong registered ID");
                }else{
                    System.out.println("1. Electronics");
                    System.out.println("2. Clothing");
                    System.out.println("Enter the option to explore more: ");
                    int opt2 = sc.nextInt();
                    if(opt2 == 1){
                        int j = checkElectronicsId();
                        if(j==-1){
                            System.out.println("Wrong Product ID");
                        }else{
                            a4.get(i).setCartElectronics(a1.get(j));
                            System.out.println("Record entered successfully");
                        }
                    }else if(opt2 == 2){
                        int j = checkClothingId();
                        if(j==-1){
                            System.out.println("Wrong Product ID");
                        }else{
                            a4.get(i).setCartClothing(a2.get(j));
                            System.out.println("Record entered successfully");
                        }
                    }else{
                        System.out.println("Wrong inputted value");
                    }
                }
            }else if (opti == 2){
                int i = checkGuestUser();
                if(i==-1){
                    System.out.println("Wrong registered ID");
                }else{
                    System.out.println("1. Electronics");
                    System.out.println("2. Clothing");
                    System.out.println("Enter the option to explore more: ");
                    int opt2 = sc.nextInt();
                    if(opt2 == 1){
                        int j = checkElectronicsId();
                        if(j==-1){
                            System.out.println("Wrong Product ID");
                        }else{
                            a3.get(i).setCartElectronics(a1.get(j));
                            System.out.println("Record entered successfully");
                        }
                    }else if(opt2 == 2){
                        int j = checkClothingId();
                        if(j==-1){
                            System.out.println("Wrong Product ID");
                        }else{
                            a3.get(i).setCartClothing(a2.get(j));
                            System.out.println("Record entered successfully");
                        }
                    }else{
                        System.out.println("Wrong inputted value");
                    }
                }
            }else{
                System.out.println("You are not a User");
            }
        }else if(opt == 2){
            int i = checkRegisterUser();
            if(i==-1){
                System.out.println("Wrong Registered Id");
            }else{
                for(int j=0; j<a4.get(i).cartElectronics.size(); j++){
                    a4.get(i).setOrderElectronics(a4.get(i).cartElectronics.get(j));
                    a4.get(i).cartElectronics.remove(j);
                }
                for(int j=0; j<a4.get(i).cartClothings.size(); j++){
                    a4.get(i).setOrderClothing(a4.get(i).cartClothings.get(j));
                    a4.get(i).cartClothings.remove(j);
                }
                System.out.println("Order Placed Successfully");
            }
        }else{
            System.out.println("Wrong inputted value");
        }
    }
    public static void displayMembers(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Display All Members");
        System.out.println("2. Display Only Registered Members");
        System.out.println("3. Display Only Guest Members");
        System.out.println("Enter the option you want to explore: ");
        int option = sc.nextInt();

        if(option == 1){
            displayRegisteredUser();
            displayGuestUser();
        }else if(option == 2){
            displayRegisteredUser();
        }else if(option == 3){
            displayGuestUser();
        }else{
            System.out.println("Wrong value entered !!");
        }
    }
    public static void displayRegisteredUser(){
        System.out.println("\nRegistered Users are");
        for(int i=0; i<a4.size(); i++){
            a4.get(i).displayDetails();
        }
    }
    public static void displayGuestUser(){
        System.out.println("\nGuest Users are");
        for(int i=0; i<a3.size(); i++){
            a3.get(i).displayDetails();
        }
    }
    public static void displayProducts(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Display All Products");
        System.out.println("2. Display Only Electronic Product");
        System.out.println("3. Display Only Clothing Product");
        System.out.println("Enter the option you want to explore: ");
        int option = sc.nextInt();

        if(option == 1){
            displayElectronics();
            displayClothing();
        }else if(option == 2){
            displayElectronics();
        }else if(option == 3){
            displayClothing();
        }else{
            System.out.println("Wrong value entered !!");
        }
    }
    public static void displayElectronics(){
        System.out.println("All Electronic device in the Inventory\n");
        for(int i=0; i<a1.size(); i++){
            System.out.println("Product Id: " + a1.get(i).getProductId());
            System.out.println("Product Name: " + a1.get(i).getName());
            System.out.println("Product Price: " + a1.get(i).getPrice());
            System.out.println("Product Warranty Period: " + a1.get(i).getWarrantyPeriod());
            System.out.println("Product Power Rating: " + a1.get(i).getPowerSaving());
            System.out.println();
        }
    }
    public static void displayClothing(){
        System.out.println("All Clothing Items in the Inventory\n");
        for(int i=0; i<a2.size(); i++){
            System.out.println("Product Id: " + a2.get(i).getProductId());
            System.out.println("Product Name: " + a2.get(i).getName());
            System.out.println("Product Price: " + a2.get(i).getPrice());
            System.out.println("Product Size: " + a2.get(i).getSize());
            System.out.println("Product Color: " + a2.get(i).getColor());
            System.out.println();
        }
    }
    public static void addProduct(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Electronics");
        System.out.println("2. Clothing");
        System.out.print("Enter the option you want explore: ");
        int option = sc.nextInt();
        if(option == 1){
            a1.add(new Electronics());
        }else if(option == 2){
            a2.add(new Clothing());
        }else{
            System.out.println("Wrong value entered!!");
        }
    }
    static void mainMenu(){
        System.out.println("1. Add a Product");
        System.out.println("2. Add a User");
        System.out.println("3. Shopping Operations");
        System.out.println("4. Exit");
    }
}