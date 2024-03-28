package online.medical.Management;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Doctor implements Serializable {
    private String doctorName;
    private String logInPassword;
    private String doctorSpecialization;
    private String doctorContactNumber;

    private ArrayList<LocalDate> appointmentTimings;
    private ArrayList<Patient> appointments;
    private boolean doctorValid = false;

    boolean setDetails(){
        Scanner scannerString = new Scanner(System.in);
        int maxWrongTries = 3;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the Doctor's Name: ");
                String doctorName = scannerString.nextLine();

                System.out.print("Enter the Doctor's Specialization:(CARDIOLOGIST, DENTIST, ENT SPECIALIST, SURGEON, GENERAL, DERMATOLOGIST) ");
                String doctorSpecialization = scannerString.nextLine();
                if(!doctorSpecialization.equals("CARDIOLOGIST") && !doctorSpecialization.equals("DENTIST") && !doctorSpecialization.equals("ENT SPECIALIST") && !doctorSpecialization.equals("SURGEON") && !doctorSpecialization.equals("GENERAL") && !doctorSpecialization.equals("DERMATOLOGIST")){
                    throw new CustomException("NOT A VALID SPECIALIZATION **");
                }

                System.out.print("Enter the Doctor's LogInId Password: ");
                String doctorLogInIdPassword = scannerString.next();

                if(doctorLogInIdPassword.length() <= 6){
                    throw new CustomException("PASSWORD LENGTH MUST BE GREATER THAN 6 CHARACTERS **");
                }

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

                setDoctorName(doctorName);
                setLogInPassword(doctorLogInIdPassword);
                setDoctorSpecialization(doctorSpecialization);
                setDoctorContactNumber(contactNumber);
                setDoctorValid(true);
                break;
            }catch (CustomException customException){
                System.out.println(customException.getMessage());
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES");
            return false;
        }
        return true;
    }

//    MAIN METHODS
    Doctor logInPage(){
        Scanner scannerString = new Scanner(System.in);
        File doctorsFile;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        int maxWrongTries = 3;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the Doctor Name(LOGIN ID): ");
                String doctorName = scannerString.nextLine();

                System.out.print("Enter the Doctor Password: ");
                String doctorPassword = scannerString.nextLine();

                doctorsFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\medical\\Management\\Doctors.txt");
                fileInputStream = new FileInputStream(doctorsFile);
                if (doctorsFile.exists() && doctorsFile.isFile() && doctorsFile.length() != 0) {


                    objectInputStream = new ObjectInputStream(fileInputStream);
                    ArrayList<Doctor> doctorArrayList = (ArrayList<Doctor>) objectInputStream.readObject();

                    for(Doctor doctorObj: doctorArrayList){
                        if(doctorObj.getDoctorName().equals(doctorName) && doctorObj.getLogInPassword().equals(doctorPassword)){
                            return doctorObj;
                        }
                    }
                }else{
                    throw new CustomException("NO DOCTORS IN THE DB **");
                }
            } catch (IOException ioException){
                System.out.println("IO EXCEPTION CAUGHT WHILE WORKING WITH DOCTORS.TXT FILE **");
            } catch (ClassNotFoundException classNotFoundException) {
                System.out.println("CLASS NOT FOUND EXCEPTION CAUGHT WHILE WORKING WITH DOCTORS.TXT FILE **");
            } catch (CustomException customException){
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
                    System.out.println("IO EXCEPTION CAUGHT WHILE CLOSING DOCTOR.TXT FILES.");
                }
            }
        }
        return null;
    }
    void doctorMainMenu(){
        Scanner scannerInteger = new Scanner(System.in);
        int maxWrongTries = 3;
        while(--maxWrongTries >= 0){
            try{
                displayDoctorMainMenu();
                System.out.println("Select the option out of following: ");
                int choice = scannerInteger.nextInt();

                switch (choice){
                    case 1:
                        viewAppointmentList();
                        break;
                    case 2:
                        Patient patient = new Patient();
                        patient.viewPatientList();
                        break;
                    case 3:
                        boolean prescriptionGiven = givePrescription();
                        if(prescriptionGiven){
                            System.out.println("Prescription Added Successfully");
                        }
                        break;
                    case 4:
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
    boolean givePrescription(){
        Scanner scannerString = new Scanner(System.in);
        if(appointments.size() == 0){
            System.out.println("NO APPOINTMENTS AS OF NOW **");
            return false;
        }

        int maxWrongTries = 3;
        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the Patient Name: ");
                String patientName = scannerString.nextLine();

                System.out.print("Enter the Contact Number: ");
                String contactNumber = scannerString.nextLine();

                if(contactNumber.length() != 10){
                    throw new CustomException("CONTACT NUMBER LENGTH MUST BE OF 10 DIGITS **");
                }
                for(int contactNumberIndex = 0; contactNumberIndex < contactNumber.length(); contactNumberIndex++){
                    if(contactNumber.charAt(contactNumberIndex) < '0' || contactNumber.charAt(contactNumberIndex) > '9'){
                        throw new CustomException("NOT A VALID CONTACT NUMBER **");
                    }
                }

                System.out.print("Enter the Prescription: ");
                String prescription = scannerString.nextLine();

                boolean appointmentFound = false;
                for(int appointmentIndex = 0; appointmentIndex < appointments.size(); appointmentIndex++){
                    if(appointments.get(appointmentIndex).getPatientName().equals(patientName) && appointments.get(appointmentIndex).getPatientContactNumber().equals(contactNumber)){
                        appointmentFound = true;
                        appointments.get(appointmentIndex).setPrescription(prescription);
                    }
                }
                if(!appointmentFound){
                    throw new CustomException("APPOINTMENT NOT FOUND **");
                }else{                                                                                                  // updating it into the file
                    File patientFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\medical\\Management\\Patient.txt");
                    FileInputStream fileInputStream = new FileInputStream(patientFile);
                    FileOutputStream fileOutputStream = new FileOutputStream(patientFile);
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

                    ArrayList<Patient> patientArrayList = (ArrayList<Patient>) objectInputStream.readObject();
                    objectOutputStream.writeObject(patientArrayList);
                    return true;
                }
            }catch (IOException ioException){
                System.out.println("IO EXCEPTION CAUGHT WHILE WORKING WITH PATIENT.TXT FILE **");
            }
            catch (ClassNotFoundException classNotFoundException){
                System.out.println("CLASS NOT FOUND EXCEPTION WHILE WORKING WITH PATIENT.TXT FILE **");
            }
            catch(CustomException customException){
                System.out.println(customException.getMessage());
            }
        }
        return false;
    }
    void viewAppointmentList(){
        if(appointments.size() == 0){
            System.out.println("NO APPOINTMENTS AS OF NOW **");
        }
        for(int appointmentIndex = 0; appointmentIndex < appointments.size(); appointmentIndex++){
            System.out.println("Mr. " + appointments.get(appointmentIndex).getPatientName() + ", Contact Number: " + appointments.get(appointmentIndex).getPatientContactNumber() + " and Timing: " + appointmentTimings.get(appointmentIndex));
        }
    }
    void viewDoctorList(){
        ArrayList<Doctor> doctorsArrayList = getDoctorList();
        if(doctorsArrayList != null){
            for(Doctor obj: doctorsArrayList){
                System.out.println("Dr. " + obj.getDoctorName() + ", Speciality: " + obj.getDoctorSpecialization());
            }
        }
    }
    ArrayList<Doctor> getDoctorList(){
        File doctorsFile;
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        ArrayList<Doctor> doctorsArrayList = new ArrayList<>();
        try{
            doctorsFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\medical\\Management\\Doctors.txt");
            fileInputStream = new FileInputStream(doctorsFile);
            if (doctorsFile.exists() && doctorsFile.isFile() && doctorsFile.length() != 0) {

                objectInputStream = new ObjectInputStream(fileInputStream);
                doctorsArrayList = (ArrayList<Doctor>) objectInputStream.readObject();
                return doctorsArrayList;
            }else{
                throw new CustomException("NO DOCTORS IN THE DB **");
            }
        } catch (IOException ioException){
            System.out.println("IO EXCEPTION CAUGHT WHILE WORKING WITH DOCTORS.TXT FILE **");
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("CLASS NOT FOUND EXCEPTION CAUGHT WHILE WORKING WITH DOCTORS.TXT FILE **");
        } catch (CustomException customException){
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
                System.out.println("IO EXCEPTION CAUGHT WHILE CLOSING DOCTOR.TXT FILES.");
            }
        }
        return null;
    }
    void displayDoctorMainMenu(){
        System.out.println("1. View Appointment List");
        System.out.println("2. See Patient List");
        System.out.println("3. Give Prescription");
        System.out.println("5. Log Out");
    }
//    SETTER METHODS
    void setDoctorName(String doctorName){
        this.doctorName = doctorName;
    }
    void setDoctorSpecialization(String doctorSpecialization){
        this.doctorSpecialization = doctorSpecialization;
    }
    void setLogInPassword(String logInPassword){
        this.logInPassword = logInPassword;
    }
    void setDoctorValid(boolean status){
        this.doctorValid = status;
    }
    void setDoctorContactNumber(String contactNumber){
        this.doctorContactNumber = contactNumber;
    }

//    GETTER METHODS
    String getDoctorName(){
        return this.doctorName;
    }
    String getLogInPassword(){
        return this.logInPassword;
    }
    String getDoctorSpecialization(){
        return this.doctorSpecialization;
    }
    String getDoctorContactNumber(){
        return this.doctorContactNumber;
    }
    boolean getDoctorValid(){
        return this.doctorValid;
    }
}
