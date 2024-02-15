import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;

class Flight{
    private int flightNumber;
    private String departureCity;
    private String destinationCity;
    private int bookedSeats;
    private int availableSeats;
    static ArrayList<String> totalCities = new ArrayList<String>();

    public Flight(int flightNumber){
        totalCities.add("chandigarh");
        totalCities.add("bangalore");
        totalCities.add("pune");
        totalCities.add("new delhi");
        totalCities.add("mumbai");
        totalCities.add("indore");
        totalCities.add("dhaka");
        totalCities.add("malaysia");
        totalCities.add("maldives");

        setDetails(flightNumber);
    }
    void setDetails(int flightNumber){
        Scanner sc = new Scanner(System.in);
        setFlightNumber(flightNumber);
        setBookedSeats(0);
        boolean cond = true;
        int count = 0;
        while(cond && count<3){
            System.out.print("Enter the Departure City: ");
            cond = !setDepartureCity(sc.nextLine());
            count++;
        }
        if(count == 3){
            System.out.println("TOO many wrong inputted value !!");
            System.exit(1);;
        }
        cond = true;
        count = 0;
        while(cond && count<3){
            System.out.print("Enter the Destination City: ");
            cond = !setDestinationCity(sc.nextLine());
            count++;
        }
        if(count == 3){
            System.out.println("TOO many wrong inputted value !!");
            System.exit(1);;
        }
        System.out.print("Enter the Available Seats: ");
        cond = true;
        count = 0;
        do{
            try{
                setAvailableSeats(sc.nextInt());
                if(this.availableSeats >= 10 && this.availableSeats <= 853){
                    cond = false;
                }else{
                    System.out.println("Enter within the maximum limit of aircraft to carry passengers");
                }
            }catch(Exception e){
                new BankException("Enter a valid number !!");
            }finally {
                sc.nextLine();
                count++;
            }
        }while(cond && count<=3);
        if(count == 4){
            System.out.println("Too many wrong inputted value !!");
            System.exit(1);
        }

    }
    void setBookedSeats(int bookedSeats){
        this.bookedSeats = bookedSeats;
    }
    int getBookedSeats(){
        return this.bookedSeats;
    }
    void getDetails(){
        System.out.println("\nThe Flight Number is: " + this.flightNumber);
        System.out.println("The Departure City is: " + this.departureCity);
        System.out.println("The Destination City is: " + this.destinationCity);
        System.out.println("The Booked Seats are: " + this.bookedSeats);
        System.out.println("Available Seats are: " + this.availableSeats);
    }
    boolean setFlightNumber(int flightNumber){
        this.flightNumber = flightNumber;
        return true;
    }
    boolean setDepartureCity(String departureCity){
        if(totalCities.contains(departureCity) == true){
            this.departureCity = departureCity;
            return true;
        }else{
            System.out.println("The city is not in the ArrayList of Cities !!");
            return false;
        }
    }
    boolean setDestinationCity(String destinationCity){
        if(totalCities.contains(destinationCity) && destinationCity.equals(this.getDepartureCity())){
            System.out.println("Departure and Destination cities cannot be same !!");
            return false;
        }else if(totalCities.contains(destinationCity)){
            this.destinationCity = destinationCity;
            return true;
        }else{
            System.out.println("The city is not in the ArrayList of Cities !!");
            return false;
        }
    }
    boolean setAvailableSeats(int availableSeats){
        this.availableSeats = availableSeats;
        return true;
    }
    int getFlightNumber(){
        return this.flightNumber;
    }
    String getDepartureCity(){
        return this.departureCity;
    }
    String getDestinationCity(){
        return this.destinationCity;
    }
    int getAvailableSeats(){
        return this.availableSeats;
    }
}