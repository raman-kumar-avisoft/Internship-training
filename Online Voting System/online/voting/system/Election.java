package online.voting.system;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Election {
    String getValidElectionId(){
        Scanner scannerString = new Scanner(System.in);
        try{
            System.out.print("Enter the Election Id: ");
            String electionId = scannerString.next();

            if (electionId.length() != 4) {
                throw new CustomException("ELECTION ID MUST BE OF LENGTH 4 CHARACTERS **");
            } else if (electionId.charAt(0) != 'E' || electionId.charAt(1) != 'S') {
                throw new CustomException("ELECTION ID FIRST TWO CHARACTERS MUST BE OF CAPITAL ALPHABETS **");
            } else if (!(electionId.charAt(2) >= '0' && electionId.charAt(2) <= '9') || !(electionId.charAt(3) >= '0' && electionId.charAt(3) <= '9')) {
                throw new CustomException("ELECTION ID LAST TWO CHARACTERS MUST BE OF ONLY DIGITS **");
            }
            return electionId;
        }catch (CustomException customException){
            System.out.println(customException.getMessage());
            return null;
        }
    }
    String getValidEmployeeId(){
        Scanner scannerString = new Scanner(System.in);
        try{
            System.out.print("Enter the Employee Id of the Candidate: ");
            String employeeId = scannerString.next();

            if (employeeId.length() != 4) {
                throw new CustomException("EMPLOYEE ID MUST BE OF 4 CHARACTERS **");
            } else if (employeeId.charAt(0) != 'A' || employeeId.charAt(1) != 'S') {
                throw new CustomException("EMPLOYEE ID FIRST TWO CHARACTERS MUST BE OF CAPITAL ALPHABETS **");
            } else if (!(employeeId.charAt(2) >= '0' && employeeId.charAt(2) <= '9') || !(employeeId.charAt(3) >= '0' && employeeId.charAt(3) <= '9')) {
                throw new CustomException("EMPLOYEE ID LAST TWO CHARACTERS MUST BE OF ONLY DIGITS **");
            }
            return employeeId;
        }catch (CustomException customException){
            System.out.println(customException.getMessage());
            return null;
        }
    }
    public Election(boolean validation) {
        if(validation){
            Scanner scannerInteger = new Scanner(System.in);

            int maxWrongTries = 2;
            boolean continueLooping = true;
            boolean status;
            String electionId;
            while (continueLooping) {
                try {
                    displayElectionMainMenu();
                    System.out.print("Enter the option out of the option: ");
                    int choice = scannerInteger.nextInt();

                    switch (choice) {
                        case 1:                                                                                             // Create Election
                            electionId = getValidElectionId();
                            if(electionId != null){
                                status = addElection(electionId);
                                if(status){
                                    System.out.println("ElectionId is Created Successfully");
                                    maxWrongTries = 2;
                                }
                            }
                            break;
                        case 2:                                                                                             // Remove Election
                            electionId = getValidElectionId();
                            if(electionId != null){
                                status = removeElection(electionId);
                                if(status){
                                    System.out.println("Election is Removed Successfully");
                                    maxWrongTries = 2;
                                }
                            }
                            break;

                        case 3:                                                                                             // Add Election Candidate
                            status = addCandidate();
                            if(status){
                                System.out.println("Election Candidate Added Successfully");
                                maxWrongTries = 2;
                            }
                            break;

                        case 4:                                                                                             // Remove Election Candidate
                            status = removeCandidate();
                            if(status){
                                System.out.println("Election Candidate Remove Successfully");
                                maxWrongTries = 2;
                            }
                            break;

                        case 5:
                            getElectionDetails();
                            break;

                        case 6:
                            continueLooping = false;
                            break;

                        default:
                            throw new CustomException("NOT A VALID CHOICE **");
                    }
                } catch (InputMismatchException inputMismatchException) {
                    System.out.println("NOT A VALID NUMBER **");
                } catch (CustomException customException) {
                    System.out.println(customException.getMessage());
                } finally {
                    if (maxWrongTries-- == 0) {
                        System.out.println("TOO MANY WRONG TRIES **");
                        continueLooping = false;
                    }
                }
            }
        }
    }
    void displayElectionMainMenu() {
        System.out.println("1. Add Election");
        System.out.println("2. Remove a Election");
        System.out.println("3. Add a Candidate");
        System.out.println("4. Remove a Candidate");
        System.out.println("5. Get Election Information");
        System.out.println("6. Go Back To Admin Menu");
    }
    boolean addElection(String electionId) {
        File actualElectionFile = null;
        File actualElectionFileCopy = null;
        FileWriter fileWriter = null;
        FileReader fileReader = null;
        BufferedWriter bufferedWriter = null;
        BufferedReader bufferedReader = null;

        try {
            actualElectionFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualElection.csv");
            actualElectionFileCopy = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualElectionCopy.csv");
            boolean actualElectionFileCreated = actualElectionFileCopy.createNewFile();
            if(!actualElectionFileCreated){
                throw new CustomException("ACTUAL ELECTION FILE COPY IS NOT CREATED **");
            }

            fileWriter = new FileWriter(actualElectionFileCopy);
            fileReader = new FileReader(actualElectionFile);

            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);

            boolean electionIdFound = false;
            String row;
            while ((row = bufferedReader.readLine()) != null) {                                                         // bufferReader.readLine() returns null if EOF.
                String[] rowWords = row.split(",");
                if (electionId.equals(rowWords[0])) {
                    electionIdFound = true;
                    break;
                }
                bufferedWriter.write(row);
                bufferedWriter.write("\n");
            }
            if (!electionIdFound) {
                bufferedWriter.write(electionId + "," + ",");
                return true;
            }else{
                throw new CustomException("ELECTION ID ALREADY PRESENT IN THE ACTUAL ELECTION FILE **");
            }

        } catch (IOException ioException) {
            System.out.println("IOEXCEPTION CAUGHT WHILE USING ACTUAL ELECTION FILE **");
        } catch (CustomException customException) {
            System.out.println(customException.getMessage());
        }finally {
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
        return false;
    }
    boolean removeElection(String employeeId){
        File actualElectionFile = null;
        File actualElectionFileCopy = null;
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try{
            actualElectionFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualElection.csv");
            actualElectionFileCopy = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualElectionCopy.csv");
            boolean actualElectionFileCreated = actualElectionFileCopy.createNewFile();
            if(!actualElectionFileCreated){
                throw new CustomException("ACTUAL ELECTION FILE COPY IS NOT CREATED **");
            }

            fileReader = new FileReader(actualElectionFile);
            fileWriter = new FileWriter(actualElectionFileCopy);

            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);

            String row;
            boolean electionIdFound = false;
            while((row = bufferedReader.readLine())!=null){
                String[] rowBreak = row.split(",");
                if(!(rowBreak[0].equals(employeeId))){
                    bufferedWriter.write(row);
                    bufferedWriter.write("\n");
                }else{
                    electionIdFound = true;
                }
            }
            if(!electionIdFound){
                throw new CustomException("ELECTION ID IS NOT FOUND **");
            }
            return true;
        }catch(IOException ioException){
            System.out.println("IOEXCEPTION IN ACTUAL ELECTION FILE **");
        }catch (CustomException customException) {
            System.out.println(customException.getMessage());
        }
        finally{
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
                    boolean actualFileCopyDeleted = actualElectionFileCopy.renameTo(new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualElectionFile.csv"));
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
        return false;
    }
    boolean addCandidate() {
        Scanner scannerString = new Scanner(System.in);
        int maxWrongTries = 2;
        boolean continueLooping = true;
        while (continueLooping) {
            try {
                String electionId = getValidElectionId();
                if(electionId == null){
                    throw new CustomException("NOT A VALID ELECTION ID **");
                }

                boolean validElectionId = checkElectionId(electionId);                                                  // Check the ElectionId in Election File.
                if(!validElectionId){
                    throw new CustomException("ELECTION ID IS NOT PRESENT IN THE ACTUAL ELECTION FILE **");
                }

                String employeeId = getValidEmployeeId();
                if(employeeId == null){
                    throw new CustomException("NOT A VALID EMPLOYEE ID **");
                }
                boolean validEmployeeId = checkEmployeeId(employeeId);                                                  // Check the EmployeeId in the DB employeeId.
                if (!validEmployeeId) {
                    throw new CustomException("EMPLOYEE ID IS NOT PRESENT IN THE ACTUAL USER FILE **");
                }

                boolean alreadyCandidate = checkElectionCandidateList(electionId, employeeId);
                if (alreadyCandidate) {
                    throw new CustomException("EMPLOYEE WITH THIS EMPLOYEE ID IS ALREADY A ELECTION CANDIDATE **");
                }

                System.out.print("Enter the Election Symbol for the Candidate (#, $, %, &, ?, @): ");                   // Election Candidate Symbol.
                char candidateSymbol = scannerString.next().charAt(0);
                if (!(candidateSymbol >= '#' && candidateSymbol <= '&') && !(candidateSymbol >= '?' && candidateSymbol <= '@')) {
                    throw new CustomException("CANDIDATE SYMBOL IS NOT VALID (#, $, %, &, ?, @) **") ;
                }

                boolean validateSymbol = checkCandidateSymbol(electionId, candidateSymbol);
                if (validateSymbol) {
                    throw new CustomException("ELECTION SYMBOL IS ALREADY TAKEN BY SOME OTHER ELECTION CANDIDATE **");
                }

                boolean addedToFile = addElectionCandidateToFile(electionId, employeeId, candidateSymbol);              // Adding Election Candidate to Election File.
                if(addedToFile){
                    continueLooping = false;
                    return true;
                }

            }catch (CustomException customException) {
                System.out.println(customException.getMessage());
            }finally {
                if (maxWrongTries-- == 0) {
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }
        return false;
    }
    boolean addElectionCandidateToFile(String electionId, String employeeId, char candidateSymbol){
        File actualElectionFile = null;
        File actualElectionFileCopy = null;
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try{
            actualElectionFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualElection.csv");
            actualElectionFileCopy = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualElectionCopy.csv");
            boolean actualElectionFileCreated = actualElectionFileCopy.createNewFile();
            if(!actualElectionFileCreated){
                throw new CustomException("ACTUAL ELECTION FILE COPY IS NOT CREATED **");
            }

            fileReader = new FileReader(actualElectionFile);
            fileWriter = new FileWriter(actualElectionFileCopy);
            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);

            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] rowData = row.split(",");
                if (electionId.equals(rowData[0])) {
                    String newLine = electionId + ",";
                    if (rowData.length == 1) {                                                                          // IN CASE OF NO CANDIDATE.
                        newLine += employeeId + " " + candidateSymbol + ":,";
                        bufferedWriter.write(newLine);
                    } else {
                        newLine += rowData[1] + employeeId + " " + candidateSymbol + ":,";
                        bufferedWriter.write(newLine);
                    }
                } else {
                    bufferedWriter.write(row);
                }
                bufferedWriter.write("\n");
            }
        } catch(IOException ioException){
            System.out.println("IOEXCEPTION CAUGHT WHILE USING ACTUAL ELECTION FILE **");
        } catch(CustomException customException){
            System.out.println(customException.getMessage());
        } finally{
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
                if(actualElectionFile != null && actualElectionFileCopy != null){
                    boolean actualFileDeleted = actualElectionFile.delete();
                    boolean actualFileCopyDeleted = actualElectionFileCopy.renameTo(new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualElectionFile.csv"));
                    if(!actualFileDeleted || !actualFileCopyDeleted){
                        throw new CustomException("FILE NAME NOT DELETED AND RENAMED **");
                    }
                }
            } catch (IOException e) {
                System.out.println("Exception caught while closing the file (checkElection)!!");
            } catch (CustomException customException){
                System.out.println(customException.getMessage());
            }
        }
        return false;
    }
    boolean checkElectionCandidateList(String electionId, String employeeId){
        File actualElectionFile;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            actualElectionFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualElection.csv");
            fileReader = new FileReader(actualElectionFile);
            bufferedReader = new BufferedReader(fileReader);

            String row;
            while ((row = bufferedReader.readLine()) != null) {                                                         // Checking EmployeeId in DB
                String[] rowBreak = row.split(",");
                if (electionId.equals(rowBreak[0]) && rowBreak.length>1) {
                    String[] candidates = rowBreak[1].split(":");
                    for (String candidate : candidates) {
                        if (candidate.length() > 4 && candidate.substring(0, 4).equals(employeeId)) {
                            return true;
                        }
                    }
                }
            }
        }catch(IOException ioException){
            System.out.println("IOEXCEPTION CAUGHT WHILE USING ACTUAL ELECTION FILE **");
        }
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                System.out.println("IOEXCEPTION CAUGHT WHILE CLOSING ACTUAL ELECTION FILE **");
            }
        }
        return false;
    }
    boolean checkCandidateSymbol(String electionId, char candidateSymbol){
        File actualElection;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            actualElection = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualElection.csv");
            fileReader = new FileReader(actualElection);
            bufferedReader = new BufferedReader(fileReader);

            String row;
            while ((row = bufferedReader.readLine()) != null) {                                                         // Checking EmployeeId in DB
                String[] rowBreak = row.split(",");
                if (electionId.equals(rowBreak[0]) && rowBreak.length>1) {
                    String[] candidates = rowBreak[1].split(":");
                    for (String candidate : candidates) {
                        char currentCandidateSymbol = candidate.substring(candidate.length() - 1).charAt(0);
                        if (currentCandidateSymbol == candidateSymbol) {
                            return true;
                        }
                    }
                }
            }
        }catch(IOException ioException){
            System.out.println("IOEXCEPTION CAUGHT WHILE USING ACTUAL ELECTION FILE **");
        }
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                System.out.println("IOEXCEPTION CAUGHT WHILE CLOSING ACTUAL ELECTION FILE **");
            }
        }
        return false;
    }
    boolean removeCandidate(){

        int maxWrongTries = 2;
        boolean continueLooping = true;

        while (continueLooping) {
            try {
                String electionId = getValidElectionId();
                if(electionId == null){
                    throw new CustomException("NOT A VALID ELECTION ID **");
                }

                boolean validElectionId = checkElectionId(electionId);                                                  // Check the ElectionId in Election File.
                if(!validElectionId){
                    throw new CustomException("ELECTION ID IS NOT PRESENT IN THE ACTUAL ELECTION FILE **");
                }

                String employeeId = getValidEmployeeId();
                if(employeeId == null){
                    throw new CustomException("NOT A VALID EMPLOYEE ID **");
                }

                boolean validEmployeeId = checkEmployeeId(employeeId);                                                  // Check the EmployeeId for the employeeId.
                if (!validEmployeeId) {
                    throw new CustomException("NO EMPLOYEE WITH THIS EMPLOYEE ID **");
                }

                boolean removeFromFile = removeElectionCandidateFromFile(electionId, employeeId);                       // Removing Election Candidate from Election File.
                if(removeFromFile){
                    continueLooping = false;
                    return true;
                }else{
                    throw new CustomException("NO ELECTION CANDIDATE WITH THIS ELECTION ID **");
                }
            }catch (CustomException customException) {
                System.out.println(customException.getMessage());
            }finally {
                if (maxWrongTries-- == 0) {
                    System.out.println("too many wrong tries !!");
                    continueLooping = false;
                }
            }
        }
        return false;
    }
    boolean removeElectionCandidateFromFile(String electionId, String employeeId){
        File actualElection = null;
        File actualElectionCopy = null;
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        boolean dataRemoved = false;
        try{
            actualElection = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualElection.csv");
            actualElectionCopy = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualElectionCopy.csv");
            boolean actualElectionFileCreated = actualElectionCopy.createNewFile();
            if(!actualElectionFileCreated){
                throw new CustomException("ACTUAL ELECTION FILE IS NOT CREATED **");
            }
            fileReader = new FileReader(actualElection);
            fileWriter = new FileWriter(actualElectionCopy);
            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);

            String row;
            while ((row = bufferedReader.readLine()) != null) {
                String[] rowData = row.split(",");
                if (electionId.equals(rowData[0])) {
                    String[] candidates = rowData[1].split(":");
                    StringBuilder newRowData = new StringBuilder(rowData[0] + ",");

                    for(int candidatesIndex = 0; candidatesIndex < candidates.length && candidates[candidatesIndex].length() > 4; candidatesIndex++){
                        if(candidates[candidatesIndex].substring(0,4).equals(employeeId)){
                            dataRemoved = true;
                        }else{
                            newRowData.append(candidates[candidatesIndex]).append(":");
                        }
                    }
                    if(rowData.length == 3){
                        newRowData.append(",").append(rowData[2]);
                    }else{
                        newRowData.append(",");
                    }
                    bufferedWriter.write(newRowData.toString());
                } else {
                    bufferedWriter.write(row);
                }
                bufferedWriter.write("\n");
            }
        } catch(IOException ioException){
            System.out.println("IOEXCEPTION CAUGHT IN ACTUAL ELECTION FILE **");
        } catch(CustomException customException){
            System.out.println(customException.getMessage());
        } finally{
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
                boolean fileDeleted = actualElection.delete();
                boolean fileRenamed = actualElectionCopy.renameTo(new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualElection.csv"));

                if(!fileDeleted || !fileRenamed){
                    throw new CustomException("FILE NOT DELETED AND RENAMED **");
                }
            } catch (IOException e) {
                System.out.println("IOEXCEPTION WHILE CLOSING ACTUAL ELECTION FILE **");
            } catch (CustomException customException){
                System.out.println(customException.getMessage());
            }
        }
        return dataRemoved;
    }
    boolean checkEmployeeId(String employeeId) {
        File actualUserFile;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            actualUserFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualUser.csv");
            fileReader = new FileReader(actualUserFile);
            bufferedReader = new BufferedReader(fileReader);

            String row;
            boolean userFound = false;
            while ((row = bufferedReader.readLine()) != null) {                                                         // Checking EmployeeId in the DB
                String[] rowData = row.split(",");
                if (employeeId.equals(rowData[0])) {
                    userFound = true;
                }
            }
            if (!userFound) {
                throw new CustomException("NO EMPLOYEE WITH THIS EMPLOYEE ID **");
            }else{
                return true;
            }
        } catch (IOException ioException) {
            System.out.println("IOEXCEPTION CAUGHT WHILE USING ACTUAL USER FILE **");
        } catch (CustomException customException) {
            System.out.println(customException.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                System.out.println("IOEXCEPTION CAUGHT WHILE CLOSING ACTUAL USER FILE **");
            }
        }
        return false;
    }
    boolean checkElectionId(String electionId) {
        File actualElection;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            actualElection = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualElection.csv");
            fileReader = new FileReader(actualElection);
            bufferedReader = new BufferedReader(fileReader);

            String row;
            boolean electionIdFound = false;
            while ((row = bufferedReader.readLine()) != null) {                                                         // Checking EmployeeId in DB
                String[] rowBreak = row.split(",");
                if (electionId.equals(rowBreak[0])) {
                    electionIdFound = true;
                    break;
                }
            }

            if (electionIdFound) {
                return true;
            }
        }catch(IOException ioException){
            System.out.println("IOEXCEPTION CAUGHT IN ACTUAL ELECTION FILE **");
        }
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                System.out.println("IOEXCEPTION WHILE CLOSING THE ACTUAL ELECTION FILE **");
            }
        }
        return false;
    }
    void displayGetElectionDetailMainMenu(){
        System.out.println("1. Show Elections");
        System.out.println("2. Show Particular Election Details");
        System.out.println("3. Go back to Admin Menu");
    }
    void getElectionDetails(){
        Scanner scannerInt = new Scanner(System.in);

        int maxWrongTries = 2;
        boolean continueLooping = true;
        while(continueLooping){
            try{
                displayGetElectionDetailMainMenu();
                int choice = scannerInt.nextInt();
                switch(choice){
                    case 1:
                        showElections();
                        maxWrongTries = 2;
                        break;
                    case 2:
                        showParticularElectionDetails();
                        maxWrongTries = 2;
                        break;
                    case 3:
                        continueLooping = false;
                        break;
                    default:
                        throw new CustomException("NOT A VALID OPTION **");
                }
            }catch(CustomException customException){
                System.out.println(customException.getMessage());
            }finally {
                maxWrongTries--;
                if(maxWrongTries == 0){
                    System.out.println("TOO MANY WRONG TRIES **");
                    continueLooping = false;
                }
            }
        }
    }
    void showElections(){
        File actualElection;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        int count = 0;
        boolean electionPresent = false;
        try{
            actualElection = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\voting\\system\\actualElection.csv");
            fileReader = new FileReader(actualElection);
            bufferedReader = new BufferedReader(fileReader);

            bufferedReader.readLine();                                                                            // Already read one line for the heading.
            String row;
            while((row = bufferedReader.readLine()) != null){
                String[] rowBreak = row.split(",");
                System.out.println("Election Id: "+ rowBreak[0]);
                electionPresent = true;
                count++;
            }
            if(electionPresent){
                System.out.println("Total Election are: " + count);
            }else{
                System.out.println("NO ELECTION ARE CURRENTLY ONGOING **");
            }
        }catch (IOException ioException){
            System.out.println("IOEXCEPTION WHILE USING ACTUAL ELECTION FILE **");
        }finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                System.out.println("IOEXCEPTION WHILE CLOSING ACTUAL ELECTION FILE **");
            }
        }
    }
    void showParticularElectionDetails(){
        File actualElection;
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try{
            String electionId = getValidElectionId();
            if(electionId == null){
                throw new CustomException("NOT A VALID ELECTION ID **");
            }

            boolean validElectionId = checkElectionId(electionId);                                                      // Check the ElectionId in Election File.
            if(!validElectionId){
                throw new CustomException("NO ELECTION ID IN ACTUAL ELECTION FILE **");
            }
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
                        for(int electionCandidatesIndex = 0; electionCandidatesIndex < electionCandidates.length; electionCandidatesIndex++){
                            String employeeId = electionCandidates[electionCandidatesIndex].substring(0,4);
                            char electionSymbol = electionCandidates[electionCandidatesIndex].charAt(electionCandidates[electionCandidatesIndex].length()-1);
                            User obj = new User(false);
                            String employeeName = obj.getEmployeeName(employeeId);
                            if(employeeName == null){
                                throw new CustomException("EMPLOYEE ID IS NOT PRESENT IN ACTUAL USER FILE **");
                            }
                            if(rowBreak.length>2){
                                String[] votesCount = rowBreak[2].split(":");
                                String[] votesCount2 = votesCount[electionCandidatesIndex].split(" ");
                                System.out.println("Election Candidate's Employee Id: " + employeeId +", Employee Name: " + employeeName + ", Election Symbol: " + electionSymbol + " and VOTES COUNT: "+votesCount2.length);
                            }else{
                                System.out.println("Election Candidate's Employee Id: " + employeeId +", Employee Name: " + employeeName + ", Election Symbol: " + electionSymbol + " and VOTES COUNT: " + "0");
                            }
                        }
                    }else{
                        System.out.println("Details of the Election With Election Id: " + electionId +", 0 Election Candidates, 0 Votes");
                    }
                }
            }

        }catch (CustomException customException){
            System.out.println(customException.getMessage());
        }catch (IOException ioException){
            System.out.println("IOEXCEPTION CAUGHT WHILE USING ACTUAL USER FILE **");
        }finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                System.out.println("IOEXCEPTION CAUGHT WHILE CLOSING ACTUAL USER FILE **");
            }
        }
    }
}