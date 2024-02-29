package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PerformOperationOnCsv {
    public static void main(String[] args) {
        ArrayList<String[]> rowData = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the csv file path in which you want to perform operation: ");
        String csvFileLocation = scanner.next();

        try{
            File file = new File(csvFileLocation);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

//                String path = "C:\\Users\Raman\\OneDrive\\Desktop\\JAVA INTERSHIOP PROGRAM\\22-FEB (DAY 21)\\Assignment\\actual.csv";
            String row = "";
            String[] columnName = bufferedReader.readLine().split(",");
            while((row = bufferedReader.readLine()) != null){ // fileReader.read() returns boolean value if
                String[] rowWords = row.split(",");
                rowData.add(rowWords);
            }
            bufferedReader.close();

            File fileOutput = new File("C:\\Users\\Raman\\OneDrive\\Desktop\\PerformOperationOnCsv.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileOutput));

            for(int rowDataIndex = 0; rowDataIndex < rowData.size(); rowDataIndex++){
                rowData.get(rowDataIndex)[1] += " " +rowData.get(rowDataIndex)[2];
            }
            String heading = "";
            for(int columnNameIndex = 0; columnNameIndex < columnName.length; columnNameIndex++){
                if(columnNameIndex == 1){
                    heading += "Full Name,";
                }else if(columnNameIndex == 2){
                    continue;
                }
                else{
                    if(columnNameIndex == columnName.length -1){
                        heading += columnName[columnNameIndex]+"\n";
                    }else{
                        heading += columnName[columnNameIndex]+",";
                    }
                }
            }
            bufferedWriter.write(heading);
            for(int rowDataIndex = 0; rowDataIndex < rowData.size(); rowDataIndex++){
                String line = "";
                for(int columnNameIndex = 0; columnNameIndex < columnName.length; columnNameIndex++){
                    if(columnNameIndex == 2){
                        continue;
                    }else if(columnNameIndex == columnName.length -1){
                        line += rowData.get(rowDataIndex)[columnNameIndex];
                    }else{
                        line += rowData.get(rowDataIndex)[columnNameIndex]+",";
                    }
                }
                bufferedWriter.write(line+"\n");
            }
            bufferedWriter.close();

            System.out.println("File Operation done check your file");

        }catch (IOException e){
            System.out.println("Exception caught !!");
        }
    }
}
