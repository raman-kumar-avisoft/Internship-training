package online.medical.Management;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin{
    private final String adminLogInId = "raman@123";
    private final String adminPassword = "strongPassword";
    private ArrayList<Doctor> doctorArrayList;
    private ArrayList<Patient> patientArrayList;
    boolean logInPage(){
        Scanner scannerString = new Scanner(System.in);

        int maxWrongTries = 3;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Admin LogIn Id: ");
                String logInId = scannerString.nextLine();

                System.out.print("Admin LogIn Password: ");
                String logInPassword = scannerString.nextLine();

                if(logInId.equals(this.adminLogInId) && logInPassword.equals(this.adminPassword)){
                    return true;
                }
                throw new CustomException("WRONG ADMIN LONGIN ID AND PASSWORD **");
            }catch (CustomException customException){
                System.out.println(customException.getMessage());
            }
        }
        return false;
    }
    void adminMainMenu(){
        Scanner scannerInteger = new Scanner(System.in);
        int maxWrongTries = 3;
        while(--maxWrongTries >= 0){
            try{
                displayAdminMainMenu();
                System.out.print("Select the Option out of the Following: ");
                int choice = scannerInteger.nextInt();
//                int choice = 1;

                switch(choice){
                    case 1:
                        boolean doctorAdded = addDoctor();
                        if(doctorAdded){
                            System.out.println("Doctor Added Successfully");
                        }
                        maxWrongTries = 2;
                        break;
                    case 2:
                        boolean doctorRemoved = removeDoctorInArrayList();
                        if(doctorRemoved){
                            System.out.println("Doctor Removed Successfully");
                        }
                        maxWrongTries = 2;
                        break;
                    case 3:
                        boolean doctorUpdated = updateDoctorInArrayList();
                        if(doctorUpdated){
                            System.out.println("Doctor Updated Successfully");
                        }
                        maxWrongTries = 2;
                        break;
                    case 4:
                        boolean doctorViewed = viewDoctorInArrayList();
                        if(doctorViewed){
                            System.out.println("Doctor Viewed Successfully");
                        }
                        maxWrongTries = 2;
                        break;
                    case 5:
                        boolean patientAdded = addPatient();
                        if(patientAdded){
                            System.out.println("Patient Added Successfully");
                        }
                        maxWrongTries = 2;
                        break;
                    case 6:
                        boolean patientRemoved = removePatientInArrayList();
                        if(patientRemoved){
                            System.out.println("Patient Removed Successfully");
                        }
                        maxWrongTries = 2;
                        break;
                    case 7:
                        boolean patientUpdated = updatePatientInArrayList();
                        if(patientUpdated){
                            System.out.println("Patient Updated Successfully");
                        }
                        maxWrongTries = 2;
                        break;
                    case 8:
                        boolean patientViewed = viewPatientInArrayList();
                        if(patientViewed){
                            System.out.println("Doctor Viewed Successfully");
                        }
                        maxWrongTries = 2;
                        break;
                    case 9:
                        maxWrongTries = -2;
                        break;
                    default:
                        System.out.println("NOT A VALID OPTION **");
                        break;
                }

            } catch (InputMismatchException inputMismatchException){
                System.out.println("NOT A VALID NUMBER **");
            }
//            catch (CustomException customException){
//                System.out.println(customException.getMessage());
//            }
        }
    }
    boolean addDoctor(){
        Doctor doctor = new Doctor();
        doctor.setDetails();
        if(doctor.getDoctorValid()){
            boolean doctorAdded = addDoctorInArrayList(doctor);
            return true;
        }else{
            return false;
        }
    }
    boolean addDoctorInArrayList(Doctor doctor){
        File doctorsFile;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try{
            doctorsFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\medical\\Management\\Doctors.txt");
            fileInputStream = new FileInputStream(doctorsFile);
            doctorArrayList = new ArrayList<>();
            if (doctorsFile.exists() && doctorsFile.isFile() && doctorsFile.length() != 0) {
                objectInputStream = new ObjectInputStream(fileInputStream);
                doctorArrayList = (ArrayList<Doctor>) objectInputStream.readObject();
            }
//            else{
//                throw new CustomException("FILE NOT FOUND OR NULL POINTER EXCEPTION **");
//            }

            fileOutputStream = new FileOutputStream(doctorsFile);
            for(Doctor obj: doctorArrayList){
                if(obj.getDoctorName().equals(doctor.getDoctorName()) && obj.getDoctorSpecialization().equals(doctor.getDoctorSpecialization()) ){
                    objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject(doctorArrayList);
                    throw new CustomException("DOCTOR WITH THIS NAME AND SPECIALIZATION ALREADY PRESENT IN THE DB **");
                }
            }
            doctorArrayList.add(doctor);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(doctorArrayList);
            return true;
        }catch(IOException ioException){
            System.out.println("IO EXCEPTION CAUGHT WHILE WORKING WITH DOCTORS.TXT FILE **");
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("CLASS NOT FOUND EXCEPTION CAUGHT WHILE WORKING WITH DOCTORS.TXT FILE **");
        } catch(CustomException customException){
            System.out.println(customException.getMessage());
        } finally {
            try{
                if(objectInputStream != null){
                    objectInputStream.close();
                }
                if(objectOutputStream != null){
                    objectOutputStream.close();
                }
                if(fileInputStream != null){
                    fileInputStream.close();
                }
                if(fileOutputStream != null){
                    fileOutputStream.close();
                }
            }catch (IOException ioException){
                System.out.println("IO EXCEPTION CAUGHT WHILE CLOSING DOCTOR.TXT FILES.");
            }
        }
        return false;
    }
    boolean removeDoctorInArrayList(){
        Scanner scannerString = new Scanner(System.in);
        File doctorsFile;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try{
            System.out.print("Enter the Doctor Name: ");
            String doctorName = scannerString.nextLine();

            System.out.print("Enter the Contact Number: ");
            String contactNumber = scannerString.next();

            if(contactNumber.length() != 10){
                throw new CustomException("CONTACT NUMBER LENGTH MUST BE OF 10 DIGITS **");
            }

            for(int contactNumberIndex = 0; contactNumberIndex < contactNumber.length(); contactNumberIndex++){
                if(contactNumber.charAt(contactNumberIndex) < '0' || contactNumber.charAt(contactNumberIndex) > '9'){
                    throw new CustomException("NOT A VALID CONTACT NUMBER **");
                }
            }

            doctorsFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\medical\\Management\\Doctors.txt");
            fileInputStream = new FileInputStream(doctorsFile);
            doctorArrayList = new ArrayList<>();
            if (doctorsFile.exists() && doctorsFile.isFile() && doctorsFile.length() != 0) {
                objectInputStream = new ObjectInputStream(fileInputStream);
                doctorArrayList = (ArrayList<Doctor>) objectInputStream.readObject();
            } else{
                throw new CustomException("FILE NOT FOUND OR NULL POINTER EXCEPTION **");
            }

            fileOutputStream = new FileOutputStream(doctorsFile);
            int index = 0;
            boolean doctorFound = false;
            for(Doctor obj: doctorArrayList){
                if(obj.getDoctorName().equals(doctorName) && obj.getDoctorContactNumber().equals(contactNumber) ){
                    doctorFound = true;
                    doctorArrayList.remove(index);
                }
                index++;
            }
            if(!doctorFound){
                throw new CustomException("NO DOCTOR FOUND WITH THESE DETAILS IN THE DB **");
            }

            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(doctorArrayList);
            return true;
        }catch(IOException ioException){
            System.out.println("IO EXCEPTION CAUGHT WHILE WORKING WITH DOCTORS.TXT FILE **");
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("CLASS NOT FOUND EXCEPTION CAUGHT WHILE WORKING WITH DOCTORS.TXT FILE **");
        } catch(CustomException customException){
            System.out.println(customException.getMessage());
        } finally {
            try{
                if(objectInputStream != null){
                    objectInputStream.close();
                }
                if(objectOutputStream != null){
                    objectOutputStream.close();
                }
                if(fileInputStream != null){
                    fileInputStream.close();
                }
                if(fileOutputStream != null){
                    fileOutputStream.close();
                }
            }catch (IOException ioException){
                System.out.println("IO EXCEPTION CAUGHT WHILE CLOSING DOCTOR.TXT FILES.");
            }
        }
        return false;
    }
    boolean updateDoctorInArrayList(){
        Scanner scannerString = new Scanner(System.in);
        File doctorsFile;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try{
            System.out.print("Enter the Doctor Name: ");
            String doctorName = scannerString.nextLine();

            System.out.print("Enter the Contact Number: ");
            String contactNumber = scannerString.next();

            if(contactNumber.length() != 10){
                throw new CustomException("CONTACT NUMBER LENGTH MUST BE OF 10 DIGITS **");
            }

            for(int contactNumberIndex = 0; contactNumberIndex < contactNumber.length(); contactNumberIndex++){
                if(contactNumber.charAt(contactNumberIndex) < '0' || contactNumber.charAt(contactNumberIndex) > '9'){
                    throw new CustomException("NOT A VALID CONTACT NUMBER **");
                }
            }

            doctorsFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\medical\\Management\\Doctors.txt");
            fileInputStream = new FileInputStream(doctorsFile);
            doctorArrayList = new ArrayList<>();
            if (doctorsFile.exists() && doctorsFile.isFile() && doctorsFile.length() != 0) {
                objectInputStream = new ObjectInputStream(fileInputStream);
                doctorArrayList = (ArrayList<Doctor>) objectInputStream.readObject();
            } else{
                throw new CustomException("FILE NOT FOUND OR NULL POINTER EXCEPTION **");
            }

            fileOutputStream = new FileOutputStream(doctorsFile);
            int index = 0;
            boolean doctorFound = false;
            for(Doctor obj: doctorArrayList){
                if(obj.getDoctorName().equals(doctorName) && obj.getDoctorContactNumber().equals(contactNumber) ){
                    doctorFound = true;
                    int maxWrongTries = 3;
                    while(--maxWrongTries >= 0){
                        try{
                            System.out.print("Enter the new Contact Number: ");
                            String newContactNumber = scannerString.next();

                            if(newContactNumber.length() != 10){
                                throw new CustomException("CONTACT NUMBER LENGTH MUST BE OF 10 DIGITS **");
                            }

                            for(int contactNumberIndex = 0; contactNumberIndex < newContactNumber.length(); contactNumberIndex++){
                                if(newContactNumber.charAt(contactNumberIndex) < '0' || newContactNumber.charAt(contactNumberIndex) > '9'){
                                    throw new CustomException("NOT A VALID CONTACT NUMBER **");
                                }
                            }
                            obj.setDoctorContactNumber(newContactNumber);
                            break;
                        }catch (CustomException customException){
                            System.out.println(customException.getMessage());
                        }
                    }
                    if(maxWrongTries < 0 ){
                        throw new CustomException("TOO MANY WRONG TRIES ** ");
                    }
                }
            }
            if(!doctorFound){
                throw new CustomException("NO DOCTOR FOUND WITH THESE DETAILS IN THE DB **");
            }

            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(doctorArrayList);
            return true;
        }catch(IOException ioException){
            System.out.println("IO EXCEPTION CAUGHT WHILE WORKING WITH DOCTORS.TXT FILE **");
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("CLASS NOT FOUND EXCEPTION CAUGHT WHILE WORKING WITH DOCTORS.TXT FILE **");
        } catch(CustomException customException){
            System.out.println(customException.getMessage());
        } finally {
            try{
                if(objectInputStream != null){
                    objectInputStream.close();
                }
                if(objectOutputStream != null){
                    objectOutputStream.close();
                }
                if(fileInputStream != null){
                    fileInputStream.close();
                }
                if(fileOutputStream != null){
                    fileOutputStream.close();
                }
            }catch (IOException ioException){
                System.out.println("IO EXCEPTION CAUGHT WHILE CLOSING DOCTOR.TXT FILES.");
            }
        }
        return false;
    }
    boolean viewDoctorInArrayList(){
        File doctorsFile;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try{
            doctorsFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\medical\\Management\\Doctors.txt");
            fileInputStream = new FileInputStream(doctorsFile);
            doctorArrayList = new ArrayList<>();
            if (doctorsFile.exists() && doctorsFile.isFile() && doctorsFile.length() != 0) {
                objectInputStream = new ObjectInputStream(fileInputStream);
                doctorArrayList = (ArrayList<Doctor>) objectInputStream.readObject();
            } else{
                throw new CustomException("FILE NOT FOUND OR NULL POINTER EXCEPTION **");
            }

            if(doctorArrayList.size() == 0){
                System.out.println("NO DOCTORS ARE PRESENT CURRENTLY **");
            }else{
                System.out.println("TOTAL NUMBER OF DOCTORS ARE : " + doctorArrayList.size());
                for(Doctor obj: doctorArrayList){
                    System.out.println("DOCTOR NAME: " + obj.getDoctorName() + ", DOCTOR SPECIALISATION: " + obj.getDoctorSpecialization() + " AND CONTACT NUMBER: " + obj.getDoctorContactNumber());
                }
            }
            return true;
        }catch(IOException ioException){
            System.out.println("IO EXCEPTION CAUGHT WHILE WORKING WITH DOCTORS.TXT FILE **");
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("CLASS NOT FOUND EXCEPTION CAUGHT WHILE WORKING WITH DOCTORS.TXT FILE **");
        } catch(CustomException customException){
            System.out.println(customException.getMessage());
        } finally {
            try{
                if(objectInputStream != null){
                    objectInputStream.close();
                }
                if(fileInputStream != null){
                    fileInputStream.close();
                }
            }catch (IOException ioException){
                System.out.println("IO EXCEPTION CAUGHT WHILE CLOSING DOCTOR.TXT FILES.");
            }
        }
        return false;
    }
    boolean addPatient(){
        Patient patient = new Patient();
        patient.setDetails();
        if(patient.getPatientValid()){
            boolean doctorAdded = addPatientInArrayList(patient);
            if(doctorAdded){
                return true;
            }
        }
        return false;
    }
    boolean addPatientInArrayList(Patient patient){
        File patientsFile;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try{
            patientsFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\medical\\Management\\Patients.txt");
            fileInputStream = new FileInputStream(patientsFile);
            patientArrayList = new ArrayList<>();
            if (patientsFile.exists() && patientsFile.isFile() && patientsFile.length() != 0) {
                objectInputStream = new ObjectInputStream(fileInputStream);
                patientArrayList = (ArrayList<Patient>) objectInputStream.readObject();
            }

            fileOutputStream = new FileOutputStream(patientsFile);
            for(Patient obj: patientArrayList){
                if(obj.getPatientName().equals(patient.getPatientName()) && obj.getPatientContactNumber().equals(patient.getPatientContactNumber()) ){
                    objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    objectOutputStream.writeObject(patientArrayList);
                    throw new CustomException("PATIENT WITH THIS NAME AND CONTACT DETAILS ALREADY PRESENT IN THE DB **");
                }
            }
            patientArrayList.add(patient);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(patientArrayList);
            return true;
        }catch(IOException ioException){
            System.out.println("IO EXCEPTION CAUGHT WHILE WORKING WITH PATIENTS.TXT FILE **");
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("CLASS NOT FOUND EXCEPTION CAUGHT WHILE WORKING WITH PATIENTS.TXT FILE **");
        } catch(CustomException customException){
            System.out.println(customException.getMessage());
        } finally {
            try{
                if(objectInputStream != null){
                    objectInputStream.close();
                }
                if(objectOutputStream != null){
                    objectOutputStream.close();
                }
                if(fileInputStream != null){
                    fileInputStream.close();
                }
                if(fileOutputStream != null){
                    fileOutputStream.close();
                }
            }catch (IOException ioException){
                System.out.println("IO EXCEPTION CAUGHT WHILE CLOSING PATIENTS.TXT FILES.");
            }
        }
        return false;
    }
    boolean removePatientInArrayList(){
        Scanner scannerString = new Scanner(System.in);
        File patientsFile;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try{
            System.out.print("Enter the Patient Name: ");
            String doctorName = scannerString.nextLine();

            System.out.print("Enter the Contact Number: ");
            String contactNumber = scannerString.next();

            if(contactNumber.length() != 10){
                throw new CustomException("CONTACT NUMBER LENGTH MUST BE OF 10 DIGITS **");
            }

            for(int contactNumberIndex = 0; contactNumberIndex < contactNumber.length(); contactNumberIndex++){
                if(contactNumber.charAt(contactNumberIndex) < '0' || contactNumber.charAt(contactNumberIndex) > '9'){
                    throw new CustomException("NOT A VALID CONTACT NUMBER **");
                }
            }

            patientsFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\medical\\Management\\Patients.txt");
            fileInputStream = new FileInputStream(patientsFile);
            patientArrayList = new ArrayList<>();
            if (patientsFile.exists() && patientsFile.isFile() && patientsFile.length() != 0) {
                objectInputStream = new ObjectInputStream(fileInputStream);
                doctorArrayList = (ArrayList<Doctor>) objectInputStream.readObject();
            } else{
                throw new CustomException("FILE NOT FOUND OR NULL POINTER EXCEPTION **");
            }

            fileOutputStream = new FileOutputStream(patientsFile);
            int index = 0;
            boolean doctorFound = false;
            for(Patient obj: patientArrayList){
                if(obj.getPatientName().equals(doctorName) && obj.getPatientContactNumber().equals(contactNumber) ){
                    doctorFound = true;
                    patientArrayList.remove(index);
                }
                index++;
            }
            if(!doctorFound){
                throw new CustomException("NO DOCTOR FOUND WITH THESE DETAILS IN THE DB **");
            }

            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(patientArrayList);
            return true;
        }catch(IOException ioException){
            System.out.println("IO EXCEPTION CAUGHT WHILE WORKING WITH PATIENT.TXT FILE **");
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("CLASS NOT FOUND EXCEPTION CAUGHT WHILE WORKING WITH PATIENT.TXT FILE **");
        } catch(CustomException customException){

        } finally {
            try{
                if(objectInputStream != null){
                    objectInputStream.close();
                }
                if(objectOutputStream != null){
                    objectOutputStream.close();
                }
                if(fileInputStream != null){
                    fileInputStream.close();
                }
                if(fileOutputStream != null){
                    fileOutputStream.close();
                }
            }catch (IOException ioException){
                System.out.println("IO EXCEPTION CAUGHT WHILE CLOSING DOCTOR.TXT FILES.");
            }
        }
        return false;
    }
    boolean updatePatientInArrayList(){
        Scanner scannerString = new Scanner(System.in);
        File patientsFile;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try{
            System.out.print("Enter the Patient Name: ");
            String patientName = scannerString.nextLine();

            System.out.print("Enter the Contact Number: ");
            String contactNumber = scannerString.next();

            if(contactNumber.length() != 10){
                throw new CustomException("CONTACT NUMBER LENGTH MUST BE OF 10 DIGITS **");
            }

            for(int contactNumberIndex = 0; contactNumberIndex < contactNumber.length(); contactNumberIndex++){
                if(contactNumber.charAt(contactNumberIndex) < '0' || contactNumber.charAt(contactNumberIndex) > '9'){
                    throw new CustomException("NOT A VALID CONTACT NUMBER **");
                }
            }

            patientsFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\medical\\Management\\Patients.txt");
            fileInputStream = new FileInputStream(patientsFile);
            patientArrayList = new ArrayList<>();
            if (patientsFile.exists() && patientsFile.isFile() && patientsFile.length() != 0) {
                objectInputStream = new ObjectInputStream(fileInputStream);
                patientArrayList = (ArrayList<Patient>) objectInputStream.readObject();
            } else{
                throw new CustomException("FILE NOT FOUND OR NULL POINTER EXCEPTION **");
            }

            fileOutputStream = new FileOutputStream(patientsFile);
            int index = 0;
            boolean doctorFound = false;
            for(Patient obj: patientArrayList){
                if(obj.getPatientName().equals(patientName) && obj.getPatientContactNumber().equals(contactNumber) ){
                    doctorFound = true;
                    int maxWrongTries = 3;
                    while(--maxWrongTries >= 0){
                        try{
                            System.out.print("Enter the new Contact Number: ");
                            String newContactNumber = scannerString.next();

                            if(newContactNumber.length() != 10){
                                throw new CustomException("CONTACT NUMBER LENGTH MUST BE OF 10 DIGITS **");
                            }

                            for(int contactNumberIndex = 0; contactNumberIndex < newContactNumber.length(); contactNumberIndex++){
                                if(newContactNumber.charAt(contactNumberIndex) < '0' || newContactNumber.charAt(contactNumberIndex) > '9'){
                                    throw new CustomException("NOT A VALID CONTACT NUMBER **");
                                }
                            }
                            obj.setPatientContactNumber(newContactNumber);
                            break;
                        }catch (CustomException customException){
                            System.out.println(customException.getMessage());
                        }
                    }
                    if(maxWrongTries < 0 ){
                        throw new CustomException("TOO MANY WRONG TRIES ** ");
                    }
                }
            }
            if(!doctorFound){
                throw new CustomException("NO PATIENT FOUND WITH THESE DETAILS IN THE DB **");
            }

            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(patientArrayList);
            return true;
        }catch(IOException ioException){
            System.out.println("IO EXCEPTION CAUGHT WHILE WORKING WITH PATIENTS.TXT FILE **");
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("CLASS NOT FOUND EXCEPTION CAUGHT WHILE WORKING WITH PATIENTS.TXT FILE **");
        } catch(CustomException customException){
            System.out.println(customException.getMessage());
        } finally {
            try{
                if(objectInputStream != null){
                    objectInputStream.close();
                }
                if(objectOutputStream != null){
                    objectOutputStream.close();
                }
                if(fileInputStream != null){
                    fileInputStream.close();
                }
                if(fileOutputStream != null){
                    fileOutputStream.close();
                }
            }catch (IOException ioException){
                System.out.println("IO EXCEPTION CAUGHT WHILE CLOSING DOCTOR.TXT FILES.");
            }
        }
        return false;
    }
    boolean viewPatientInArrayList(){
        File patientFile;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try{
            patientFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\medical\\Management\\Patients.txt");
            fileInputStream = new FileInputStream(patientFile);
            patientArrayList = new ArrayList<>();
            if (patientFile.exists() && patientFile.isFile() && patientFile.length() != 0) {
                objectInputStream = new ObjectInputStream(fileInputStream);
                patientArrayList = (ArrayList<Patient>) objectInputStream.readObject();
            } else{
                throw new CustomException("FILE NOT FOUND OR NULL POINTER EXCEPTION **");
            }

            if(patientArrayList.size() == 0){
                System.out.println("NO PATIENTS ARE PRESENT CURRENTLY **");
            }else{
                System.out.println("TOTAL NUMBER OF PATIENTS ARE : " + patientArrayList.size());
                for(Patient obj: patientArrayList){
                    System.out.println("PATIENT NAME: " + obj.getPatientName() + " AND CONTACT NUMBER: " + obj.getPatientContactNumber());
                }
            }
            return true;
        }catch(IOException ioException){
            System.out.println("IO EXCEPTION CAUGHT WHILE WORKING WITH PATIENTS.TXT FILE **");
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("CLASS NOT FOUND EXCEPTION CAUGHT WHILE WORKING WITH PATIENT.TXT FILE **");
        } catch(CustomException customException){
            System.out.println(customException.getMessage());
        } finally {
            try{
                if(objectInputStream != null){
                    objectInputStream.close();
                }
                if(fileInputStream != null){
                    fileInputStream.close();
                }
            }catch (IOException ioException){
                System.out.println("IO EXCEPTION CAUGHT WHILE CLOSING PATIENTS.TXT FILES.");
            }
        }
        return false;
    }
    void displayAdminMainMenu(){
        System.out.println("1. Add a Doctor");
        System.out.println("2. Remove a Doctor");
        System.out.println("3. Update Doctor");
        System.out.println("4. Show Doctors");
        System.out.println("5. Add a Patient");
        System.out.println("6. Remove a Patient");
        System.out.println("7. Update Patient");
        System.out.println("8. Show Patients");
        System.out.println("9. Go Back to Medical Management Class");
    }
}
