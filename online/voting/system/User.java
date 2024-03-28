package online.voting.system;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class User {
    User(boolean runConstructor){
        if(runConstructor){
            userMainMenu();
        }
    }
    void userMainMenu(){
        Scanner scannerInteger = new Scanner(System.in);
        boolean continueLooping = true;
        int maxWrongTries = 2;
        while(continueLooping){
            try{
                displayUserMainMenu();
                System.out.print("Enter the choice you want to expand: ");
                int choice = scannerInteger.nextInt();
                boolean status;
                switch (choice){
                    case 1:
                        String empId = logInUser();
                        if(empId.length() == 4){
                            System.out.println("login Successful");
                            status = userVoting(empId);
                            if(status){
                                System.out.println("vote casted successfully");
                            }
                            maxWrongTries = 2;
                        }
                        break;
                    case 2:
                        status = registerUser();
                        if(status){
                            System.out.println("User registration Successful");
                            maxWrongTries = 2;
                        }
                        break;
                    case 3:
                        status = changeUserPassword();
                        if(status){
                            System.out.println("Password changed Successful");
                            maxWrongTries = 2;
                        }
                        break;
                    case 4:
                        continueLooping = false;
                        break;
                    default:
                        System.out.println("NOT A VALID OPTION **");
                        break;
                }
            }catch (InputMismatchException inputMismatchException){
                System.out.println("Not a valid number !!");
            }finally {
                if(maxWrongTries-- == 0){
                    System.out.println("too many wrong tries !!");
                    continueLooping = false;
                }
            }
        }
    }
    void displayUserMainMenu(){
        System.out.println("1. Already a User");
        System.out.println("2. Register a User");
        System.out.println("3. Reset Password");
        System.out.println("5. Back to Main Menu");
    }
    String logInUser(){
        Scanner scannerString = new Scanner(System.in);
        boolean continueLooping = true;
        int maxWrongTries = 2;
        while(continueLooping){
            try{
                System.out.print("Enter the Login ID(Emp ID): ");
                String empId = scannerString.next();

                System.out.print("Enter the password: ");
                String password = scannerString.next();
                if(password.length() <= 6){
                    throw new CustomException("Password length should be greater than 6 character !!");
                }
                File actualUserFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualUser.csv");
                FileReader fileReader = new FileReader(actualUserFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String row;
                boolean empIdFound = false;
                while((row = bufferedReader.readLine()) != null) {                                                      // CHECKING FOR CORRESPONDING LOGIN ID AND PASSWORD.
                    String[] rowData = row.split(",");
                    for (int rowDataIndex = 0; rowDataIndex < rowData.length; rowDataIndex++) {
                        if ( rowData[0].equals(empId)) {
                            if (rowData[rowData.length - 1].equals(password)) {
                                return empId;
                            } else {
                                if (rowData.length > 3) {
                                    throw new CustomException("WRONG PASSWORD **");
                                } else {
                                    throw new CustomException("USER NOT REGISTERED **");
                                }
                            }
                        }
                    }
                }
                if(!empIdFound){
                    throw new CustomException("EMPLOYEE ID DOES NOT EXITS **");
                }
            }catch (IOException ioException){
                System.out.println("INPUT OUTPUT EXCEPTION CAUGHT WHILE FETCHING ACTUAL USER FILE DATA **");
            }catch (CustomException customException){
                System.out.println(customException.getMessage());
            }finally {
                if(maxWrongTries-- == 0){
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }
        return null;
    }
    boolean registerUser(){
        Scanner scannerString = new Scanner(System.in);

        int maxWrongTries = 2;
        boolean continueLooping = true;
        boolean adminFound = false;

        File actualAdminFile = null;
        File actualAdminFileCopy = null;
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        while (continueLooping){
            try{
                String empId, password;
                System.out.print("Enter the LogIn Id(employee Id): ");
                empId = scannerString.next();

                if(empId.length() != 4){
                    throw new CustomException("Employee Id length should be equal to 4 only !!");
                }else if(empId.charAt(0) != 'A' || empId.charAt(1) != 'S'){
                    throw new CustomException("Employee Id is not in right FORMAT (AS__) First two characters must be capital Alphabets");
                }else if(!(empId.charAt(2) >= '0' && empId.charAt(2) <= '9') || !(empId.charAt(3) >= '0' && empId.charAt(3) <= '9')){
                    throw new CustomException("Employee Id is not in right FORMAT (AS__) Last two characters must be digits");
                }

                System.out.print("Enter the password: ");
                password = scannerString.next();

                if(password.length() <= 6){
                    throw new CustomException("Password length should be greater than 6 character !!");
                }

                // Now we will read data from actualAdmin and check if admin is already registered else we will register the admin.
                actualAdminFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualUser.csv");
                actualAdminFileCopy = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualUser2.csv");
                boolean fileCreated = actualAdminFileCopy.createNewFile();
                if(!fileCreated){
                    throw new CustomException("CANNOT CREATE ACTUAL USER COPY FILE");
                }
                fileReader = new FileReader(actualAdminFile);
                fileWriter = new FileWriter(actualAdminFileCopy);
                bufferedReader = new BufferedReader(fileReader);
                bufferedWriter = new BufferedWriter(fileWriter);

                boolean alreadyRegistered = false;
                String row;
                while((row = bufferedReader.readLine()) != null){                                                       // bufferReader.readLine() returns null if EOF.
                    String[] rowWords = row.split(",");
                    if(rowWords[0].equals(empId)){
                        adminFound = true;
                        if(rowWords.length >= 4 && rowWords[rowWords.length-2].equals("true")){                         // Already Registered
                            alreadyRegistered = true;
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
                            for (String rowWord : rowWords) {
                                rowData.append(rowWord).append(",");
                            }
                            rowData.append("true,").append(password).append("\n");
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

                if(alreadyRegistered){
                    throw new CustomException("USER IS ALREADY REGISTERED **");
                }else if(!adminFound){
                    throw new CustomException("USER NOT FOUND **");
                }
                continueLooping = false;
                return true;

            }catch(CustomException customException){
                System.out.println(customException.getMessage());
            }catch (IOException ioException){
                System.out.println("IOEXCEPTION CAUGHT WHILE READING ACTUAL USER FILE **");
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
                    }if(actualAdminFile != null && actualAdminFileCopy != null){
                        boolean actualAdminDelete = actualAdminFile.delete();
                        boolean actualAdminCopyDelete = actualAdminFileCopy.renameTo(new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualUser.csv"));
                        if(!actualAdminDelete || !actualAdminCopyDelete){
                            throw new CustomException("FILE NOT CLOSED SUCCESSFULLY");
                        }
                    }
                }catch(IOException ioException){
                    System.out.println("IOEXCEPTION CAUGHT WHILE USING ACTUAL USER FILE **");
                }catch(CustomException customException){
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
    boolean changeUserPassword(){
        Scanner scannerString = new Scanner(System.in);
        boolean continueLooping = true;
        int maxWrongTries = 2;

        File actualAdminFile = null;
        File actualAdminFileCopy = null;
        FileWriter fileWriter = null;
        FileReader fileReader = null;
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;

        while(continueLooping){
            try {
                System.out.print("Enter the Login ID(Emp ID): ");
                String empId = scannerString.next();

                if(empId.length() != 4){
                    throw new CustomException("EMPLOYEE ID LENGTH MUST BE OF 4 CHARACTERS **");
                }else if(empId.charAt(0) != 'A' || empId.charAt(1) != 'S'){
                    throw new CustomException("EMPLOYEE ID IS NOT STARTING WITH THE CORRECT FORMAT (FIRST TWO LETTER MUST BE CAPITAL LETTER CHARACTERS) **");
                }else if(!(empId.charAt(2) >= '0' && empId.charAt(2) <= '9') || !(empId.charAt(3) >= '0' && empId.charAt(3) <= '9')){
                    throw new CustomException("EMPLOYEE ID IS NOT ENDING WITH THE CORRECT FORMAT (LAST TWO LETTER MUST BE DIGITS) **");
                }

                System.out.print("Enter the Current Password: ");
                String currentPassword = scannerString.next();

                System.out.print("Enter the New Password: ");
                String newPassword = scannerString.next();
                if (newPassword.length() <= 6) {
                    throw new CustomException("NEW PASSWORD LENGTH MUST BE GREATER THAN 6 CHARACTERS **");
                }

                actualAdminFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualUser.csv");
                actualAdminFileCopy = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualUserCopy.csv");
                boolean fileCreated = actualAdminFileCopy.createNewFile();
                if(!fileCreated){
                    throw new CustomException("CANNOT CREATE ACTUAL USER COPY FILE");
                }

                fileReader = new FileReader(actualAdminFile);
                fileWriter = new FileWriter(actualAdminFileCopy);

                bufferedReader = new BufferedReader(fileReader);
                bufferedWriter = new BufferedWriter(fileWriter);

                boolean recordFound = false;
                boolean wrongPassword = false;
                String rowData;
                while ((rowData = bufferedReader.readLine()) != null) {                                                 // bufferReader.readLine() returns null if EOF.
                    String[] rowWords = rowData.split(",");

                    StringBuilder rowDataCopy = new StringBuilder();
                    if (rowWords[0].equals(empId) ) {
                        if(rowWords[rowWords.length-1].equals(currentPassword)){
                            for (int rowWordsIndex = 0; rowWordsIndex < rowWords.length; rowWordsIndex++) {
                                if (rowWordsIndex == rowWords.length-1) {
                                    rowDataCopy.append(newPassword).append("\n");
                                } else {
                                    rowDataCopy.append(rowWords[rowWordsIndex]).append(",");
                                }
                            }
                            bufferedWriter.write(rowDataCopy.toString());
                            recordFound = true;
                        }else{
                            for (int rowWordsIndex = 0; rowWordsIndex < rowWords.length; rowWordsIndex++) {
                                if (rowWordsIndex == rowWords.length-1) {
                                    rowDataCopy.append(newPassword).append("\n");
                                } else {
                                    rowDataCopy.append(rowWords[rowWordsIndex]).append(",");
                                }
                            }
                            bufferedWriter.write(rowDataCopy.toString());
                            wrongPassword = true;
                        }
                    } else {
                        for (int rowWordsIndex = 0; rowWordsIndex < rowWords.length; rowWordsIndex++) {
                            if (rowWordsIndex != rowWords.length - 1) {
                                rowDataCopy.append(rowWords[rowWordsIndex]).append(",");
                            } else {
                                rowDataCopy.append(rowWords[rowWordsIndex]).append("\n");
                            }
                        }
                        bufferedWriter.write(rowDataCopy.toString());
                    }
                }

                if(recordFound){
                    continueLooping = false;
                    return true;
                }else if(wrongPassword){
                    throw new CustomException("WRONG PASSWORD **");
                }else{
                    throw new CustomException("EMPLOYEE NOT FOUND **");
                }
            }
            catch (IOException ioException){
                System.out.println("IOEXCEPTION CAUGHT WHILE USING ACTUAL USER FILE **");
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
                        boolean actualFileDeleted = actualAdminFile.delete();
                        boolean actualFileCopyDeleted = actualAdminFileCopy.renameTo(new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualUser.csv"));
                        if(!actualFileDeleted || !actualFileCopyDeleted){
                            throw new CustomException("FILE NAME NOT DELETED AND RENAMED **");
                        }
                    }
                }catch(IOException ioException){
                    System.out.println("IOEXCEPTION AUGHT WHILE WORKING WITH FILES **");
                }catch(CustomException customException){
                    System.out.println(customException.getMessage());
                }finally {
                    if(maxWrongTries-- == 0){
                        System.out.println("too many wrong tries !!");
                        continueLooping = false;
                    }
                }
            }
        }
        return false;
    }
    boolean userVoting(String employeeId){
        Scanner scannerString = new Scanner(System.in);
        int maxWrongTries = 2;
        boolean continueLooping = true;

        while(continueLooping){
            Election electionObject = new Election(false);
            try{
                String electionId = electionObject.getValidElectionId();
                if(electionId == null){
                    throw new CustomException("ELECTION ID DOES NOT EXIST **");
                }

                boolean electionCandidatesPresent = displayCandidates(electionId);
                if(!electionCandidatesPresent){
                    throw new CustomException("NO EMPLOYEE IS STANDING FOR THE ELECTION **");
                }

                boolean alreadyVoted = checkVoterInElection(electionId, employeeId);                                    // CHECK WEATHER THE EMPLOYEE ALREADY VOTED OR NOT.
                if(alreadyVoted){
                    throw new CustomException("EMPLOYEE HAS ALREADY VOTED **");
                }

                System.out.print("Enter the Election Symbol you want to vote: ");
                char votingSymbol = scannerString.next().charAt(0);

                boolean voteStatus = castVote(electionId, employeeId, votingSymbol);
                if(!voteStatus){
                    throw new CustomException("VOTING SYMBOL DOES NOT EXIST **");
                }else{
                    maxWrongTries = 0;
                }
            }catch (CustomException customException){
                System.out.println(customException.getMessage());
            }finally {
                if(maxWrongTries-- == 0){
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }
        return false;
    }
    boolean checkVoterInElection(String electionId, String employeeId){
        File actualElection;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try{
            actualElection = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualElection.csv");
            fileReader = new FileReader(actualElection);
            bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();
            String row;
            while((row = bufferedReader.readLine()) != null){
                String[] rowBreak = row.split(",");
                if(electionId.equals(rowBreak[0])){

                    if(rowBreak.length != 3){                                                                           // VOTER LIST IS EMPTY.
                        return false;
                    }
                    else{
                        String[] electionCandidatesVotes = rowBreak[2].split(":");
                        for (String electionCandidatesVote : electionCandidatesVotes) {

                            String[] candidatesWiseVotes = electionCandidatesVote.split(" ");
                            for (String candidatesWiseVote : candidatesWiseVotes) {
                                if (employeeId.equals(candidatesWiseVote)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }catch (IOException ioException){
            System.out.println("IOException caught in show ParticularElectionDetails()");
        }finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                System.out.println("Exception caught while closing the file (displayCandidate in displayCandidates)!!");
            }
        }
        return false;
    }
    boolean displayCandidates(String electionId){
        File actualElection;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        boolean candidatePresent = true;
        try{
            actualElection = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualElection.csv");
            fileReader = new FileReader(actualElection);
            bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();
            String row;
            while((row = bufferedReader.readLine()) != null){
                String[] rowBreak = row.split(",");
                if(electionId.equals(rowBreak[0])){
                    if(rowBreak.length > 1){
                        System.out.println("Details of the Election With Election Id: " + electionId);
                        String[] electionCandidates = rowBreak[1].split(":");
                        for (String electionCandidate : electionCandidates) {
                            String employeeId = electionCandidate.substring(0, 4);
                            char electionSymbol = electionCandidate.charAt(electionCandidate.length() - 1);
                            User obj = new User(false);
                            String employeeName = obj.getEmployeeName(employeeId);
                            if (employeeName == null) {
                                throw new CustomException("Employee Id is not present in actualUser file !!");
                            }
                            System.out.println("Election Candidate's Employee Id: " + employeeId + ", Election Symbol: " + electionSymbol);
                        }
                    }else{
                        candidatePresent = false;
                    }
                }
            }
        }catch (CustomException customException){
            System.out.println(customException.getMessage());
        }catch (IOException ioException){
            System.out.println("IOException caught in show ParticularElectionDetails()");
        }finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                System.out.println("Exception caught while closing the file (displayCandidate in displayCandidates)!!");
            }
        }
        return candidatePresent;
    }
    boolean castVote(String electionId, String employeeId, char votingSymbol){
        File actualElectionFile = null;
        File actualElectionFileCopy = null;
        FileWriter fileWriter = null;
        FileReader fileReader = null;
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;
        boolean absentSymbol = false;

        try {
            actualElectionFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualElection.csv");
            actualElectionFileCopy = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualElectionCopy.csv");
            boolean actualElectionFileCreated = actualElectionFileCopy.createNewFile();
            if(!actualElectionFileCreated){
                throw new CustomException("ACTUAL ELECTION FILE COPY IS NOT CREATED");
            }

            fileWriter = new FileWriter(actualElectionFileCopy);
            fileReader = new FileReader(actualElectionFile);

            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);

            String row;
            while ((row = bufferedReader.readLine()) != null) {                                                         // bufferReader.readLine() returns null if EOF.
                String[] rowWords = row.split(",");
                StringBuilder newLine = new StringBuilder();

                if (electionId.equals(rowWords[0])) {                                                                   // if election id is found
                    newLine.append(rowWords[0]).append(",");
                    String[] candidates = rowWords[1].split(":");
                    int electionCandidateIndex = -1;
                    for(int candidatesIndex=0; candidatesIndex < candidates.length; candidatesIndex++) {
                        newLine.append(candidates[candidatesIndex]).append(":");
                        if(candidates[candidatesIndex].substring(candidates[candidatesIndex].length()-1).charAt(0) == votingSymbol) {
                            electionCandidateIndex = candidatesIndex;
                        }
                    }
                    newLine.append(",");
                    if(electionCandidateIndex == -1) {
                        absentSymbol = true;
                        if(rowWords.length == 3) {
                            newLine.append(rowWords[2]);
                        }
                    }
                    else {
                        if(rowWords.length == 3) {
                            String[] candidatesVotes = rowWords[2].split(":");
                            for(int candidatesVotesIndex = 0; candidatesVotesIndex < candidates.length; candidatesVotesIndex++) {
                                if(candidatesVotesIndex == electionCandidateIndex) {
                                    if (candidatesVotes[candidatesVotesIndex].equals(" ")) {
                                        newLine.append(employeeId).append(":");
                                    } else {
                                        newLine.append(candidatesVotes[candidatesVotesIndex]).append(" ").append(employeeId).append(":");
                                    }
                                }else {
                                    newLine.append(candidatesVotes[candidatesVotesIndex]).append(":");
                                }
                            }
                        }
                        else {
                            for(int candidatesVotesIndex = 0; candidatesVotesIndex < candidates.length; candidatesVotesIndex++) {
                                if (candidatesVotesIndex == electionCandidateIndex) {
                                    newLine.append(employeeId).append(":");
                                }else{
                                    newLine.append(" " + ":");
                                }
                            }
                            newLine.append(employeeId).append(":");
                        }
                    }
                }
                else{                                                                                              // If election id is not found
                    for(int rowWordsIndex2 = 0; rowWordsIndex2 < rowWords.length; rowWordsIndex2++){
                        if(rowWordsIndex2 == rowWords.length-1){
                            newLine.append(rowWords[rowWordsIndex2]);
                        }else{
                            newLine.append(rowWords[rowWordsIndex2]).append(",");
                        }
                    }
                }
                bufferedWriter.write(newLine.toString());
                bufferedWriter.write("\n");
            }
        } catch (IOException ioException) {
            System.out.println("IOEXCEPTION CAUGHT !!");
        } catch (CustomException customException){
            System.out.println(customException.getMessage());
        } finally {
            try{
                if( bufferedReader != null){
                    bufferedReader.close();
                }
                if( bufferedWriter != null){
                    bufferedWriter.close();
                }
                if(fileReader != null){
                    fileReader.close();
                }
                if(fileWriter != null){
                    fileWriter.close();
                }
                if(actualElectionFile != null && actualElectionFileCopy != null){
                    boolean actualFileDeleted = actualElectionFile.delete();
                    boolean actualFileCopyDeleted = actualElectionFileCopy.renameTo(new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualElection.csv"));
                    if(!actualFileDeleted || !actualFileCopyDeleted){
                        throw new CustomException("FILE NAME NOT DELETED AND RENAMED **");
                    }
                }

            }catch (IOException ioException){
                System.out.println("IOEXCEPTION AUGHT WHILE WORKING WITH FILES **");
            }catch (CustomException customException){
                System.out.println(customException.getMessage());
            }
        }
        return !absentSymbol;
    }
    String getEmployeeName(String employeeId){
        File actualUser;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        String employeeName = null;

        try{
            actualUser = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualUser.csv");
            fileReader = new FileReader(actualUser);
            bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();
            String row;
            while((row = bufferedReader.readLine())!=null){
                String[] rowBreak = row.split(",");
                if(employeeId.equals(rowBreak[0])){
                    employeeName = rowBreak[1];
                }
            }
        }catch (IOException ioException){
            System.out.println("Some error in getEmployeeName in User CLass");
        }finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                // Handle exceptions during closing
                System.out.println("Exception caught while closing the file (getEmployee Name)!!");
            }
        }
        return employeeName;
    }
}
