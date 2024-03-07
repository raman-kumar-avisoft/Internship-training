import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class minOfMaxTimeToStudy {
    public static void main(String[] args) {
        Scanner scannerInteger = new Scanner(System.in);

        int numberOfChapters = -1;
        int maxWrongTries = 2;
        while(maxWrongTries>0){
            try{
                System.out.print("Enter the Number of Chapters in Syllabus: ");
                numberOfChapters = scannerInteger.nextInt();

                if(numberOfChapters < 1){
                    throw new InputMismatchException();
                }
                maxWrongTries = -1;
            }catch (InputMismatchException inputMismatchException){
                maxWrongTries--;
                if(maxWrongTries==0){
                    System.out.println("too many wrong tries !!");
                    System.exit(0);
                }
                System.out.println("Enter a valid number of chapters");
            }
        }

//        insert the time to complete each chapter
        int[] timeToCompleteChapter = new int[numberOfChapters];

        maxWrongTries = 2;
        System.out.println("Enter the time to complete the chapter sequentially: ");
        for(int timeToCompleteChapterIndex = 0; timeToCompleteChapterIndex  < timeToCompleteChapter.length; timeToCompleteChapterIndex ++){
            try{
                int value = scannerInteger.nextInt();
                timeToCompleteChapter[timeToCompleteChapterIndex] = value;
                if(value < 0){
                    throw new InputMismatchException();
                }
            }catch (InputMismatchException inputMismatchException){
                timeToCompleteChapterIndex--;
                maxWrongTries--;
                if(maxWrongTries==0){
                    System.out.println("too many wrong tries !!");
                    System.exit(0);
                }
                System.out.println("Enter the valid time to complete the chapter !!");
            }
        }

        maxWrongTries = 2;
        int numberOfDays = -1;
        while(maxWrongTries>0){
            try{
                System.out.print("Enter the Number of Days to finish the syllabus: ");
                numberOfDays = scannerInteger.nextInt();

                if(numberOfDays < 1 || numberOfDays >numberOfChapters){
                    throw new InputMismatchException();
                }
                maxWrongTries = -1;
            }catch (InputMismatchException inputMismatchException){
                maxWrongTries--;
                if(maxWrongTries==0){
                    System.out.println("too many wrong tries !!");
                    System.exit(0);
                }
                System.out.println("Enter a valid number of Days to finish off the syllabus");
            }
        }

//        MAIN LOGIC OF THE PROBLEM
        int answer = minTimeToStudyEachDay(timeToCompleteChapter, numberOfDays);
        System.out.println("Minimum Possible time to study particular day till exam day is: " + answer);
    }
    static int minTimeToStudyEachDay(int[] timeToCompleteChapter, int numberOfDays){

        int minDistance = 0;
        for(int timeToCompleteChapterIndex = 0; timeToCompleteChapterIndex < timeToCompleteChapter.length; timeToCompleteChapterIndex++){
            minDistance += timeToCompleteChapter[timeToCompleteChapterIndex];
        }

        int s = 0;
        int e = minDistance;
        while(s<=e){
            int mid = (e-s)/2 + s;

            boolean possibleSolution = verifyCondition(mid, timeToCompleteChapter, numberOfDays);
            if(possibleSolution){
                minDistance = mid;
                e = mid-1;
            }else{
                s = mid +1;
            }
        }
        return minDistance;
    }
    static boolean verifyCondition(int mid, int[] timeToCompleteChapter, int numberOfDays){
        int atLeastTimeToStudy = timeToCompleteChapter[0];
        for(int timeToCompleteChapterIndex = 1; timeToCompleteChapterIndex < timeToCompleteChapter.length; timeToCompleteChapterIndex++){
            if((atLeastTimeToStudy+timeToCompleteChapter[timeToCompleteChapterIndex]) >= mid){
                atLeastTimeToStudy = timeToCompleteChapter[timeToCompleteChapterIndex];
                numberOfDays--;
            }else{
                atLeastTimeToStudy += timeToCompleteChapter[timeToCompleteChapterIndex];
            }
            if(numberOfDays<0){
                return false;
            }
        }
        return true;
    }
}
