package online.voting.system;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin {
    Admin(){
        adminMainMenu();
    }
    void adminMainMenu(){
        Scanner scannerInteger = new Scanner(System.in);

        boolean continueLooping = true;
        int maxWrongTries = 3;

        while(continueLooping){
            try{
                displayAdminMainMenu();
                System.out.print("Select the choice out of following: ");
                int choice = scannerInteger.nextInt();

                switch (choice) {
                    case 1:
                        adminLoginMenu();
                        maxWrongTries = 3;
                        break;
                    case 2:
                        boolean loginStatus = logInAdmin();
                        if(loginStatus){
                            System.out.println("LogIn Successfully");
                            viewElectionResults();
                        }else{
                            System.out.println("LOGIN UNSUCCESSFULLY **");
                        }
                        maxWrongTries = 3;
                        break;
                    case 3:
                        continueLooping = false;
                        break;
                    default:
                        throw new CustomException("NOT A VALID CHOICE **");
                }
            }catch (InputMismatchException inputMismatchException){
                System.out.println("NOT A VALID NUMBER **");
            }catch (CustomException customException){
                System.out.println(customException.getMessage());
            }finally {
                if(maxWrongTries-- == 0){
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }
    }
    void viewElectionResults(){
        new Election(true);
    }
    void displayAdminMainMenu(){
        System.out.println("1. Only For Admins");
        System.out.println("2. Election Results");
        System.out.println("3. Go back to Main Menu");
    }
    void adminLoginMenu(){
        Scanner scannerInteger = new Scanner(System.in);

        int maxWrongTries = 3;
        boolean continueLooping = true;
        while(continueLooping){
            try{
                displayAdminLoginMainMenu();
                System.out.print("Select the choice out of following: ");
                int choice = scannerInteger.nextInt();

                switch (choice) {
                    case 1:
                        registerAdmin();
                        maxWrongTries = 3;
                        break;
                    case 2:
                        boolean loginStatus = logInAdmin();
                        if(loginStatus){
                            System.out.println("LogIn Successfully");
                        }else{
                            System.out.println("LOGIN UNSUCCESSFULLY **");
                        }
                        maxWrongTries = 3;
                        break;
                    case 3:
                        boolean passwordResetStatus = changeAdminPassword();
                        if(passwordResetStatus){
                            System.out.println("Password Reset Successfully");
                        }else{
                            System.out.println("PASSWORD RESET UNSUCCESSFULLY **");
                        }
                        maxWrongTries = 3;
                        break;
                    case 4:
                        continueLooping = false;
                        break;
                    default:
                        throw new CustomException("NOT A VALID OPTION **");
                }
            }catch (InputMismatchException inputMismatchException){
                System.out.println("NOT A VALID NUMBER **");
            }catch (CustomException customException){
                System.out.println(customException.getMessage());
            }finally {
                if(maxWrongTries-- == 0){
                    System.out.println("too many wrong tries !!");
                    continueLooping = false;
                }
            }
        }
    }
    void displayAdminLoginMainMenu(){
        System.out.println("1. Register a Admin");
        System.out.println("2. Already a Admin");
        System.out.println("3. Change Password of Admin");
        System.out.println("4. Go back in Online Voting System");
    }
    void registerAdmin(){
        Scanner scannerString = new Scanner(System.in);

        File actualAdminFile = null;
        File actualAdminFileCopy = null;
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        int maxWrongTries = 3;
        boolean continueLooping = true;
        boolean adminFound = false;
        while (continueLooping){
            try{
                String empId, password;
                System.out.print("Enter the employee Id: ");
                empId = scannerString.next();

                if(empId.length() != 4){
                    throw new CustomException("EMPLOYEE ID MUST BE OF 4 CHARACTERS **");
                }else if(empId.charAt(0) != 'A' || empId.charAt(1) != 'S'){
                    throw new CustomException("EMPLOYEE ID FIRST TWO CHARACTERS MUST BE OF CAPITAL LETTERS **");
                }else if(!(empId.charAt(2) >= '0' && empId.charAt(2) <= '9') || !(empId.charAt(3) >= '0' && empId.charAt(3) <= '9')){
                    throw new CustomException("EMPLOYEE ID LAST TWO CHARACTERS MUST BE OF DIGITS **");
                }

                System.out.print("Enter the password: ");
                password = scannerString.next();

                if(password.length() <= 6){
                    throw new CustomException("PASSWORD LENGTH MUST BE GREATER THAN 6 CHARACTERS **");
                }

                // Now we will read data from actualAdmin and check if admin is already registered else we will register the admin.
                actualAdminFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualAdmin.csv");
                actualAdminFileCopy = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualAdminCopy.csv");
                boolean actualAdminFileCreated = actualAdminFileCopy.createNewFile();
                if(!actualAdminFileCreated){
                    throw new CustomException("ACTUAL ADMIN COPY NOT CREATED **");
                }

                fileReader = new FileReader(actualAdminFile);
                fileWriter = new FileWriter(actualAdminFileCopy);
                bufferedReader = new BufferedReader(fileReader);
                bufferedWriter = new BufferedWriter(fileWriter);

                boolean lineMatched = true;
                String row;
                while((row = bufferedReader.readLine()) != null){                                                       // bufferReader.readLine() returns null if EOF.
                    String[] rowWords = row.split(",");
                    if(rowWords[1].equals(empId)){
                        adminFound = true;
                        System.out.println(rowWords.length);
                        if(!rowWords[2].equals(" ")){
                            lineMatched = false;
                            StringBuilder rowData = new StringBuilder();
                            for(int rowWordsIndex = 0; rowWordsIndex < rowWords.length; rowWordsIndex++){
                                if(rowWordsIndex != rowWords.length -1 ){
                                    rowData.append(rowWords[rowWordsIndex]).append(",");
                                }else{
                                    rowData.append(rowWords[rowWordsIndex]).append("\n");
                                }
                            }
                            bufferedWriter.write(rowData.toString());
                        }else{
                            StringBuilder rowData = new StringBuilder();
                            for(int rowWordsIndex = 0; rowWordsIndex < rowWords.length; rowWordsIndex++){
                                if(rowWordsIndex == 2){
                                    rowData.append("true,");
                                }else if(rowWordsIndex == 3){
                                    rowData.append(password).append("\n");
                                }else{
                                    rowData.append(rowWords[rowWordsIndex]).append(",");
                                }
                            }
                            bufferedWriter.write(rowData.toString());
                        }
                    }else{
                        StringBuilder rowData = new StringBuilder();
                        for(int rowWordsIndex = 0; rowWordsIndex < rowWords.length; rowWordsIndex++){
                            if(rowWordsIndex != rowWords.length -1 ){
                                rowData.append(rowWords[rowWordsIndex]).append(",");
                            }else{
                                rowData.append(rowWords[rowWordsIndex]).append("\n");
                            }
                        }
                        bufferedWriter.write(rowData.toString());
                    }
                }
                if(!lineMatched){
                    throw new CustomException("EMPLOYEE IS ALREADY REGISTERED **");
                }else if(!adminFound){
                    throw new CustomException("EMPLOYEE ID NOT FOUND **");
                }

                continueLooping = false;

            } catch(CustomException customException){
                System.out.println(customException.getMessage());
            } catch(EOFException eofException){
                System.out.println("ACTUAL ADMIN FILE IS EMPTY **");
            } catch (IOException ioException){
                System.out.println("IOEXCEPTION CAUGHT WHILE USING ACTUAL ADMIN FILE **");
            } finally {
                try{
                    if(bufferedReader != null){
                        bufferedReader.close();
                    }
                    if(bufferedWriter != null){
                        bufferedWriter.close();
                    }
                    if(fileReader != null){
                        fileReader.close();
                    }
                    if(fileWriter != null){
                        fileWriter.close();
                    }
                    if(actualAdminFile != null && actualAdminFileCopy != null){
                        boolean actualAdminFileDeleted = actualAdminFile.delete();
                        boolean actualAdminFileCopyRenamed = actualAdminFileCopy.renameTo(new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualAdmin.csv"));
                        if(!actualAdminFileDeleted || !actualAdminFileCopyRenamed){
                            throw new CustomException("FILE NAME NOT DELETED AND RENAMED **");
                        }
                    }
                }catch (IOException ioException){
                    System.out.println("IOEXCEPTION CAUGHT WHILE CLOSING ACTUAL ADMIN FILE **");
                }catch (CustomException customException){
                    System.out.println(customException.getMessage());
                }

                if(maxWrongTries-- == 0){
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }
    }
    boolean logInAdmin(){
        Scanner scannerString = new Scanner(System.in);
        boolean continueLooping = true;
        int maxWrongTries = 3;
        while(continueLooping){
            try{
                System.out.print("Enter the Login ID(Emp ID): ");
                String empId = scannerString.next();

                System.out.print("Enter the password: ");
                String password = scannerString.next();

                File actualAdminFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualAdmin.csv");
                FileReader fileReader = new FileReader(actualAdminFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String row;
                while((row = bufferedReader.readLine()) != null) {
                    String[] rowData = row.split(",");
                    for(int rowDataIndex = 0; rowDataIndex < rowData.length; rowDataIndex++){
                        if( rowData[1].equals(empId) && rowData[3].equals(password)){
                            return true;
                        }
                    }
                }
                return false;
            }catch (IOException ioException){
                System.out.println("IOEXCEPTION CAUGHT WHILE USING ACTUAL ADMIN FILE **");
            }
            finally {
                if(maxWrongTries-- == 0){
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }
        return false;
    }
    boolean changeAdminPassword(){
        Scanner scannerString = new Scanner(System.in);

        File actualAdminFile = null;
        File actualAdminFileCopy = null;
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        boolean continueLooping = true;
        int maxWrongTries = 3;
        while(continueLooping){
            try {
                System.out.print("Enter the Login ID(Emp ID): ");
                String empId = scannerString.next();

                System.out.print("Enter the Current Password: ");
                String currentPassword = scannerString.next();

                System.out.print("Enter the New Password: ");
                String newPassword = scannerString.next();
                if (newPassword.length() <= 6) {
                    throw new CustomException("LENGTH OF NEW PASSWORD MUST BE GREATER THAN 6 CHARACTERS **");
                }

                actualAdminFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualAdmin.csv");
                actualAdminFileCopy = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualAdminCopy.csv");

                boolean actualAdminFileCreated = actualAdminFileCopy.createNewFile();
                if(!actualAdminFileCreated){
                    throw new CustomException("ACTUAL ADMIN COPY NOT CREATED **");
                }

                fileReader = new FileReader(actualAdminFile);
                fileWriter = new FileWriter(actualAdminFileCopy);

                bufferedReader = new BufferedReader(fileReader);
                bufferedWriter = new BufferedWriter(fileWriter);

                String rowData;
                while ((rowData = bufferedReader.readLine()) != null) {                                                 // bufferReader.readLine() returns null if EOF.
                    String[] rowWords = rowData.split(",");

                    StringBuilder rowDataCopy = new StringBuilder();
                    if (rowWords[1].equals(empId) && rowWords[3].equals(currentPassword)) {
                        for (int rowWordsIndex = 0; rowWordsIndex < rowWords.length; rowWordsIndex++) {
                            if (rowWordsIndex == 3) {
                                rowDataCopy.append(newPassword).append("\n");
                            } else {
                                rowDataCopy.append(rowWords[rowWordsIndex]).append(",");
                            }
                        }
                    } else {
                        for (int rowWordsIndex = 0; rowWordsIndex < rowWords.length; rowWordsIndex++) {
                            if (rowWordsIndex != rowWords.length - 1) {
                                rowDataCopy.append(rowWords[rowWordsIndex]).append(",");
                            } else {
                                rowDataCopy.append(rowWords[rowWordsIndex]).append("\n");
                            }
                        }
                    }
                    bufferedWriter.write(rowDataCopy.toString());
                }

                continueLooping = false;
                return true;
            }
            catch (IOException ioException){
                System.out.println("IOEXCEPTION CAUGHT WHILE USING ACTUAL ADMIN FILE **");
            }
            catch (CustomException customException){
                System.out.println(customException.getMessage());
            }finally {
                try{
                    if(bufferedReader != null){
                        bufferedReader.close();
                    }
                    if(bufferedWriter != null){
                        bufferedWriter.close();
                    }
                    if(fileReader != null){
                        fileReader.close();
                    }
                    if(fileWriter != null){
                        fileWriter.close();
                    }
                    if(actualAdminFile != null && actualAdminFileCopy != null){
                        boolean actualAdminFileDeleted = actualAdminFile.delete();
                        boolean actualAdminFileCopyRenamed = actualAdminFileCopy.renameTo(new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualAdmin.csv"));
                        if(!actualAdminFileDeleted || !actualAdminFileCopyRenamed){
                            throw new CustomException("FILE NAME NOT DELETED AND RENAMED **");
                        }
                    }
                }catch (IOException ioException){
                    System.out.println("IOEXCEPTION CAUGHT WHILE CLOSING ACTUAL ADMIN FILE **");
                }catch (CustomException customException){
                    System.out.println(customException.getMessage());
                }

                if(maxWrongTries-- == 0){
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }
        return false;
    }
}