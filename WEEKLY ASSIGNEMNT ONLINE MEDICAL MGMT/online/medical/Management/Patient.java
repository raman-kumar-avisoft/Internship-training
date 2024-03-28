package online.medical.Management;

import task.management.system.CustomException;

import javax.print.Doc;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Patient implements Serializable {
    private String patientName;
    private String patientPassword;
    private String patientContactNumber;
    private String prescription;
    private boolean patientValid = false;
    private ArrayList<Doctor> appointments = new ArrayList<>();

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
    Patient logInPage(){
    Scanner scannerString = new Scanner(System.in);
    File patientsFile;
    FileInputStream fileInputStream = null;
    ObjectInputStream objectInputStream = null;

    int maxWrongTries = 3;
    while(--maxWrongTries >= 0){
        try{
            System.out.print("Enter the Patient Name(LOGIN ID): ");
            String patientName = scannerString.nextLine();

            System.out.print("Enter the Patient Password: ");
            String patientPassword = scannerString.nextLine();

            patientsFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\medical\\Management\\Patients.txt");
            fileInputStream = new FileInputStream(patientsFile);
            if (patientsFile.exists() && patientsFile.isFile() && patientsFile.length() != 0) {

                objectInputStream = new ObjectInputStream(fileInputStream);
                ArrayList<Patient> patientArrayList = (ArrayList<Patient>) objectInputStream.readObject();

                for(Patient patientObj: patientArrayList){
                    if(patientObj.getPatientName().equals(patientName) && patientObj.getPatientPassword().equals(patientPassword)){
                        return patientObj;
                    }
                }
            }else{
                throw new online.medical.Management.CustomException("NO PATIENT WITH THESE DETAILS IN THE DB **");
            }
        } catch (IOException ioException){
            System.out.println("IO EXCEPTION CAUGHT WHILE WORKING WITH PATIENTS.TXT FILE **");
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("CLASS NOT FOUND EXCEPTION CAUGHT WHILE WORKING WITH PATIENTS.TXT FILE **");
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
                System.out.println("IO EXCEPTION CAUGHT WHILE CLOSING PATIENTS.TXT FILES.");
            }
        }
    }
    return null;
}
    void patientMainMenu(Patient patient){
    Scanner scannerInteger = new Scanner(System.in);
    int maxWrongTries = 3;
    while(--maxWrongTries >= 0){
        try{
            displayPatientMainMenu();
            System.out.print("Select the option out of following: ");
            int choice = scannerInteger.nextInt();

            switch (choice){
                case 1:
                    boolean appointmentStatus = bookAppointment(patient);
                    if(appointmentStatus){
                        System.out.println("Appointment Booked Successfully");
                    }
                    maxWrongTries = 3;
                    break;
                case 2:
                    boolean appointmentCancelled = cancelAppointment(patient);
                    if(appointmentCancelled){
                        System.out.println("Appointment Cancelled Successfully");
                    }
                    maxWrongTries = 3;
                    break;
                case 3:
                    Doctor doctor = new Doctor();
                    doctor.viewDoctorList();
                    maxWrongTries = 3;
                    break;
                case 4:
                    displayPrescription();
                    maxWrongTries = 3;
                    break;
                case 5:
                    displayAppointments();
                    maxWrongTries = 3;
                    break;
                case 6:
                    System.out.println("Log Out Successfully");
                    maxWrongTries = -1;
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
    boolean bookAppointment(Patient patient){
        Scanner scannerString = new Scanner(System.in);
        int maxWrongTries = 3;
        while(--maxWrongTries >= 0) {
            try {

                System.out.print("Enter the appointment date and time (in DD/MM/YYYY HH format): ");
                String dateTimeString = scannerString.nextLine(); // Input for appointment date and time

                // Define a DateTimeFormatter to parse the input string
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH");

                // Parse the input string to create a LocalDateTime object
                LocalDateTime currentDateTime = LocalDateTime.now();
                String formattedCurrentDateTime = currentDateTime.format(formatter);
                System.out.println("Current Date and Time: " + formattedCurrentDateTime);

                LocalDateTime appointmentDateTime = LocalDateTime.parse(dateTimeString, formatter);
                System.out.println("Appointment Date and Time: " + appointmentDateTime);

                // Example of comparing current date and time with an appointment date and time
                if (currentDateTime.isAfter(appointmentDateTime)) {
                    throw new CustomException("APPOINTMENT TIME MUST BE OF FUTURE NOT PAST **");
                }

                // Checking now the doctor data that this time and patient is not already taken an appointment and valid appointment timing does not clash with any other patient.

                System.out.print("Enter the Doctor Name: ");
                String doctorName = scannerString.nextLine();
                System.out.print("Enter the Doctor Specialization: ");
                String doctorSpecialization = scannerString.nextLine();

                Doctor doctor = new Doctor();
                doctor = doctor.getDoctorObject(doctorName, doctorSpecialization);
                if(doctor == null){
                    throw new CustomException("NO DOCTOR PRESENT WITH THESE DETAILS IN THE DB **");
                }
                System.out.println("Till1");
                boolean appointmentAdded = doctor.setAppointment(doctor, patient, appointmentDateTime);
                if(appointmentAdded){
                    // update the doctor.txt file.
                    File doctorsFile, patientsFile = null;
                    FileOutputStream fileOutputStream = null, fileOutputStream1 = null;
                    FileInputStream fileInputStream = null, fileInputStream1 = null;
                    ObjectInputStream objectInputStream = null, objectInputStream1 = null;
                    ObjectOutputStream objectOutputStream = null, objectOutputStream1 = null;


                    try{
                        doctorsFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\medical\\Management\\Doctors.txt");
                        fileInputStream = new FileInputStream(doctorsFile);
                        objectInputStream = new ObjectInputStream(fileInputStream);
                        ArrayList<Doctor> doctorArrayList = (ArrayList<Doctor>) objectInputStream.readObject();
                        for(int doctorArrayListIndex = 0; doctorArrayListIndex < doctorArrayList.size(); doctorArrayListIndex++){
                            if(doctorArrayList.get(doctorArrayListIndex).getDoctorName().equals(doctor.getDoctorName()) && doctorArrayList.get(doctorArrayListIndex).getDoctorContactNumber().equals(doctor.getDoctorContactNumber())){
                                doctorArrayList.set(doctorArrayListIndex, doctor);
                            }
                        }
                        fileOutputStream = new FileOutputStream(doctorsFile);
                        objectOutputStream = new ObjectOutputStream(fileOutputStream);

                        objectOutputStream.writeObject(doctorArrayList);

                        appointments.add(doctor);
                        patientsFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\medical\\Management\\Patients.txt");
                        fileInputStream1 = new FileInputStream(patientsFile);
                        objectInputStream1 = new ObjectInputStream(fileInputStream1);

                        ArrayList<Patient> patientsArrayList = (ArrayList<Patient>) objectInputStream1.readObject();
                        for(int patientsArrayListIndex = 0; patientsArrayListIndex < patientsArrayList.size(); patientsArrayListIndex++){
                            if(patientsArrayList.get(patientsArrayListIndex).getPatientName().equals(patient.getPatientName()) && patientsArrayList.get(patientsArrayListIndex).getPatientContactNumber().equals(patient.getPatientContactNumber())){
                                patientsArrayList.set(patientsArrayListIndex, patient);
                            }
                        }
                        fileOutputStream1 = new FileOutputStream(patientsFile);
                        objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);

                        objectOutputStream1.writeObject(patientsArrayList);

                        return true;
                    }catch (ClassNotFoundException classNotFoundException){
                        System.out.println("CLASS NOT EXCEPTION CAUGHT WHILE WORKING WITH DOCTORS.TXT FILE **");
                    }
                    catch(IOException ioException){
                        System.out.println("IO EXCEPTION CAUGHT WHILE USING DOCTOR.txt FILE **");
                    }finally {
                        try{
                            if(objectOutputStream != null){
                                objectOutputStream.close();
                            }
                            if(objectOutputStream1 != null){
                                objectOutputStream.close();
                            }
                            if(objectInputStream != null){
                                objectInputStream.close();
                            }
                            if(objectInputStream1 != null){
                                objectInputStream.close();
                            }
                            if(fileOutputStream != null){
                                fileOutputStream.close();
                            }
                            if(fileOutputStream1 != null){
                                fileOutputStream.close();
                            }
                            if(fileInputStream != null){
                                fileInputStream.close();
                            }
                            if(fileInputStream1 != null){
                                fileInputStream.close();
                            }
                        }catch (IOException ioException){
                            System.out.println("IO EXCEPTION CAUGHT WHILE CLOSING DOCTOR.txt FILE **");
                        }
                    }

                }else{
                    throw new CustomException("APPOINTMENT AT THAT TIME ALREADY PRESENT **");
                }
            } catch (CustomException customException) {
                System.out.println(customException.getMessage());
            }
        }
        return false;
    }
    boolean cancelAppointment(Patient patient){
        Scanner scannerString = new Scanner(System.in);

        if(this.appointments.size() == 0){
            System.out.println("NO APPOINTMENTS ARE BOOKED YET **");
            return false;
        }
        int maxWrongTries = 3;
        while(--maxWrongTries >= 0) {
            try {
                System.out.print("Enter the Doctor Name: ");
                String doctorName = scannerString.nextLine();
                System.out.print("Enter the Doctor Specialization: ");
                String doctorSpecialization = scannerString.nextLine();

                Doctor doctor = new Doctor();
                doctor = doctor.getDoctorObject(doctorName, doctorSpecialization);

                boolean appointmentFounded = false;
                for (int appointmentIndex = 0; appointmentIndex < appointments.size(); appointmentIndex++) {
                    if (appointments.get(appointmentIndex).getDoctorName().equals(doctorName) && appointments.get(appointmentIndex).getDoctorSpecialization().equals(doctorSpecialization)) {
                        appointmentFounded = true;
                        appointments.remove(appointmentIndex);
                    }
                }
                if (!appointmentFounded) {
                    throw new CustomException("NO APPOINTMENT WITH THESE DOCTOR DETAILS FOUND IN THE PATIENTS APPOINTMENT **");
                } else {
                    if (doctor == null) {
                        System.out.println("DOCTOR ALREADY DELETED **");
                    } else {
                        boolean appointmentRemoved = doctor.removeAppointment(doctor, patient);
                        if (appointmentRemoved) {

                            File doctorsFile, patientsFile = null;
                            FileOutputStream fileOutputStream = null, fileOutputStream1 = null;
                            FileInputStream fileInputStream = null, fileInputStream1 = null;
                            ObjectInputStream objectInputStream = null, objectInputStream1 = null;
                            ObjectOutputStream objectOutputStream = null, objectOutputStream1 = null;

                            try {
                                doctorsFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\medical\\Management\\Doctors.txt");
                                fileInputStream = new FileInputStream(doctorsFile);
                                objectInputStream = new ObjectInputStream(fileInputStream);
                                ArrayList<Doctor> doctorArrayList = (ArrayList<Doctor>) objectInputStream.readObject();
                                for (int doctorArrayListIndex = 0; doctorArrayListIndex < doctorArrayList.size(); doctorArrayListIndex++) {
                                    if (doctorArrayList.get(doctorArrayListIndex).getDoctorName().equals(doctor.getDoctorName()) && doctorArrayList.get(doctorArrayListIndex).getDoctorContactNumber().equals(doctor.getDoctorContactNumber())) {
                                        doctorArrayList.set(doctorArrayListIndex, doctor);
                                    }
                                }
                                fileOutputStream = new FileOutputStream(doctorsFile);
                                objectOutputStream = new ObjectOutputStream(fileOutputStream);

                                objectOutputStream.writeObject(doctorArrayList);

                                appointments.add(doctor);
                                patientsFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\medical\\Management\\Patients.txt");
                                fileInputStream1 = new FileInputStream(patientsFile);
                                objectInputStream1 = new ObjectInputStream(fileInputStream1);

                                ArrayList<Patient> patientsArrayList = (ArrayList<Patient>) objectInputStream1.readObject();
                                for (int patientsArrayListIndex = 0; patientsArrayListIndex < patientsArrayList.size(); patientsArrayListIndex++) {
                                    if (patientsArrayList.get(patientsArrayListIndex).getPatientName().equals(patient.getPatientName()) && patientsArrayList.get(patientsArrayListIndex).getPatientContactNumber().equals(patient.getPatientContactNumber())) {
                                        patientsArrayList.set(patientsArrayListIndex, patient);
                                    }
                                }
                                fileOutputStream1 = new FileOutputStream(patientsFile);
                                objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);

                                objectOutputStream1.writeObject(patientsArrayList);

                                return true;
                            } catch (ClassNotFoundException classNotFoundException) {
                                System.out.println("CLASS NOT EXCEPTION CAUGHT WHILE WORKING WITH DOCTORS.TXT FILE **");
                            } catch (IOException ioException) {
                                System.out.println("IO EXCEPTION CAUGHT WHILE USING DOCTOR.txt FILE **");
                            } finally {
                                try {
                                    if (objectOutputStream != null) {
                                        objectOutputStream.close();
                                    }
                                    if (objectOutputStream1 != null) {
                                        objectOutputStream.close();
                                    }
                                    if (objectInputStream != null) {
                                        objectInputStream.close();
                                    }
                                    if (objectInputStream1 != null) {
                                        objectInputStream.close();
                                    }
                                    if (fileOutputStream != null) {
                                        fileOutputStream.close();
                                    }
                                    if (fileOutputStream1 != null) {
                                        fileOutputStream.close();
                                    }
                                    if (fileInputStream != null) {
                                        fileInputStream.close();
                                    }
                                    if (fileInputStream1 != null) {
                                        fileInputStream.close();
                                    }
                                } catch (IOException ioException) {
                                    System.out.println("IO EXCEPTION CAUGHT WHILE CLOSING DOCTOR.txt FILE **");
                                }
                            }
                        } else {
                            throw new CustomException("APPOINTMENT NOT REMOVED FROM THE DOCTOR OBJECT **");
                        }
                    }
                }
            }catch(CustomException customException){
                System.out.println(customException.getMessage());
            }
        }
        return false;
    }
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
    void displayAppointments(){
        if(appointments == null || appointments.size() == 0){
            System.out.println("NO APPOINTMENT TILL NOW **");
            return ;
        }
        for(Doctor docObj: appointments){
            System.out.println("Dr. " + docObj.getDoctorName() + " with Specialization in: " + docObj.getDoctorSpecialization());
        }
    }
    Patient getPatientObject(String patName, String patContact){
        ArrayList<Patient> patientArrayList = getPatientList();
        if(patientArrayList == null){
            System.out.println("PATIENT LIST IS EMPTY **");
            return null;
        }
        for(Patient patObj: patientArrayList){
            if(patObj.getPatientName().equals(patName) && patObj.getPatientContactNumber().equals(patContact)){
                return patObj;
            }
        }
        return null;
    }
    void displayPatientMainMenu(){
    System.out.println("1. Book Appointment");
    System.out.println("2. Cancel Appointment");
    System.out.println("3. Doctors List");
    System.out.println("4. Display Prescription");
    System.out.println("5. View Appointments");
    System.out.println("6. Log Out");
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
    ArrayList<Doctor> getAppointments(){
        return appointments;
    }
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
