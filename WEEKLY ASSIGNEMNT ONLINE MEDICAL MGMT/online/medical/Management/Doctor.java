package online.medical.Management;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Doctor implements Serializable {
    private String doctorName;
    private String logInPassword;
    private String doctorSpecialization;
    private String doctorContactNumber;
    private ArrayList<LocalDateTime> appointmentTimings;
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
                appointments = new ArrayList<>();
                appointmentTimings = new ArrayList<>();
                break;
            }catch (CustomException customException){
                scannerString.nextLine();
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
                    throw new CustomException("NO DOCTORS WITH THESE DETAILS IN THE DB **");
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
    void doctorMainMenu(Doctor doctor){
        Scanner scannerInteger = new Scanner(System.in);
        int maxWrongTries = 3;
        while(--maxWrongTries >= 0){
            try{
                displayDoctorMainMenu();
                System.out.print("Select the option out of following: ");
                int choice = scannerInteger.nextInt();

                switch (choice){
                    case 1:
                        viewAppointmentList();
                        maxWrongTries = 3;
                        break;
                    case 2:
                        Patient patient = new Patient();
                        patient.viewPatientList();
                        maxWrongTries = 3;
                        break;
                    case 3:
                        boolean prescriptionGiven = givePrescription(doctor);
                        if(prescriptionGiven){
                            System.out.println("Prescription Added Successfully");
                        }
                        maxWrongTries = 3;
                        break;
                    case 4:
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
    boolean givePrescription(Doctor doctor){
        Scanner scannerString = new Scanner(System.in);
        if(doctor.appointments.size() == 0){
            System.out.println("NO APPOINTMENTS AS OF NOW **");
            return false;
        }

        int maxWrongTries = 3;

        File doctorFile = null, patientFile = null;
        FileInputStream fileInputStream = null, fileInputStream1 = null;
        FileOutputStream fileOutputStream = null, fileOutputStream1 = null;
        ObjectInputStream objectInputStream = null, objectInputStream1 = null;
        ObjectOutputStream objectOutputStream = null, objectOutputStream1 = null;

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


                Patient patient = new Patient();
                patient = patient.getPatientObject(patientName, contactNumber);
                if(patient == null){
                    System.out.println("NO PATIENT EXISTS IN THE PATIENT.TXT FILE **");
                }

                System.out.print("Enter the Prescription: ");
                String prescription = scannerString.nextLine();

                boolean appointmentFound = false;
                for(int appointmentIndex = 0; appointmentIndex < doctor.appointments.size(); appointmentIndex++){
                    if(doctor.appointments.get(appointmentIndex).getPatientName().equals(patientName) && doctor.appointments.get(appointmentIndex).getPatientContactNumber().equals(contactNumber)){
                        appointmentFound = true;
                        patient.setPrescription(prescription);
                    }
                }
                if(!appointmentFound){
                    throw new CustomException("APPOINTMENT NOT FOUND **");
                }else{                                                                                                  // updating it into the file

                    patientFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\medical\\Management\\Patients.txt");
                    fileInputStream = new FileInputStream(patientFile);
                    objectInputStream = new ObjectInputStream(fileInputStream);
                    ArrayList<Patient> patientArrayList = (ArrayList<Patient>) objectInputStream.readObject();
                    for(int patientArrayIndex = 0; patientArrayIndex < patientArrayList.size(); patientArrayIndex++){
                        if(patientArrayList.get(patientArrayIndex).getPatientName().equals(patientName) && patientArrayList.get(patientArrayIndex).getPatientContactNumber().equals(contactNumber)){
                            // REMOVE THE OBJECT FROM THE OBJECTS IN APPOINTMENT.
                            for(int patientAppointmentIndex = 0; patientAppointmentIndex < patient.getAppointments().size(); patientAppointmentIndex++){
                                if(patient.getAppointments().get(patientAppointmentIndex).getDoctorName().equals(doctor.getDoctorName())){
                                    patient.getAppointments().remove(patientAppointmentIndex);
                                }
                            }
                            patientArrayList.set(patientArrayIndex, patient);
                        }
                    }

                    fileOutputStream = new FileOutputStream(patientFile);
                    objectOutputStream = new ObjectOutputStream(fileOutputStream);

                    objectOutputStream.writeObject(patientArrayList);

                    // update doctor file as well as we gave prescription remove from the appointments.
                    doctorFile = new File("C:\\Users\\Raman\\IdeaProjects\\InternshipProgram\\src\\main\\java\\online\\medical\\Management\\Doctors.txt");
                    fileInputStream1 = new FileInputStream(doctorFile);
                    objectInputStream1 = new ObjectInputStream(fileInputStream1);
                    ArrayList<Doctor> doctorArrayList = (ArrayList<Doctor>) objectInputStream1.readObject();

                    for(int doctorArrayIndex = 0; doctorArrayIndex < doctorArrayList.size(); doctorArrayIndex++){
                        if(doctorArrayList.get(doctorArrayIndex).getDoctorName().equals(doctor.getDoctorName()) && doctorArrayList.get(doctorArrayIndex).getDoctorContactNumber().equals(doctor.getDoctorContactNumber())){
                            // REMOVE THE OBJECT FROM THE OBJECTS IN APPOINTMENT.
                            for(int doctorAppointmentIndex = 0; doctorAppointmentIndex < doctor.getAppointments().size(); doctorAppointmentIndex++){
                                if(doctor.getAppointments().get(doctorAppointmentIndex).getPatientName().equals(patientName)){
                                    doctor.getAppointments().remove(doctorAppointmentIndex);
                                }
                            }
                            doctorArrayList.set(doctorArrayIndex, doctor);
                        }
                    }

                    fileOutputStream1 = new FileOutputStream(doctorFile);
                    objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);

                    objectOutputStream1.writeObject(doctorArrayList);
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
            }finally {
                try{
                    if(objectInputStream != null){
                        objectInputStream.close();
                    }
                    if(objectInputStream1 != null){
                        objectInputStream1.close();
                    }
                    if(objectOutputStream != null){
                        objectOutputStream.close();
                    }
                    if(objectOutputStream1 != null){
                        objectOutputStream1.close();
                    }
                    if(fileInputStream != null){
                        fileInputStream.close();
                    }
                    if(fileInputStream1 != null){
                        fileInputStream1.close();
                    }
                    if(fileOutputStream != null){
                        fileOutputStream.close();
                    }
                    if(fileOutputStream1 != null){
                        fileOutputStream1.close();
                    }
                }catch (IOException ioException){
                    System.out.println("IO EXCEPTION CAUGHT WHILE CLOSING THE FILES **");
                }
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
    Doctor getDoctorObject(String docName, String docSpec){
        ArrayList<Doctor> doctorArrayList = getDoctorList();
        for(Doctor docObj: doctorArrayList){
            if(docObj.getDoctorName().equals(docName) && docObj.getDoctorSpecialization().equals(docSpec)){
                return docObj;
            }
        }
        return null;
    }
    boolean setAppointment(Doctor doctor, Patient patient, LocalDateTime appointmentDateTime){
        ArrayList<Patient> doctorAppointmentsArrayList = doctor.getAppointments();
        ArrayList<LocalDateTime> doctorAppointmentsTimingArrayList = doctor.getAppointmentTimings();

        for(int doctorAppointmentsArrayListIndex = 0; doctorAppointmentsArrayListIndex < doctorAppointmentsArrayList.size(); doctorAppointmentsArrayListIndex++){
            if(doctorAppointmentsArrayList.get(doctorAppointmentsArrayListIndex).equals(patient) || doctorAppointmentsTimingArrayList.get(doctorAppointmentsArrayListIndex).equals(appointmentDateTime)){
                return false;
            }
        }

        doctor.setAppointments(patient);
        doctor.setAppointmentTimings(appointmentDateTime);
        return true;
    }
    boolean removeAppointment(Doctor doctor, Patient patient){
        for(int appointmentIndex = 0; appointmentIndex < appointments.size(); appointmentIndex++){
            if(appointments.get(appointmentIndex).getPatientName().equals(patient.getPatientName()) && appointments.get(appointmentIndex).getPatientContactNumber().equals(patient.getPatientContactNumber())){
                appointments.remove(appointmentIndex);
                return true;
            }
        }
        return false;
    }
    void displayDoctorMainMenu(){
        System.out.println("1. View Appointment List");
        System.out.println("2. See Patient List");
        System.out.println("3. Give Prescription");
        System.out.println("4. Log Out");
    }


//    SETTER METHODS
    void setDoctorName(String doctorName){
        this.doctorName = doctorName;
    }
    void setAppointments(Patient patient){
        this.appointments.add(patient);
    }
    void setAppointmentTimings(LocalDateTime appointmentTimings){
        this.appointmentTimings.add(appointmentTimings);
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
    ArrayList<Patient> getAppointments(){
        return this.appointments;
    }
    ArrayList<LocalDateTime> getAppointmentTimings(){
        return this.appointmentTimings;
    }
}
