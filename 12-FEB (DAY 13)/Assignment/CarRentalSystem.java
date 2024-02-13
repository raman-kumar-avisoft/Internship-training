import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

abstract class Vehicle{
        static int id = 1;
        private int uniqueId;
       private String maker;
       private String model;
       private int year;
       private double rentalPrice;
       Vehicle(){
           Scanner sc = new Scanner(System.in);
           System.out.println("Enter the details for entry of the vehicle in the DB");
           setUniqueId();
           System.out.print("Maker of the Vehicle: ");
           setMaker(sc.nextLine());
           System.out.print("Model of the Vehicle: ");
           setModel(sc.nextLine());
           System.out.print("Year of the Vehicle Manufactured: ");
           setYear(sc.nextInt());
           sc.nextLine();
           System.out.print("Enter the Rental Price of the Vehicle: ");
           setRentalPrice(sc.nextDouble());
       }
       void setUniqueId(){
           this.uniqueId = id++;
       }
       void setUniqueId(int i){
           this.uniqueId = i;
       }
       void setMaker(String data){
           this.maker = data;
       }
       void setModel(String data){
           this.model = data;
       }
       void setYear(int data){
           this.year = data;
       }
       void setRentalPrice(double data){
           this.rentalPrice = data;
       }
       int getUniqueId(){
           return this.uniqueId;
       }
       String getMaker(){
           return this.maker;
       }
       String getModel(){
            return this.model;
       }
       int getYear(){
           return this.year;
       }
       double getRentalPrice(){
           return this.rentalPrice;
       }
}
class Car extends Vehicle{
    Scanner sc = new Scanner(System.in);
    private int numSeats;
    private String fuelType;

    Car(){
        super();
        System.out.print("Enter the number of seats: ");
        setNumSeats(sc.nextInt());
        sc.nextLine();
        System.out.print("Enter the Fuel Type: ");
        setFuelType(sc.nextLine());
    }
    Car(Car obj){
        this.setUniqueId(obj.getUniqueId());
        this.setMaker(obj.getMaker());
        this.setModel(obj.getModel());
        this.setFuelType(obj.getFuelType());
        this.setNumSeats(obj.getNumSeats());
        this.setYear(obj.getYear());
        this.setRentalPrice(obj.getRentalPrice());
    }
    void setNumSeats(int data){
        this.numSeats = data;
    }
    void setFuelType(String data){
        this.fuelType = data;
    }
    int getNumSeats(){
        return this.numSeats;
    }
    String getFuelType(){
        return this.fuelType;
    }
}
class OtherVehicle extends Vehicle{
    Scanner sc = new Scanner(System.in);
    private int numSeats;
    private String fuelType;

    OtherVehicle(){
        super();
        System.out.print("Enter the number of seats: ");
        setNumSeats(sc.nextInt());
        sc.nextLine();
        System.out.print("Enter the Fuel Type: ");
        setFuelType(sc.nextLine());
    }
    OtherVehicle(OtherVehicle obj){
        this.setUniqueId(obj.getUniqueId());
        this.setMaker(obj.getMaker());
        this.setModel(obj.getModel());
        this.setFuelType(obj.getFuelType());
        this.setNumSeats(obj.getNumSeats());
        this.setYear(obj.getYear());
        this.setRentalPrice(obj.getRentalPrice());
    }
    void setNumSeats(int data){
        this.numSeats = data;
    }
    void setFuelType(String data){
        this.fuelType = data;
    }
    int getNumSeats(){
        return this.numSeats;
    }
    String getFuelType(){
        return this.fuelType;
    }
}
class Customer{
    private String name;
    private static  int uniqueId = 0;
    private String email;
    private double rentalCost;
    private ArrayList<Car> rentedCar;
    private ArrayList<Car> returnedCar;
    private ArrayList<OtherVehicle> rentedOtherVehicle;
    private ArrayList<OtherVehicle> returnedOtherVehicle;

    Customer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the details of the Customer");
        setUniqueId();
        System.out.print("Enter the name of the customer: ");
        setName(sc.nextLine());
        System.out.print("Enter the email of the customer: ");
        setEmail(sc.nextLine());
        rentalCost = 0;
    }
    void displayDetails(){
        System.out.println("Unique Id: " + this.uniqueId);
        System.out.println("Name is: " + this.name);
        System.out.println("Email is: " + this.email);
        System.out.println("Rental Cost: " + this.rentalCost);
        System.out.println();
    }
    void setUniqueId(){
        this.uniqueId = ++uniqueId;
    }
    void setName(String data){
        this.name = data;
    }
    void setEmail(String data){
        this.email = data;
    }
    void setRentedCar(Car obj){
        this.rentedCar.add(obj);
        this.rentalCost += obj.getRentalPrice();
    }
    void setRentedOtherVehicle(OtherVehicle obj){
        this.rentedOtherVehicle.add(obj);
        this.rentalCost -= obj.getRentalPrice();
    }
    int getUniqueId(){
        return this.uniqueId;
    }
    String getName(){
        return this.name;
    }
    String getEmail(){
        return this.email;
    }
    ArrayList<Car> getRentedCar(){
        return this.rentedCar;
    }
    ArrayList<OtherVehicle> getRentedOtherVehicle(){
        return this.rentedOtherVehicle;
    }
}
class RentalAgencyClass extends Customer{
    int rentalCost;

}
public class CarRentalSystem {
    static ArrayList<OtherVehicle> otherVehicleArrayList = new ArrayList<>();
    static ArrayList<Car> carArrayList = new ArrayList<>();
    static ArrayList<Customer> customerArrayList = new ArrayList<>();
    public static void main(String[] args) {

        System.out.println("WELCOME TO ONLINE CAR RENTAL SYSTEM !!");
        Scanner sc = new Scanner(System.in);
        boolean cond = true;

        while(cond){
            displayMainMenu();
            System.out.println("Choose one of the option from above");
            int op = sc.nextInt();

            switch (op){
                case 1:
                    addVehicle();
                    break;
                case 2:
                    System.out.println("1. Remove Cars");
                    System.out.println("2. Remove Other Vehicle");
                    System.out.println("Select the option out of following: ");
                    int op2 = sc.nextInt();

                    if(op2 == 1){
                        System.out.print("enter the unique id: ");
                        int i = checkCar(sc.nextInt());
                        if(i == -1){
                            System.out.println("WRONG UNIQUE ID OF THE CAR VEHICLE");
                        }else{
                            Car obj = removeCarVehicle(i);
                        }

                    }else if(op2 == 2){
                        System.out.print("enter the unique id: ");
                        int i = checkOtherVehicle(sc.nextInt());
                        if(i == -1){
                            System.out.println("WRONG UNIQUE iD OF THE OTHER VEHICLE");
                        }else{
                            OtherVehicle obj = removeOtherVehicle(i);
                        }
                    }else{
                        System.out.println("Wrong inputted value !!");
                    }
                    break;

                case 3:
                    System.out.println("1. Cars");
                    System.out.println("2. Other Vehicles");
                    System.out.println("3. Customers");
                    System.out.println("Select your choice: ");
                    int op3 = sc.nextInt();
                    if(op3 == 1){
                        displayCars();
                    }else if(op3 == 2){
                        displayOtherVehicle();
                    }else if(op3 == 3){
                        displayCustomer();
                    }else{
                        System.out.println("Wrong inputted value !!");
                    }
                    break;

                case 4:
                    System.out.println("Adding a Customer: ");
                    customerArrayList.add(new Customer());
                    break;

                case 5:
                    int i = checkCustomer();
                    if(i == -1){
                        System.out.println("No user found with this Id");
                    }
                    else{
                        System.out.println("Rental System");
                        System.out.println("1. Rent a Car");
                        System.out.println("2. Return a Car");
                        System.out.println("Select the option out of the following");
                        int op4 = sc.nextInt();

                        if(op4 == 1){

                        }else if(op4 == 2){

                        }else{
                            System.out.println("Wrong input selected !!");
                        }
                    }
                    break;

                case 6:
                    cond = false;
                    break;

                default:
                    System.out.println("Wrong value inputted !!");
                    break;
            }
        }
    }
    static int checkCustomer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the unique Id of customer: ");
        int val = sc.nextInt();
        sc.nextLine();

        for(int i=0; i<customerArrayList.size(); i++){
            if(val == customerArrayList.get(i).getUniqueId()){
                return i;
            }
        }
        return -1;
    }
    static void displayCustomer(){
        System.out.println("\nDetailed details of the customers: ");
        for(int i=0; i<customerArrayList.size(); i++){
            customerArrayList.get(i).displayDetails();
        }
    }
    static Car removeCarVehicle(int index){
        Car obj = carArrayList.get(index);
        carArrayList.remove(index);
        return obj;
    }
    static OtherVehicle removeOtherVehicle(int index){
        OtherVehicle obj = otherVehicleArrayList.get(index);
        otherVehicleArrayList.remove(index);
        return obj;
    }
    static int checkCar(int uid){
        for(int i=0; i<carArrayList.size(); i++){
            if(uid == carArrayList.get(i).getUniqueId()){
                return i;
            }
        }
        return -1;
    }
    static int checkOtherVehicle(int uid){
        for(int i=0; i<otherVehicleArrayList.size(); i++){
            if(uid == otherVehicleArrayList.get(i).getUniqueId()){
                return i;
            }
        }
        return -1;
    }
    static void displayCars() {
        System.out.println();
        for(int i=0; i<carArrayList.size(); i++){
            System.out.println("Unique Id: "+carArrayList.get(i).getUniqueId());
            System.out.println("Maker: "+carArrayList.get(i).getMaker());
            System.out.println("Model: "+carArrayList.get(i).getModel());
            System.out.println("Manufacturing Year: "+carArrayList.get(i).getYear());
            System.out.println("Fuel Type: "+carArrayList.get(i).getFuelType());
            System.out.println("No. of Seats: "+carArrayList.get(i).getNumSeats());
            System.out.println("Rental Price: "+carArrayList.get(i).getRentalPrice());
            System.out.println();
        }
    }
    static void displayOtherVehicle(){
        System.out.println();
        for(int i=0; i<otherVehicleArrayList.size(); i++){
            System.out.println("Unique Id: "+otherVehicleArrayList.get(i).getUniqueId());
            System.out.println("Maker: "+otherVehicleArrayList.get(i).getMaker());
            System.out.println("Model: "+otherVehicleArrayList.get(i).getModel());
            System.out.println("Manufacturing Year: "+otherVehicleArrayList.get(i).getYear());
            System.out.println("Fuel Type: "+otherVehicleArrayList.get(i).getFuelType());
            System.out.println("No. of Seats: "+otherVehicleArrayList.get(i).getNumSeats());
            System.out.println("Rental Price: "+otherVehicleArrayList.get(i).getRentalPrice());
            System.out.println();
        }
    }
    static void addVehicle(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Adding section of the Car Rental System");
        System.out.println("1. For Adding a CAR");
        System.out.println("2. For Adding any other vehicle");

        int op = sc.nextInt();
        if(op == 1){
            carArrayList.add(new Car());
//            new Car();
            System.out.println("Successfully addition of the new car ");
        }else if(op == 2){
            otherVehicleArrayList.add(new OtherVehicle());
            System.out.println("Successfully addition of the new vehicle ");
        }else{
            System.out.println("Wrong inputted value !!");
        }
    }
    static void displayMainMenu(){
        System.out.println("1. Add a Vehicle");
        System.out.println("2. Remove a Vehicle");
        System.out.println("3. Display");
        System.out.println("4. Add a Customer");
        System.out.println("5. Rental Car");
        System.out.println("6. Exit");
    }
}