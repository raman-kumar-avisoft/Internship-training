import java.util.ArrayList;
import java.util.Scanner;

class ReservationSystem{
    ArrayList<Flight> flightArrayList = new ArrayList<>();
    public ReservationSystem(){
        Scanner sc = new Scanner(System.in);
        boolean cond = true;
        while(cond){
            boolean cond2 = true;
            int count = 0;
            do{
                try{
                    displayMainMenu();
                    System.out.println("Select the option out of the following: ");
                    int choice = sc.nextInt();
                    cond2 = false;
                    switch (choice){
                        case 1:
                            System.out.println("Adding a Flight");
                            addFlight();
                            break;
                        case 2:
                            System.out.println("Booking a ticket");
                            bookTicket();
                            break;
                        case 3:
                            System.out.println("Cancelling the ticket");
                            cancelTicket();
                            break;
                        case 4:
                            displayFlightDetails();
                            break;
                        case 5:
                            cond = false;
                            break;
                        default:
                            System.out.println("Select a valid option !!");
                            break;
                    }
                }catch(Exception e){
                    new BankException("Enter a valid Number !!");
                    cond2 = true;
                }
                finally {
                    sc.nextLine();
                    count++;
                }
            }while(cond2 && count<=3);
            if(count == 4){
                System.exit(1);
            }
        }
    }
    void bookTicket(){
        Scanner sc = new Scanner(System.in);
        boolean cond = true;
        int count = 0;
        do{
            try{
                System.out.print("Enter the flight number you want to book tickets in: ");
                int flightNo = sc.nextInt();
                int index = -1;
                for(int i=0; i<flightArrayList.size(); i++){
                    if(flightNo == flightArrayList.get(i).getFlightNumber()){
                        index = i;
                        break;
                    }
                }
                if(index != -1){
                    boolean cond2 = true;
                    int count2 = 0;
                    int ticketAmt=0;
                    do{
                        try{
                            System.out.println("Enter the number of tickets you want to book: ");
                            ticketAmt = sc.nextInt();
                            if(ticketAmt < 0){
                                throw new BankException("Number of tickets cannot be less than 0");
                            }
                            if(ticketAmt <= (flightArrayList.get(index).getAvailableSeats() - flightArrayList.get(index).getBookedSeats())){
                                flightArrayList.get(index).setBookedSeats(flightArrayList.get(index).getBookedSeats() + ticketAmt);
                                cond2 = false;
                                cond = false;
                                System.out.println("Ticket successfully booked");
                            }else{
                                System.out.println("Booking tickets are more than available tickets !!");
                            }
                        }catch(Exception e){
                            new BankException("Not a valid Number !!");
                            cond2 = true;
                        }
                        finally{
                            count2++;
                        }
                    }while(cond2 && count2<=3);
                    if(count2 == 4){
                        System.out.println("Too many wrong inputted values ");
                        System.exit(1);
                    }
                }else{
                    System.out.println("No Flight with this Flight number is present in the DB");
                }
            }catch (Exception e){
                new BankException("Not a valid flight Number !!");
            }finally {
                count++;
            }
        }while(cond && count<=3);

        if(count == 4){
            System.out.println("Too many wrong inputted value !!");
            System.exit(1);
        }
    }
    void cancelTicket(){
        Scanner sc = new Scanner(System.in);
        boolean cond = true;
        int count = 0;
        do{
            try{
                System.out.print("Enter the flight number you want to cancel tickets in: ");
                int flightNo = sc.nextInt();
                int index = -1;
                for(int i=0; i<flightArrayList.size(); i++){
                    if(flightNo == flightArrayList.get(i).getFlightNumber()){
                        index = i;
                        break;
                    }
                }
                if(index != -1){
                    boolean cond2 = true;
                    int count2 = 0;
                    int ticketAmt=0;
                    do{
                        try{
                            System.out.println("Enter the number of tickets you want to cancel: ");
                            ticketAmt = sc.nextInt();
                            if(ticketAmt < 0){
                                throw new BankException("Number of tickets cannot be less than 0");
                            }
                            if(ticketAmt <= flightArrayList.get(index).getBookedSeats()){
                                flightArrayList.get(index).setBookedSeats(flightArrayList.get(index).getBookedSeats() - ticketAmt);
                                cond2 = false;
                                cond = false;
                                System.out.println("Ticket Cancelled successfully");
                            }else{
                                System.out.println("Cancelling tickets are more than booked tickets !!");
                            }
                        }catch(Exception e){
                            new BankException("Not a valid Number !!");
                            cond2 = true;
                        }
                        finally{
                            count2++;
                        }
                    }while(cond2 && count2<=3);
                    if(count2 == 4){
                        System.out.println("Too many wrong inputted values ");
                        System.exit(1);
                    }
                }else{
                    System.out.println("No Flight with this Flight number is present in the DB");
                }
            }catch (Exception e){
                new BankException("Not a valid flight Number !!");
            }finally {
                count++;
            }
        }while(cond && count<=3);

        if(count == 4){
            System.out.println("Too many wrong inputted value !!");
            System.exit(1);
        }

    }
    void displayFlightDetails(){
        System.out.println("\nTotal Flights in the DB are: " + flightArrayList.size());
        System.out.println("There details are as follows: ");
        for(Flight obj: flightArrayList){
            obj.getDetails();
        }
    }
    void addFlight(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Details of the Flight");
        boolean cond = true;
        int count =0;
        do{
            try{
                System.out.print("Enter the Flight Number: ");
                int flightNumber = sc.nextInt();
                boolean goThrough = true;
                for(Flight obj: flightArrayList){
                    if(obj.getFlightNumber() == flightNumber){
                        goThrough = false;
                        break;
                    }
                }
                if(goThrough){
                    flightArrayList.add(new Flight(flightNumber));
                    cond = false;
                }else{
                    System.out.println("Flight already exists with this flight number !!");
                    cond = true;
                }
            }catch(Exception e){
                new BankException("Enter a Valid Number !!");
            }
            finally {
                sc.nextLine();
                count++;
            }
        }while(cond && count<=3);
        if(count == 4){
            System.out.println("Too many wrong Outputs !!");
            System.exit(1);
        }
    }
    void displayMainMenu(){
        System.out.println("\n1. Add a Flight");
        System.out.println("2. Book a Flight Ticket (Reserving)");
        System.out.println("3. Cancelling a Ticket (Cancelling)");
        System.out.println("4. Display Flights Details");
        System.out.println("5. Exit");
    }
}