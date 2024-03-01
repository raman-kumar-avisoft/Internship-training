import java.util.*;

// Approach to this question is we make a pair of peopleHeight and inFront and then the lowest inFront and minimum people height will be places first in the queue.
// This code is not complete yet.
public class ArrangePersonHeight {
    public static void main(String[] args) {
        Scanner scannerInt = new Scanner(System.in);
        System.out.println("Enter the Number of Person for Testing: ");
        int totalPeople = scannerInt.nextInt();

        int[] peopleHeight = new int[totalPeople];
        System.out.println("Enter the height of the people");
        int wrongInputTries = 3;
        for(int peopleHeightIndex = 0; peopleHeightIndex < totalPeople; peopleHeightIndex++){
            try {
                int value = scannerInt.nextInt();
                peopleHeight[peopleHeightIndex] = value;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                wrongInputTries--;
                peopleHeightIndex--;
                scannerInt.nextInt(); // for consumes the invalid input
            }
        }

        int[] peopleInFront = new int[totalPeople];
        System.out.println("Enter the Number of people are in front of the respective Person");
        for(int peopleInFrontIndex = 0; peopleInFrontIndex < totalPeople; peopleInFrontIndex++){
            try {
                int value = scannerInt.nextInt();
                peopleInFront[peopleInFrontIndex] = value;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                wrongInputTries--;
                peopleInFrontIndex--;
                scannerInt.nextInt(); // for consumes the invalid input
            }
        }

        List<int[]> pairHeight_InFront = new ArrayList<>();
        for(int peopleHeightIndex = 0; peopleHeightIndex < totalPeople; peopleHeightIndex++){
            int[] demoPair = new int[2];
            demoPair[0] = peopleHeight[peopleHeightIndex];
            demoPair[1] = peopleInFront[peopleHeightIndex];
            pairHeight_InFront.add(demoPair);
        }

        Collections.sort(pairHeight_InFront, (a, b) -> Integer.compare(a[1], b[1]));
//        sort with demopair[1]
        for(int peopleHeightIndex = 0; peopleHeightIndex < totalPeople; peopleHeightIndex++){
            System.out.println(pairHeight_InFront.get(peopleHeightIndex)[0] + " : " + pairHeight_InFront.get(peopleHeightIndex)[1] );
        }


        List<Integer> actualOrder = new ArrayList<>();
        // Insert each person at the specified position
//        for (List<int[]> pairHeight_InFrontDemo : pairHeight_InFront) {
            int inFrontPersonCount = person[1];
            for(int peopleInFrontIndex = 0; peopleInFrontIndex < totalPeople; peopleInFrontIndex++){

            }
        }

        // Print the actual order
        System.out.println("Actual Order:");
        for (int height : actualOrder) {
            System.out.print(height + " ");
        }
    }
}