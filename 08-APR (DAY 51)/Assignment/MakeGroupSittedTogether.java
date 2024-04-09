import java.util.InputMismatchException;
import java.util.Scanner;

public class MakeGroupSittedTogether {
    public static void main(String[] args) {
        Scanner scannerString = new Scanner(System.in);
        int maxWrongTries = 3;

        String seats = "";
        while(--maxWrongTries >= 0){
            try{
                System.out.println("Enter the seating postion(. -> Representing a empty string and *-> represent an occupied string: ");
                seats = scannerString.next();

                for(int seatsIndex = 0; seatsIndex<seats.length(); seatsIndex++){
                    if(seats.charAt(seatsIndex) != '.' && seats.charAt(seatsIndex) != '*'){
                        throw new InputMismatchException();
                    }
                }
                break;
            }catch (InputMismatchException inputMismatchException){
                System.out.println("NOT A VALID SEATING ORDER **");
            }
        }

        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }

        int answer = minJumps(seats);
        if(answer == -1){
            System.out.println("CANNOT MAKE THE PEOPLE SEAT TOGETHER **");
        }else{
            System.out.println("CAN GROUP TOGETHER PEOPLE IN "+answer+" HOPS.");
        }
    }

    public static int minJumps(String seats){
        int occupiedSeats = 0;
        int n = seats.length();

        // Count the total number of occupied seats
        for (int i = 0; i < n; i++) {
            if (seats.charAt(i) == 'x') {
                occupiedSeats++;
            }
        }

        int windowSize = 0;
        // Calculate the window size required to contain all occupied seats
        for (int i = 0; i < occupiedSeats; i++) {
            if (seats.charAt(i) == 'x') {
                windowSize++;
            }
        }

        int minJumps = Integer.MAX_VALUE;
        int jumps = 0;

        // Slide the window through the row
        for (int i = 0, j = windowSize; j <= n; i++, j++) {
            int occupiedInWindow = 0;
            // Count occupied seats within the window
            for (int k = i; k < j; k++) {
                if (seats.charAt(k) == 'x') {
                    occupiedInWindow++;
                }
            }
            // Calculate jumps required to move people to fit within the window
            jumps = occupiedSeats - occupiedInWindow;
            minJumps = Math.min(minJumps, jumps);

            if (j < n && seats.charAt(i) == 'x') {
                occupiedSeats--;
            }
            if (j < n && seats.charAt(j) == 'x') {
                occupiedSeats++;
            }
        }

        // Return the minimum number of jumps
        return minJumps;
    }
}