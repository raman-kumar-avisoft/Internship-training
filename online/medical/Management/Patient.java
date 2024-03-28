package online.medical.Management;

import task.management.system.CustomException;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Patient implements Serializable {
    private String patientName;
    private String patientPassword;
    private String patientContactNumber;
    private String prescription;
    private boolean patientValid = false;

    boolean setDetails(){
        Scanner scannerString = new Scanner(System.in);
        int maxWrongTries = 3;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the Patient Name: ");
                String patientName = scannerString.nextLine();

                System.out.print("Enter the Patient LogIn Password: ");
                String patientPassword = scannerString.next();

                if(patientPassword.length() <= 6){
                    throw new CustomException("PATIENT PASSWORD LENGTH MUST BE GREATER THAN 6 CHARACTERS **");
                }

                System.out.print("Enter the Contact Number of Patient: ");
                String contactNumber = scannerString.next();

                if(contactNumber.length() != 10){
                    throw new CustomException("CONTACT NUMBER LENGTH MUST BE OF 10 DIGITS **");
                }
                for(int contactNumberIndex = 0; contactNumberIndex < contactNumber.length(); contactNumberIndex++){
                    if(contactNumber.charAt(contactNumberIndex) < '0' || contactNumber.charAt(contactNumberIndex) > '9'){
                        throw new CustomException("NOT A VALID CONTACT NUMBER **");
                    }
                }

                setPatientName(patientName);
                setPatientPassword(patientPassword);
                setPatientContactNumber(contactNumber);
                setPatientValid(true);
                break;
            }catch (CustomException customException){
                System.out.println(customException.getMessage());
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES **");
            return false;
        }
        return true;
    }

//    MAIN METHODS
void patientMainMenu(){
    Scanner scannerInteger = new Scanner(System.in);
    int maxWrongTries = 3;
    while(--maxWrongTries >= 0){
        try{
            displayPatientMainMenu();
            System.out.println("Select the option out of following: ");
            int choice = scannerInteger.nextInt();

            switch (choice){
                case 1:
                    boolean appointmentBooked = bookAppointment();
                    if(appointmentBooked){
                        System.out.println("Appointment Booked Successfully");
                    }
                    break;
                case 2:
                    boolean appointmentCancelled = cancelAppointment();
                    if(appointmentCancelled){
                        System.out.println("Appointment Cancelled Successfully");
                    }
                    break;
                case 3:
                    Doctor doctor = new Doctor();
                    doctor.viewDoctorList();
                    break;
                case 4:
                    displayPrescription();
                    break;
                case 5:
                    System.out.println("Log Out Successfully");
                    break;
                default:
                    System.out.println("NOT A VALID OPTION **");
                    break;
            }
        }catch (InputMismatchException inputMismatchException){
            System.out.println("NOT A VALID NUMBER **");
        }
    }
}

//**********************************************************************************************************************
    boolean bookAppointment(){
        return true;
    }
    boolean cancelAppointment(){
        return true;
    }
//**********************************************************************************************************************

    void displayPrescription(){
        if(this.prescription == null){
            System.out.println("NO PRESCRIPTION TILL NOW **");
        }else{
            System.out.println("Prescription is: " + this.prescription);
        }
    }
    void viewPatientList(){
        ArrayList<Patient> patientsArrayList = getPatientList();
        if(patientsArrayList != null){
            for(Patient obj: patientsArrayList){
                System.out.println("Mr. " + obj.getPatientName() + " having Contact Number: " + obj.getPatientContactNumber());
            }
        }
    }
    ArrayList<Patient> getPatientList(){
        File patientsFile;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        ArrayList<Patient> patientsArrayList = new ArrayList<>();
        try{
            patientsFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\medical\\Management\\Patients.txt");
            fileInputStream = new FileInputStream(patientsFile);
            if (patientsFile.exists() && patientsFile.isFile() && patientsFile.length() != 0) {

                objectInputStream = new ObjectInputStream(fileInputStream);
                patientsArrayList = (ArrayList<Patient>) objectInputStream.readObject();
                return patientsArrayList;
            }else{
                throw new online.medical.Management.CustomException("NO PATIENT IN THE DB **");
            }
        } catch (IOException ioException){
            System.out.println("IO EXCEPTION CAUGHT WHILE WORKING WITH PATIENT.TXT FILE **");
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("CLASS NOT FOUND EXCEPTION CAUGHT WHILE WORKING WITH PATIENT.TXT FILE **");
        } catch (online.medical.Management.CustomException customException){
            System.out.println(customException.getMessage());
        }finally {
            try{
                if(objectInputStream != null){
                    objectInputStream.close();
                }
                if(fileInputStream != null){
                    fileInputStream.close();
                }
            }catch (IOException ioException){
                System.out.println("IO EXCEPTION CAUGHT WHILE CLOSING PATIENT.TXT FILES.");
            }
        }
        return null;
    }
void displayPatientMainMenu(){
    System.out.println("1. Book Appointment");
    System.out.println("3. Cancel Appointment");
    System.out.println("3. Doctors List");
    System.out.println("4. Display Prescription");
    System.out.println("5. Log Out");
}
//    SETTER METHODS
    void setPatientName(String name){
        this.patientName = name;
    }
    void setPatientPassword(String password){
        this.patientPassword = password;
    }
    void setPatientContactNumber(String contactNumber){
        this.patientContactNumber = contactNumber;
    }
    void setPatientValid(boolean status){
        this.patientValid = status;
    }
    void setPrescription(String prescription){
        this.prescription = prescription;
    }

//    GETTER METHODS
    String getPatientName(){
        return this.patientName;
    }
    String getPatientPassword(){
        return this.patientPassword;
    }
    String getPatientContactNumber(){
        return this.patientContactNumber;
    }
    boolean getPatientValid(){
        return this.patientValid;
    }
}
