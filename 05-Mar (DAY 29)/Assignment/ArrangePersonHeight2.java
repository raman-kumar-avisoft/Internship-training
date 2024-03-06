import java.util.*;

public class ArrangePersonHeight2 {
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
            int[] pair = new int[2];
            pair[0] = peopleHeight[peopleHeightIndex];
            pair[1] = peopleInFront[peopleHeightIndex];
            pairHeight_InFront.add(pair);
        }

        Collections.sort(pairHeight_InFront, (a, b) -> Integer.compare(a[0], b[0])); // sorted the pairs according to increasing height of people

        for(int peopleHeightIndex = 0; peopleHeightIndex < totalPeople; peopleHeightIndex++){
            System.out.println(pairHeight_InFront.get(peopleHeightIndex)[0] + " : " + pairHeight_InFront.get(peopleHeightIndex)[1] );
        }

        List<Integer> actualOrder = new ArrayList<>(totalPeople);
        for(int actualOrderIndex = 0; actualOrderIndex<totalPeople; actualOrderIndex++){
            actualOrder.add(actualOrderIndex, -1);
        }

        for(int peopleHeightIndex = 0; peopleHeightIndex < totalPeople; peopleHeightIndex++){
            int pos = 0;
            for(int actualOrderIndex = 0; actualOrderIndex<totalPeople; actualOrderIndex++){
                if(actualOrder.get(actualOrderIndex) == -1){
                    if(pos == pairHeight_InFront.get(peopleHeightIndex)[1]){
                        actualOrder.set(actualOrderIndex, pairHeight_InFront.get(peopleHeightIndex)[0]);
                        break;
                    }
                    pos++;
                }
            }
        }

        System.out.println("Answer is : ");
        for(int actualOrderIndex = 0; actualOrderIndex<totalPeople; actualOrderIndex++){
            System.out.print(actualOrder.get(actualOrderIndex) + " ");
        }
    }
}