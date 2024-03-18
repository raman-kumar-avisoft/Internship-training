package task.management.system;

import online.voting.system.CustomException;

import java.io.*;
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
    }
    boolean adminLogIn() {
        Scanner scannerString = new Scanner(System.in);
        int maxWrongTries = 2;
        boolean continueLooping = true;
        while (continueLooping) {
            try {
                System.out.print("Enter the Admin LogIn Id: ");
                String adminLogInId = scannerString.next();

                System.out.print("Enter the Admin LogIn Password: ");
                String adminLogInPassword = scannerString.next();

                if (adminLogInId.equals(this.adminLogInId) && adminLogInPassword.equals(this.adminPassword)) {
                    return true;
                } else {
                    throw new CustomException("WRONG LOGIN ID AND PASSWORD **");
                }
            } catch (CustomException customException) {
                System.out.println(customException.getMessage());
            } finally {
                if (maxWrongTries-- == 0) {
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }
        return false;
    }
    void adminMainMenu() {
        Scanner scannerInteger = new Scanner(System.in);

        boolean continueLooping = true;
        int maxWrongTries = 2;

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
                        continueLooping = false;
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
    boolean updatePassword(){
        Scanner scannerString = new Scanner(System.in);

        File actualUserFile = null;
        File actualUserFileCopy = null;
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        int maxWrongTries = 2;
        boolean continueLooping = true;

        while (continueLooping) {
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
                return true;
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
                if (maxWrongTries-- == 0) {
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }
        return false;
    }
    boolean updateCourse(){
        Scanner scannerString = new Scanner(System.in);

        File actualUserFile = null;
        File actualUserFileCopy = null;
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        int maxWrongTries = 2;
        boolean continueLooping = true;

        while (continueLooping) {
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
                return true;
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
                if (maxWrongTries-- == 0) {
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }
        return false;
    }
    boolean removeUser() {
        Scanner scannerString = new Scanner(System.in);

        File actualUserFile = null;
        File actualUserFileCopy = null;
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        int maxWrongTries = 2;
        boolean continueLooping = true;

        while (continueLooping) {
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
                return true;
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
                if (maxWrongTries-- == 0) {
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }
        return false;
    }
    boolean addUser() {
        Scanner scannerString = new Scanner(System.in);

        File actualUserFile = null;
        File actualUserFileCopy = null;
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        int maxWrongTries = 2;
        boolean continueLooping = true;

        while (continueLooping) {
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
                    if (rowData[0].equals(logInId)) {
                        bufferedWriter.write(row + "," + logInPassword + "," + course);
                    } else {
                        bufferedWriter.write(row);
                    }
                    bufferedWriter.write("\n");
                }
                return true;
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
                if (maxWrongTries-- == 0) {
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }
        return false;
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
    void displayAdminMainMenu() {
        System.out.println("1. Add User");
        System.out.println("2. Remove User");
        System.out.println("3. Update User");
        System.out.println("4. Change User Class");
        System.out.println("5. Add Task");
        System.out.println("6. Remove Task");
        System.out.println("5. Task Status");
        System.out.println("6. Exit to TaskManagement Class");
    }

    public static void main(String[] args) {
        Admin admin = new Admin();
    }
}