package task.management.system;

import online.voting.system.CustomException;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin {
    private final String adminLogInId = "AS01";
    private final String adminPassword = "aviSoft-123";

    Admin() {
        boolean adminLoggedIn = adminLogIn();
        if (adminLoggedIn) {
            System.out.println("LogIn Successful");
            adminMainMenu();
        }

//        adminMainMenu();
    }
    boolean adminLogIn() {
        Scanner scannerString = new Scanner(System.in);
        int maxWrongTries = 3;
        while (--maxWrongTries >= 0) {
            try {
                System.out.print("Enter the Admin LogIn Id: ");
                String adminLogInId = scannerString.next();

                System.out.print("Enter the Admin LogIn Password: ");
                String adminLogInPassword = scannerString.next();

                if (adminLogInId.equals(this.adminLogInId) && adminLogInPassword.equals(this.adminPassword)) {
                    break;
                } else {
                    throw new CustomException("WRONG LOGIN ID AND PASSWORD **");
                }
            } catch (CustomException customException) {
                System.out.println(customException.getMessage());
            }
        }
        if (maxWrongTries < 0) {
            System.out.println("TOO MANY WRONG TRIES **");
            return false;
        }
        return true;
    }
    void adminMainMenu() {
        Scanner scannerInteger = new Scanner(System.in);

        boolean continueLooping = true;
        int maxWrongTries = 3;

        while (continueLooping) {
            try {
                displayAdminMainMenu();
                System.out.print("Select the choice out of following: ");
                int choice = scannerInteger.nextInt();

                switch (choice) {
                    case 1:
                        boolean userAdded = addUser();
                        if (userAdded) {
                            System.out.println("User Added Successfully");
                        }
                        maxWrongTries = 2;
                        break;
                    case 2:
                        boolean userRemoved = removeUser();
                        if (userRemoved) {
                            System.out.println("User Removed Successfully");
                        }
                        maxWrongTries = 2;
                        break;
                    case 3:
                        boolean courseUpdated = updateCourse();
                        if (courseUpdated) {
                            System.out.println("User Course Updated Successfully");
                        }
                        maxWrongTries = 2;
                        break;
                    case 4:
                        boolean resetPassword = updatePassword();
                        if (resetPassword) {
                            System.out.println("Password Updated Successfully");
                        }
                        maxWrongTries = 2;
                        break;
                    case 5:
                        boolean taskAdded = addTask();
                        if (taskAdded) {
                            System.out.println("Task Added Successfully");
                        }
                        maxWrongTries = 2;
                        break;
                    case 6:
                        boolean taskRemoved = removeTask();
                        if (taskRemoved) {
                            System.out.println("Task Removed Successfully");
                        }
                        maxWrongTries = 2;
                        break;
                    case 7:
                        boolean taskUpdated = updateTaskDueDate();
                        if (taskUpdated) {
                            System.out.println("Task Updated Successfully");
                        }
                        maxWrongTries = 2;
                        break;
                    case 8:
                        continueLooping = false;
                        break;
                    default:
                        throw new online.voting.system.CustomException("NOT A VALID OPTION **");
                }
            } catch (InputMismatchException inputMismatchException) {
                System.out.println("NOT A VALID NUMBER **");
            } catch (online.voting.system.CustomException customException) {
                System.out.println(customException.getMessage());
            } finally {
                if (maxWrongTries-- == 0) {
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }
    }
    boolean addUser() {
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

                if (logInPassword.length() <= 6) {
                    throw new CustomException("PASSWORD LENGTH MUST BE GREATER THAN 6 CHARACTERS **");
                }

                if (userRegistered(logInId)) {
                    throw new CustomException("EMPLOYEE WITH THIS USER ID IS ALREADY REGISTERED **");
                }

                System.out.print("Enter the Course Name(JAVA, ML, MERN): ");
                String course = scannerString.next();

                if (!course.equals("JAVA") && !course.equals("ML") && !course.equals("MERN")) {
                    throw new CustomException("NOT A VALID COURSE NAME **");
                }

                actualUserFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUser.csv");
                actualUserFileCopy = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUserCopy.csv");

                boolean copyFileCreated = actualUserFileCopy.createNewFile();
                if (!copyFileCreated) {
                    throw new CustomException("NOT ABLE TO CREATE ACTUAL USER COPY FILE **");
                }

                fileReader = new FileReader(actualUserFile);
                fileWriter = new FileWriter(actualUserFileCopy);
                bufferedReader = new BufferedReader(fileReader);
                bufferedWriter = new BufferedWriter(fileWriter);

                boolean userFound = false;
                String row;
                bufferedWriter.write(bufferedReader.readLine());
                bufferedWriter.write("\n");
                while ((row = bufferedReader.readLine()) != null) {
                    String[] rowData = row.split(",");
                    if (rowData[0].equals(logInId)) {
                        userFound = true;
                        bufferedWriter.write(row + "," + logInPassword + "," + course);
                    } else {
                        bufferedWriter.write(row);
                    }
                    bufferedWriter.write("\n");
                }
                if(!userFound){
                    throw new CustomException("USER ID DOES NOT EXISTS IN THE ACTUAL USER FILE **");
                }

                break;
            } catch (IOException ioException) {
                System.out.println("IOEXCEPTION WHILE READING ACTUAL USER FILE **");
            } catch (CustomException customException) {
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
                            throw new CustomException("FILE NOT DELETED AND RENAMED **");
                        }
                    }
                } catch (IOException ioException) {
                    System.out.println("IOEXCEPTION WHILE CLOSING ACTUAL USER FILE **");
                } catch (CustomException customException) {
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
    boolean removeUser() {
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

                if (!userRegistered(logInId)) {
                    throw new CustomException("EMPLOYEE WITH THIS USER ID IS NOT REGISTERED TO BE REMOVED**");
                }

                actualUserFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUser.csv");
                actualUserFileCopy = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUserCopy.csv");

                boolean copyFileCreated = actualUserFileCopy.createNewFile();
                if (!copyFileCreated) {
                    throw new CustomException("NOT ABLE TO CREATE ACTUAL USER COPY FILE **");
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
                    if (rowData[0].equals(logInId)) {
                        String newLine = rowData[0] +","+rowData[1]+","+rowData[2];
                        bufferedWriter.write(newLine);
                    }else{
                        bufferedWriter.write(row);
                    }
                    bufferedWriter.write("\n");
                }
                break;
            } catch (IOException ioException) {
                System.out.println("IOEXCEPTION WHILE READING ACTUAL USER FILE **");
            } catch (CustomException customException) {
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
                            throw new CustomException("FILE NOT DELETED AND RENAMED **");
                        }
                    }
                } catch (IOException ioException) {
                    System.out.println("IOEXCEPTION WHILE CLOSING ACTUAL USER FILE **");
                } catch (CustomException customException) {
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
    boolean updateCourse(){
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

                if (logInPassword.length() <= 6) {
                    throw new CustomException("PASSWORD LENGTH MUST BE GREATER THAN 6 CHARACTERS **");
                }

                if (userRegistered(logInId)) {
                    throw new CustomException("EMPLOYEE WITH THIS USER ID IS NOT REGISTERED **");
                }

                System.out.println("Enter the New Course Name(JAVA, ML, MERN): ");
                String course = scannerString.next();

                if (!course.equals("JAVA") && !course.equals("ML") && !course.equals("MERN")) {
                    throw new CustomException("NOT A VALID COURSE NAME **");
                }

                actualUserFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUser.csv");
                actualUserFileCopy = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUserCopy.csv");

                boolean copyFileCreated = actualUserFileCopy.createNewFile();
                if (!copyFileCreated) {
                    throw new CustomException("NOT ABLE TO CREATE ACTUAL USER COPY FILE **");
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
                        if(rowData[4].equals(course)){
                            bufferedWriter.write(row);
                        }else{
                            bufferedWriter.write(row + "," + logInPassword + "," + course);
                        }
                    } else {
                        bufferedWriter.write(row);
                    }
                    bufferedWriter.write("\n");
                }
                break;
            } catch (IOException ioException) {
                System.out.println("IOEXCEPTION WHILE READING ACTUAL USER FILE **");
            } catch (CustomException customException) {
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
                            throw new CustomException("FILE NOT DELETED AND RENAMED **");
                        }
                    }
                } catch (IOException ioException) {
                    System.out.println("IOEXCEPTION WHILE CLOSING ACTUAL USER FILE **");
                } catch (CustomException customException) {
                    System.out.println(customException.getMessage());
                }
            }
        }
        if (maxWrongTries-- == 0) {
            System.out.println("TOO MANY WRONG TRIES **");
            return false;
        }
        return true;
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
                    throw new CustomException("EMPLOYEE WITH THIS USER ID IS NOT REGISTERED **");
                }

                System.out.println("Enter the Course Name(JAVA, ML, MERN): ");
                String course = scannerString.next();

                if (!course.equals("JAVA") && !course.equals("ML") && !course.equals("MERN")) {
                    throw new CustomException("NOT A VALID COURSE NAME **");
                }

                actualUserFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUser.csv");
                actualUserFileCopy = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUserCopy.csv");

                boolean copyFileCreated = actualUserFileCopy.createNewFile();
                if (!copyFileCreated) {
                    throw new CustomException("NOT ABLE TO CREATE ACTUAL USER COPY FILE **");
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
            } catch (CustomException customException) {
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
                            throw new CustomException("FILE NOT DELETED AND RENAMED **");
                        }
                    }
                } catch (IOException ioException) {
                    System.out.println("IOEXCEPTION WHILE CLOSING ACTUAL USER FILE **");
                } catch (CustomException customException) {
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
    boolean addTask(){
        Scanner scannerString = new Scanner(System.in);
        File actualTaskFile = null, actualTaskFileCopy = null;
        FileReader taskFileReader = null;
        FileWriter taskFileWriter = null;
        BufferedReader taskBufferedReader = null;
        BufferedWriter taskBufferedWriter = null;

        File actualUserFile = null, actualUserFileCopy = null;
        FileReader userFileReader = null;
        FileWriter userFileWriter = null;
        BufferedReader userBufferedReader = null;
        BufferedWriter userBufferedWriter = null;

        int maxWrongTries = 3;

        while (--maxWrongTries >= 0) {
            try {
                Task task = new Task();
                if(!task.getTaskCreated()){
                    throw new CustomException("NOT ABLE TO CREATE A TASK **");
                }

                boolean taskAlreadyExists = checkTaskInFile(task.getTaskTitle());                                       // IF TASK ALREADY EXISTS OR NOT**
                if(taskAlreadyExists){
                    throw new CustomException("TASK WITH THIS TITLE ALREADY EXISTS **");
                }

                System.out.print("The Task is created for which course (JAVA, ML, MERN): ");
                String course = scannerString.next();
                if(!course.equals("JAVA") && !course.equals("ML") && !course.equals("MERN")){
                    throw new CustomException("NOT A VALID COURSE **");
                }

                actualUserFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUser.csv");
                actualUserFileCopy = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUserCopy.csv");

                boolean copyFileCreated = actualUserFileCopy.createNewFile();
                if (!copyFileCreated) {
                    throw new CustomException("NOT ABLE TO CREATE ACTUAL USER COPY FILE **");
                }

                userFileReader = new FileReader(actualUserFile);
                userFileWriter = new FileWriter(actualUserFileCopy);
                userBufferedReader = new BufferedReader(userFileReader);
                userBufferedWriter = new BufferedWriter(userFileWriter);

                boolean taskAdded = false;
                userBufferedWriter.write(userBufferedReader.readLine());
                userBufferedWriter.write("\n");
                String row;
                while ((row = userBufferedReader.readLine()) != null) {
                    String[] rowData = row.split(",");
                    if(rowData.length == 5 && rowData[4].equals(course)){                                               // NO TASK IS ASSIGNED TO THIS USER YET **
                        row += "," + task.getTaskTitle() + ":" + "," + task.getStatus() + ":";
                        taskAdded = true;
                    }else if(rowData.length == 7 && rowData[4].equals(course)){                                         // ADDED TASK ASSIGNMENT AND TASK STATUS **
                        taskAdded = true;
                        row = "";
                        boolean first = true;
                        for(int rowDataIndex=0; rowDataIndex < rowData.length; rowDataIndex++){
                            if(rowDataIndex == 5){
                                row += "," + rowData[rowDataIndex] + task.getTaskTitle() + ":";
                            }else if(rowDataIndex == 6){
                                row += "," + rowData[rowDataIndex] + task.getStatus() + ":";
                            }else{
                                if(first){
                                    row += rowData[rowDataIndex];
                                    first = false;
                                }else{
                                    row += "," + rowData[rowDataIndex];
                                }
                            }
                        }
                    }
                    userBufferedWriter.write(row);
                    userBufferedWriter.write("\n");
                }
                if(!taskAdded){
                    throw new CustomException("NO USER WITH THIS COURSE IS REGISTERED YET **");
                }

                actualTaskFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualTask.csv");
                actualTaskFileCopy = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualTaskCopy.csv");

                copyFileCreated = actualTaskFileCopy.createNewFile();
                if (!copyFileCreated) {
                    throw new CustomException("NOT ABLE TO CREATE ACTUAL TASK COPY FILE **");
                }

                taskFileReader = new FileReader(actualTaskFile);
                taskFileWriter = new FileWriter(actualTaskFileCopy);
                taskBufferedReader = new BufferedReader(taskFileReader);
                taskBufferedWriter = new BufferedWriter(taskFileWriter);

                taskBufferedWriter.write(taskBufferedReader.readLine());
                taskBufferedWriter.write("\n");
                while ((row = taskBufferedReader.readLine()) != null) {
                    taskBufferedWriter.write(row);
                    taskBufferedWriter.write("\n");
                }
                row = task.getTaskTitle()+","+task.getTaskDescription()+","+task.getDueDate()+","+task.getPriorityLevel();
                taskBufferedWriter.write(row);
                taskBufferedWriter.write("\n");

                break;
            } catch (IOException ioException) {
                System.out.println("IOEXCEPTION WHILE READING ACTUAL USER FILE **");
            } catch (CustomException customException) {
                System.out.println(customException.getMessage());
            } finally {
                try {
                    if (userBufferedReader != null) {
                        userBufferedReader.close();
                    }
                    if (userBufferedWriter != null) {
                        userBufferedWriter.close();
                    }
                    if (userFileWriter != null) {
                        userFileWriter.close();
                    }
                    if (userFileReader != null) {
                        userFileReader.close();
                    }
                    if (actualUserFile != null && actualUserFileCopy != null) {
                        boolean fileDeleted = actualUserFile.delete();
                        boolean fileRenamed = actualUserFileCopy.renameTo(new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUser.csv"));
                        if (!fileDeleted || !fileRenamed) {
                            throw new CustomException("TASK FILE NOT DELETED AND RENAMED **");
                        }
                    }
                    if (taskBufferedReader != null) {
                        taskBufferedReader.close();
                    }
                    if (taskBufferedWriter != null) {
                        taskBufferedWriter.close();
                    }
                    if (taskFileReader != null) {
                        taskFileReader.close();
                    }
                    if (taskFileWriter != null) {
                        taskFileWriter.close();
                    }
                    if (actualTaskFile != null && actualTaskFileCopy != null) {
                        boolean fileDeleted = actualTaskFile.delete();
                        boolean fileRenamed = actualTaskFileCopy.renameTo(new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualTask.csv"));
                        if (!fileDeleted || !fileRenamed) {
                            throw new CustomException("USER FILE NOT DELETED AND RENAMED **");
                        }
                    }
                } catch (IOException ioException) {
                    System.out.println("IOEXCEPTION WHILE CLOSING THE FILE **");
                } catch (CustomException customException) {
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
    boolean removeTask(){
        Scanner scannerString = new Scanner(System.in);
        File actualTaskFile = null, actualTaskFileCopy = null;
        FileReader taskFileReader = null;
        FileWriter taskFileWriter = null;
        BufferedReader taskBufferedReader = null;
        BufferedWriter taskBufferedWriter = null;

        File actualUserFile = null, actualUserFileCopy = null;
        FileReader userFileReader = null;
        FileWriter userFileWriter = null;
        BufferedReader userBufferedReader = null;
        BufferedWriter userBufferedWriter = null;

        int maxWrongTries = 3;

        while (--maxWrongTries >= 0) {
            try {
                System.out.print("Enter the Task Title: ");
                String taskTitle = scannerString.next();

                boolean taskAlreadyExists = checkTaskInFile(taskTitle);                                                 // IF TASK ALREADY EXISTS OR NOT**
                if(!taskAlreadyExists){
                    throw new CustomException("TASK WITH THIS TITLE DOES NOT EXISTS **");
                }

                actualUserFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUser.csv");
                actualUserFileCopy = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUserCopy.csv");

                userFileReader = new FileReader(actualUserFile);
                userFileWriter = new FileWriter(actualUserFileCopy);
                userBufferedReader = new BufferedReader(userFileReader);
                userBufferedWriter = new BufferedWriter(userFileWriter);

                boolean taskRemoved = false;
                userBufferedWriter.write(userBufferedReader.readLine());
                userBufferedWriter.write("\n");
                String row;
                while ((row = userBufferedReader.readLine()) != null) {
                    String[] rowData = row.split(",");
                    if(rowData.length == 7){                                                                            // USER WHICH HAVE SOME TASKS
                        row = "";
                        boolean first = true;
                        for(int rowDataIndex=0; rowDataIndex < 5; rowDataIndex++){
                            if(first){
                                row += rowData[rowDataIndex];
                                first = false;
                            }else{
                                row += "," + rowData[rowDataIndex];
                            }
                        }
                        String taskAssignedStr = ",";
                        String[] taskAssigned = rowData[5].split(":");
                        int index = -1, count = 0;                                                                      // FOR TASK ASSIGNMENT
                        for(String taskTi: taskAssigned){
                            if(taskTi.equals(taskTitle)){
                                taskRemoved = true;
                               index = count;
                            }else{
                                taskAssignedStr += taskTi + ":";
                            }
                            count++;
                        }

                        if(!taskAssignedStr.equals(",")){
                            taskAssignedStr += ",";
                            count=0;
                            String[] taskStatus = rowData[6].split(":");
                            for(String taskSt: taskStatus){                                                                 // FOR REMOVING THE TASK STATUS
                                if(count != index){
                                    taskAssignedStr += taskSt + ":";
                                }
                                count++;
                            }
                            row += taskAssignedStr;
                        }
                    }
                    userBufferedWriter.write(row);
                    userBufferedWriter.write("\n");
                }
                if(!taskRemoved){
                    throw new CustomException("NO USER IS ASSIGNED THIS TASK **");
                }

                actualTaskFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualTask.csv");
                actualTaskFileCopy = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualTaskCopy.csv");

                boolean copyFileCreated = actualTaskFileCopy.createNewFile();
                if (!copyFileCreated) {
                    throw new CustomException("NOT ABLE TO CREATE ACTUAL TASK COPY FILE **");
                }

                taskFileReader = new FileReader(actualTaskFile);
                taskFileWriter = new FileWriter(actualTaskFileCopy);
                taskBufferedReader = new BufferedReader(taskFileReader);
                taskBufferedWriter = new BufferedWriter(taskFileWriter);

                taskBufferedWriter.write(taskBufferedReader.readLine());
                taskBufferedWriter.write("\n");
                while ((row = taskBufferedReader.readLine()) != null) {
                    String[] rowData = row.split(",");
                    if(rowData[0].equals(taskTitle)){
                        continue;
                    }
                    taskBufferedWriter.write(row);
                    taskBufferedWriter.write("\n");
                }

                break;
            } catch (IOException ioException) {
                System.out.println("IOEXCEPTION WHILE READING ACTUAL USER FILE **");
            } catch (CustomException customException) {
                System.out.println(customException.getMessage());
            } finally {
                try {
                    if (userBufferedReader != null) {
                        userBufferedReader.close();
                    }
                    if (userBufferedWriter != null) {
                        userBufferedWriter.close();
                    }
                    if (userFileReader != null) {
                        userFileReader.close();
                    }
                    if (userFileWriter != null) {
                        userFileWriter.close();
                    }
                    if (actualUserFile != null && actualUserFileCopy != null) {
                        boolean fileDeleted = actualUserFile.delete();
                        boolean fileRenamed = actualUserFileCopy.renameTo(new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualUser.csv"));
                        if (!fileDeleted || !fileRenamed) {
                            throw new CustomException("TASK FILE NOT DELETED AND RENAMED **");
                        }
                    }

                    if (taskBufferedReader != null) {
                        taskBufferedReader.close();
                    }
                    if (taskBufferedWriter != null) {
                        taskBufferedWriter.close();
                    }
                    if (taskFileReader != null) {
                        taskFileReader.close();
                    }
                    if (taskFileWriter != null) {
                        taskFileWriter.close();
                    }
                    if (actualTaskFile != null && actualTaskFileCopy != null) {
                        boolean fileDeleted = actualTaskFile.delete();
                        boolean fileRenamed = actualTaskFileCopy.renameTo(new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualTask.csv"));
                        if (!fileDeleted || !fileRenamed) {
                            throw new CustomException("USER FILE NOT DELETED AND RENAMED **");
                        }
                    }
                } catch (IOException ioException) {
                    System.out.println("IOEXCEPTION WHILE CLOSING THE FILE **");
                } catch (CustomException customException) {
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
    boolean updateTaskDueDate(){
        Scanner scannerString = new Scanner(System.in);

        File actualTaskFile = null;
        File actualTaskFileCopy = null;
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;

        int maxWrongTries = 3;

        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the task Title: ");
                String taskTitle = scannerString.nextLine();

                System.out.print("Enter the Updated Due Date(yyyy-MM-dd HH:mm:ss): ");
                String dueDateString = scannerString.nextLine();
                LocalDateTime currentDateTime = LocalDateTime.now();                                                    // GET CURRENT DATE AND TIME
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dueDateTime = LocalDateTime.parse(dueDateString, formatter);
                int comparison = dueDateTime.compareTo(currentDateTime);

                if (comparison < 0 || comparison == 0) {
                    throw new CustomException("DUE DATE MUST A FUTURE DATE AND TIME **");
                }

                actualTaskFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualTask.csv");
                actualTaskFileCopy = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualTaskCopy.csv");

                boolean copyFileCreated = actualTaskFileCopy.createNewFile();
                if (!copyFileCreated) {
                    throw new CustomException("NOT ABLE TO CREATE ACTUAL USER COPY FILE **");
                }

                fileReader = new FileReader(actualTaskFile);
                fileWriter = new FileWriter(actualTaskFileCopy);
                bufferedReader = new BufferedReader(fileReader);
                bufferedWriter = new BufferedWriter(fileWriter);

                boolean recordFound = false;
                String row;
                row = bufferedReader.readLine();
                bufferedWriter.write(row);
                bufferedWriter.write("\n");
                while((row = bufferedReader.readLine()) != null){
                    String[] rowData = row.split(",");
                    if(taskTitle.equals(rowData[0])){
                        recordFound = true;
                        bufferedWriter.write(rowData[0] + "," + rowData[1] + "," + dueDateString + "," +rowData[3]);
                    }else{
                        bufferedWriter.write(row);
                    }
                    bufferedWriter.write("\n");
                }
                if (!recordFound){
                    throw new CustomException("NO TASK IS FOUND WITH THIS TASK TITLE **");
                }

                break;
            }catch(IOException ioException){
                System.out.println("IO EXCEPTION WHILE READING ACTUAL TASK FILE **");
            }
            catch(DateTimeParseException dateTimeParseException){
                System.out.println("NOT A VALID DATE AND TIME **");
            }catch(CustomException customException){
                System.out.println(customException.getMessage());
            }finally {
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
                    if (actualTaskFile != null && actualTaskFileCopy != null) {
                        boolean fileDeleted = actualTaskFile.delete();
                        boolean fileRenamed = actualTaskFileCopy.renameTo(new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualTask.csv"));
                        if (!fileDeleted || !fileRenamed) {
                            throw new CustomException("USER FILE NOT DELETED AND RENAMED **");
                        }
                    }
                } catch (IOException ioException) {
                    System.out.println("IOEXCEPTION WHILE CLOSING THE FILE **");
                } catch (CustomException customException) {
                    System.out.println(customException.getMessage());
                }
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES **");
            return false;
        }
        return true;
    }
    boolean checkTaskInFile(String taskTitle){

        File actualTaskFile;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try{
            actualTaskFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\task\\management\\system\\actualTask.csv");
            fileReader = new FileReader(actualTaskFile);
            bufferedReader = new BufferedReader(fileReader);

            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] rowData = row.split(",");
                if (taskTitle.equals(rowData[0])) {
                    return true;
                }
            }
        } catch (IOException ioException) {
            System.out.println("IOEXCEPTION WHILE READING ACTUAL USER FILE **");
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
    void displayAdminMainMenu() {
        System.out.println("1. Add User");
        System.out.println("2. Remove User");
        System.out.println("3. Update User Course");
        System.out.println("4. Reset User Password");
        System.out.println("5. Add Task");
        System.out.println("6. Remove Task");
        System.out.println("7. Update Task DueDate");
        System.out.println("8. Exit to TaskManagement Class");
    }
}
