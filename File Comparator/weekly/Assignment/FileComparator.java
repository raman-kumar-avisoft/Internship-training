package weekly.Assignment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FileComparator {
    public static void main(String[] args) {
        Scanner scInt = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);

        int count = 3;
        while (count > 0) {
            displayMainMenu();
            try {
                System.out.print("Enter the type of file you want to compare: ");
                int choice = scInt.nextInt();

                switch (choice) {
                    case 1:
//                String path = "C:\\Users\\Raman\\OneDrive\\Desktop\\actual.txt";
                        System.out.println("Comparing text files");
                        System.out.print("Enter the path of TXT file: ");
                        String actualTextFilePath = sc.nextLine();
                        TextFile actualTextFileObject = new TextFile(actualTextFilePath);

                        System.out.print("Enter the path of TXT file: ");
                        String expectedTextFilePath = sc.nextLine();
                        TextFile expectedTextFileObject = new TextFile(expectedTextFilePath);

                        actualTextFileObject.fileComparison(expectedTextFileObject);
                        break;
                    case 2:
//                String path = "C:\\Users\\Raman\\OneDrive\\Desktop\\actual.csv";
                        System.out.println("Comparing Csv files");
                        System.out.print("Enter the path of the CSV file: ");
                        String actualCsvFilePath = sc.nextLine();
                        CsvFile actualCsvFileObject = new CsvFile(actualCsvFilePath);

                        System.out.print("Enter the path of the CSV file: ");
                        String expectedCsvFilePath = sc.nextLine();
                        CsvFile expectedCsvFileObject = new CsvFile(expectedCsvFilePath);

                        System.out.print("If you want to leave any column which you don't want to compare: ");
                        int leaveColumn = scInt.nextInt();

                        boolean same = actualCsvFileObject.fileComparison(expectedCsvFileObject, leaveColumn);
                        if (same) {
                            System.out.println("Both files are same");
                        } else {
                            createNewFile(actualCsvFileObject);
                        }
                        break;
                    case 3:
                        System.out.println("Thank You");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Wrong value inputted");
                        break;
                }
                count = 0;
            } catch (InputMismatchException e) {
                System.out.println("Wrong input value entered");
            } finally {
                count--;
                scInt.nextLine();
            }
        }
    }

    static void createNewFile(CsvFile actualCsvObject) {
        Timestamp instant = Timestamp.from(java.time.Instant.now());
        Date date = new Date(instant.getTime());

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH-mm-ss");

        String formattedDate = sdfDate.format(date);
        String formattedTime = sdfTime.format(date);

        String fName = "output_" + formattedDate + formattedTime;

        try {
            String fileName = "C:\\Users\\Raman\\OneDrive\\Desktop\\" + fName + ".csv";
            File file = new File(fileName);
            if(file.createNewFile()){
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter output = new BufferedWriter(fileWriter);
                output.write("Index,Actual,Expected\n");
                for (int corruptFileDataIndex = 0; corruptFileDataIndex < actualCsvObject.getCorruptFileData().size(); corruptFileDataIndex++) {
                    String line = actualCsvObject.getCorruptFileData().get(corruptFileDataIndex);
                    System.out.println(line);
                    output.write(line + "\n");
                }
                output.flush();
                System.out.println("File created successfully");
                output.close();
            }else{
                System.out.println("File already Exists");
            }
        } catch (Exception e) {
            System.out.println("FileWriter exception !!");
        }
    }

    static void displayMainMenu() {
        System.out.println();
        System.out.println("1. Txt Files");
        System.out.println("2. Csv Files");
        System.out.println("3. Exit");
    }
}
