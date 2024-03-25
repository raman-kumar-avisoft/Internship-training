package task.management.system;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class User {
    User(){
        userMainMenu();
    }
    void userMainMenu(){
        Scanner scannerInteger = new Scanner(System.in);

        boolean continueLooping = true;
        int maxWrongTries = 2;

        while (continueLooping) {
            try {
                displayUserMainMenu();
                System.out.print("Select the choice out of following: ");
                int choice = scannerInteger.nextInt();

                switch (choice) {
                    case 1:
                        String userLoggedInId = userLogIn();
                        if (!userLoggedInId.equals("")) {
                            System.out.println("User LogIn Successful");
                            int maxWrongTries2 = 3;
                            while(--maxWrongTries2 >= 0){
                                try{
                                    displayLogInIdMenu();
                                    System.out.print("Select the choice out of following: ");
                                    int choice2 = scannerInteger.nextInt();
                                    switch (choice2){
                                        case 1:
                                            boolean taskViewed = viewTasks(userLoggedInId);
                                            if(taskViewed){
                                                System.out.println("Task Viewed Successfully");
                                            }
                                            maxWrongTries2 = 3;
                                            break;
                                        case 2:
                                            boolean taskSubmitted = submitTask(userLoggedInId);
                                            if(taskSubmitted){
                                                System.out.println("Task Submitted Successfully");
                                            }
                                            maxWrongTries2 = 3;
                                            break;
                                        case 3:
                                            maxWrongTries2 = 0;
                                            break;
                                        default:
                                            System.out.println("NOT A VALID OPTION **");
                                            break;
                                    }
                                }catch(InputMismatchException inputMismatchException){
                                    System.out.println("NOT A VALID NUMBER **");
                                }
                            }
                            if(maxWrongTries2 < 0){
                                System.out.println("TOO MANY WRONG TRIES **");
                            }
                        }
                        maxWrongTries = 2;
                        break;
                    case 2:
                        boolean resetPassword = updatePassword();
                        if (resetPassword) {
                            System.out.println("Password Updated Successfully");
                        }
                        maxWrongTries = 2;
                        break;
                    case 3:
                        continueLooping = false;
                        break;
                    default:
                        throw new CustomException("NOT A VALID OPTION **");
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("NOT A VALID NUMBER **");
            } catch (CustomException customException) {
                System.out.println(customException.getMessage());
            }
            if (maxWrongTries-- == 0) {
                System.out.println("TOO MANY WRONG TRIES **");
                continueLooping = false;
            }
        }
    }
    String userLogIn() {
        Scanner scannerString = new Scanner(System.in);

        File actualUserFile = null;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        String logInId = null, logInPassword;
        int maxWrongTries = 3;

        while (--maxWrongTries >= 0) {
            try {
                System.out.print("Enter the LogIn Id: ");
                logInId = scannerString.next();

                System.out.print("Enter the LogIn Password: ");
                logInPassword = scannerString.next();

                actualUserFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUser.csv");
                fileReader = new FileReader(actualUserFile);
                bufferedReader = new BufferedReader(fileReader);
                String row;
                while((row = bufferedReader.readLine()) != null){
                    String[] rowData = row.split(",");
                    if(rowData.length == 4){
                        if(logInId.equals(rowData[0]) && logInPassword.equals(rowData[3])){
                            break;
                        }else if(logInId.equals(rowData[0])){
                            throw new CustomException("WRONG PASSWORD **");
                        }
                    }
                }
                break;
            }catch(IOException ioException){
                System.out.println("IO EXCEPTION CAUGHT WHILE USING ACTUAL USER FILE **");
            }catch (CustomException customException) {
                System.out.println(customException.getMessage());
            } finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (fileReader != null) {
                        fileReader.close();
                    }
                } catch (IOException ioException) {
                    System.out.println("IOEXCEPTION WHILE USING ACTUAL USER FILE **");
                }
            }
        }
        if (maxWrongTries < 0) {
            System.out.println("TOO MANY WRONG TRIES **");
            return "";
        }
        return logInId;
    }
    boolean updatePassword(){
        Scanner scannerString = new Scanner(System.in);

        File actualUserFile = null;
        File actualUserFileCopy = null;
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        int maxWrongTries = 3;

        while (--maxWrongTries >= 0) {
            try {
                System.out.print("Enter the LogIn Id: ");
                String logInId = scannerString.next();

                System.out.print("Enter the LogIn Password: ");
                String logInPassword = scannerString.next();

                System.out.print("Enter the new LogIn Password: ");
                String newLogInPassword = scannerString.next();

                if (userRegistered(logInId)) {
                    throw new online.voting.system.CustomException("EMPLOYEE WITH THIS USER ID IS NOT REGISTERED **");
                }

                System.out.println("Enter the Course Name(JAVA, ML, MERN): ");
                String course = scannerString.next();

                if (!course.equals("JAVA") && !course.equals("ML") && !course.equals("MERN")) {
                    throw new online.voting.system.CustomException("NOT A VALID COURSE NAME **");
                }

                actualUserFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUser.csv");
                actualUserFileCopy = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUserCopy.csv");

                boolean copyFileCreated = actualUserFileCopy.createNewFile();
                if (!copyFileCreated) {
                    throw new online.voting.system.CustomException("NOT ABLE TO CREATE ACTUAL USER COPY FILE **");
                }

                fileReader = new FileReader(actualUserFile);
                fileWriter = new FileWriter(actualUserFileCopy);
                bufferedReader = new BufferedReader(fileReader);
                bufferedWriter = new BufferedWriter(fileWriter);

                String row;
                bufferedWriter.write(bufferedReader.readLine());
                bufferedWriter.write("\n");
                while ((row = bufferedReader.readLine()) != null) {
                    String[] rowData = row.split(",");
                    if (rowData[0].equals(logInId) && rowData[3].equals(logInPassword)) {
                        String newRow = "";
                        for(int rowDataIndex = 0; rowDataIndex < rowData.length; rowDataIndex++){
                            if(rowDataIndex == 3){
                                newRow += newLogInPassword + ",";
                            }
                            else if(rowDataIndex != rowData.length - 1){
                                newRow += rowData[rowDataIndex] + ",";
                            }else{
                                newRow += rowData[rowDataIndex];
                            }
                        }
                        bufferedWriter.write(rowData[0] + "," + rowData[1] + "," + rowData[2] + newLogInPassword + "," + course);
                    } else {
                        bufferedWriter.write(row);
                    }
                    bufferedWriter.write("\n");
                }

                break;
            } catch (IOException ioException) {
                System.out.println("IOEXCEPTION WHILE READING ACTUAL USER FILE **");
            } catch (online.voting.system.CustomException customException) {
                System.out.println(customException.getMessage());
            } finally {
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                    if (fileReader != null) {
                        fileReader.close();
                    }
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                    if (actualUserFile != null && actualUserFileCopy != null) {
                        boolean fileDeleted = actualUserFile.delete();
                        boolean fileRenamed = actualUserFileCopy.renameTo(new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUser.csv"));
                        if (!fileDeleted || !fileRenamed) {
                            throw new online.voting.system.CustomException("FILE NOT DELETED AND RENAMED **");
                        }
                    }
                } catch (IOException ioException) {
                    System.out.println("IOEXCEPTION WHILE CLOSING ACTUAL USER FILE **");
                } catch (online.voting.system.CustomException customException) {
                    System.out.println(customException.getMessage());
                }
            }
        }
        if (maxWrongTries < 0) {
            System.out.println("TOO MANY WRONG TRIES **");
            return false;
        }
        return true;
    }
    boolean userRegistered(String logInId) {
        File actualUser;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            actualUser = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUser.csv");
            fileReader = new FileReader(actualUser);
            bufferedReader = new BufferedReader(fileReader);

            String row;
            bufferedReader.readLine();
            while ((row = bufferedReader.readLine()) != null) {
                String[] rowData = row.split(",");
                if (logInId.equals(rowData[0]) && rowData.length > 3) {
                    return true;
                }
            }
        } catch (IOException ioException) {
            System.out.println("IOEXCEPTION WHILE USING ACTUAL USER FILE **");
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException ioException) {
                System.out.println("IOEXCEPTION WHILE CLOSING ACTUAL USER FILE **");
            }
        }
        return false;
    }
    boolean viewTasks(String logInId){
        File actualUserFile;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        boolean taskViewed = false;

        try{
            actualUserFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUser.csv");
            fileReader = new FileReader(actualUserFile);
            bufferedReader = new BufferedReader(fileReader);
            String row;
            while((row = bufferedReader.readLine()) != null){
                String[] rowData = row.split(",");
                if(logInId.equals(rowData[0])){
                    if(rowData.length >= 6){
                        System.out.println("TASKS DETAILS --");
                        String[] taskAssigned = rowData[5].split(":");
                        String[] taskStatus = rowData[6].split(":");
                        for(int taskAssignedIndex = 0; taskAssignedIndex < taskAssigned.length; taskAssignedIndex++){
                                                                                                                        // GET PRIORITY FROM THE ACTUAL TASK FILE **
                            File actualTaskFile;
                            FileReader fileReader1 = null;
                            BufferedReader bufferedReader1 = null;

                            try{
                                actualTaskFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualTask.csv");
                                fileReader1 = new FileReader(actualTaskFile);
                                bufferedReader1 = new BufferedReader(fileReader1);

                                String row2;
                                while((row2 = bufferedReader1.readLine()) != null){
                                    String[] row2Data = row2.split(",");
                                    if(taskAssigned[taskAssignedIndex].equals(row2Data[0])){
                                        taskViewed = true;
                                        System.out.println("TASK TITLE: " + taskAssigned[taskAssignedIndex] + ", TASK STATUS: " + taskStatus[taskAssignedIndex] + ", TASK PRIORITY: " + row2Data[3]);
                                        break;
                                    }
                                }
                            }catch (IOException ioException){
                                System.out.println("IO EXCEPTION WHILE USING TASK FILE **");
                            }finally {
                                try{
                                    if(bufferedReader1 != null){
                                        bufferedReader1.close();
                                    }
                                    if(fileReader1 != null){
                                        fileReader1.close();
                                    }
                                }catch (IOException ioException){
                                    System.out.println("IO EXCEPTION CAUGHT WHILE CLOSING FILE RESOURCES **");
                                }
                            }
                        }
                    }else{
                        throw new CustomException("USER WITH THIS ID DOES NOT ASSIGNED ANY TASKS");
                    }
                }
            }
        }catch(IOException ioException){
            System.out.println("IO EXCEPTION WHILE USING ACTUAL USER FILE **");
        }catch(CustomException customException){
            System.out.println(customException.getMessage());
        }finally {
            try{
                if(bufferedReader != null){
                    bufferedReader.close();
                }
                if(fileReader != null){
                    fileReader.close();
                }
            }catch (IOException ioException){
                System.out.println("IOEXCEPTION CAUGHT WHILE CLOSING ACTUAL USER FILE RESOURCES **");
            }
        }
        if(taskViewed){
            return true;
        }
        return false;
    }
    boolean submitTask(String logInId){
        Scanner scannerString = new Scanner(System.in);
        File actualUserFile = null;
        File actualUserCopyFile = null;
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        boolean taskFound = false;

        try{
            System.out.print("Enter the Task Title: ");
            String taskTitle = scannerString.next();

            actualUserFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUser.csv");
            actualUserCopyFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUserCopy.csv");

            boolean copyFileCreated = actualUserCopyFile.createNewFile();
            if (!copyFileCreated){
                throw new CustomException("ACTUAL USER COPY FILE NOT CREATED **");
            }
            fileWriter = new FileWriter(actualUserCopyFile);
            fileReader = new FileReader(actualUserFile);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedReader = new BufferedReader(fileReader);

            String row = bufferedReader.readLine();
            bufferedWriter.write(row);
            bufferedWriter.write("\n");
            while((row = bufferedReader.readLine()) != null){
                String[] rowData = row.split(",");
                if(logInId.equals(rowData[0])) {
                    if (rowData.length >= 6) {
                        String[] taskAssigned = rowData[5].split(":");
                        String[] taskStatus = rowData[6].split(":");
                        row = "";
                        row += rowData[0] + "," + rowData[1] + "," + rowData[2] + "," + rowData[3] + "," + rowData[4] + "," + rowData[5] + ",";
                        String taskStatusStr = "";
                        for (int taskAssignedIndex = 0; taskAssignedIndex < taskAssigned.length; taskAssignedIndex++) {
                            if (taskTitle.equals(taskAssigned[taskAssignedIndex])) {
                                taskFound = true;
                                taskStatusStr += "Submitted:";
                            }else{
                                taskStatusStr += taskStatus[taskAssignedIndex] + ":";
                            }
                        }
                        row += taskStatusStr;
                    }
                }
                bufferedWriter.write(row);
                bufferedWriter.write("\n");
            }
            if(!taskFound){
                throw new CustomException("NO TASKS ARE THERE FOR THIS USER TILL NOW **");
            }
            return true;
        }catch(IOException ioException){
            System.out.println("IO EXCEPTION WHILE USING ACTUAL USER FILE **");
        }catch(CustomException customException){
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

                }
                if (actualUserFile != null && actualUserCopyFile != null) {
                    boolean fileDeleted = actualUserFile.delete();
                    boolean fileRenamed = actualUserCopyFile.renameTo(new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUser.csv"));
                    if (!fileDeleted || !fileRenamed) {
                        throw new CustomException("FILE NOT DELETED AND RENAMED **");
                    }
                }
            }catch (IOException ioException){
                System.out.println("IOEXCEPTION CAUGHT WHILE CLOSING ACTUAL USER FILE RESOURCES **");
            }catch (CustomException customException) {
                System.out.println(customException.getMessage());
            }
        }
        return false;
    }
    void displayLogInIdMenu(){
        System.out.println("1. View Tasks");
        System.out.println("2. Submit Tasks");
        System.out.println("3. Go Back to User Main Menu");
    }
    void displayUserMainMenu(){
        System.out.println("1. LogIn");
        System.out.println("2. Reset Password");
        System.out.println("3. Exit To TaskManagement Class");
    }
}
