import java.util.*;

public class DistinctNumberInEachWindow {
    public static int[] countDistinct(int[] array, int windowSize) {
        int N = array.length;
        if (windowSize > N) {
            return new int[0];
        }

        int[] result = new int[N - windowSize + 1];
        Map<Integer, Integer> window = new HashMap<>();

        // Initialize window
        for (int i = 0; i < windowSize; i++) {
            window.put(array[i], window.getOrDefault(array[i], 0) + 1);
        }

        result[0] = window.size();

        // Slide the window
        for (int i = 1; i <= N - windowSize; i++) {
            int left = array[i - 1];
            int right = array[i + windowSize - 1];

            window.put(right, window.getOrDefault(right, 0) + 1);
            if (window.get(left) == 1) {
                window.remove(left);
            } else {
                window.put(left, window.get(left) - 1);
            }

            result[i] = window.size();
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);

        int arraySize = -1, maxWrongTries =3;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the size of the array: ");
                arraySize = scannerInteger.nextInt();

                if(arraySize <= 0){
                    throw new InputMismatchException();
                }
                break;
            }catch (InputMismatchException inputMismatchException){
                scannerInteger.nextLine();
                System.out.println("NOT A VALID ARRAY SIZE **");
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }

        int[] array = new int[arraySize];
        for(int arrayIndex=0; arrayIndex < arraySize; arrayIndex++){
            maxWrongTries = 3;
            while(--maxWrongTries >= 0){
                try{
                    System.out.print("Enter the value of element at index [" + arrayIndex + "]: ");
                    int value = scannerInteger.nextInt();
                    array[arrayIndex] = value;
                    break;
                }catch (InputMismatchException inputMismatchException){
                    scannerInteger.nextLine();
                    System.out.println("NOT A VALID ELEMENT VALUE **");
                }
            }
            if(maxWrongTries < 0){
                System.out.println("TOO MANY WRONG TRIES **");
                System.exit(0);
            }
        }

        int windowSize = -1;
        maxWrongTries =3;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the size of the Window: ");
                windowSize = scannerInteger.nextInt();

                if(windowSize <= 0 || windowSize > arraySize){
                    throw new InputMismatchException();
                }
                break;
            }catch (InputMismatchException inputMismatchException){
                scannerInteger.nextLine();
                System.out.println("NOT A VALID WINDOW SIZE **");
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES **");
            System.exit(0);
        }

        int[] answer = countDistinct(array, windowSize);
        for(int value: answer){
            System.out.println(value + " ");
        }
    }
}

