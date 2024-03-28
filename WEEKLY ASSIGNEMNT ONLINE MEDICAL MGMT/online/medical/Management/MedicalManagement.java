package online.medical.Management;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MedicalManagement  {
    MedicalManagement(){
        medicalManagementMainMenu();
    }
    void medicalManagementMainMenu(){
        Scanner scannerInteger = new Scanner(System.in);
        int maxWrongTries = 3;
        while(--maxWrongTries >= 0){
            try{
                displayMedicalManagementMainMenu();
                System.out.print("Select the option out of the following: ");
                int choice = scannerInteger.nextInt();
                switch (choice){
                    case 1:
                        Admin admin = new Admin();
                        boolean loggedIn = admin.logInPage();
                        if(loggedIn){
                            System.out.println("Admin Successfully LoggedIn");
                            admin.adminMainMenu();
                        }
                        maxWrongTries = 3;
                        break;
                    case 2:
                        Doctor doctor = new Doctor();
                        doctor = doctor.logInPage();
                        if(doctor != null){
                            System.out.println("Doctor LoggedIn Successfully");
                            doctor.doctorMainMenu(doctor);
                        }
                        maxWrongTries = 3;
                        break;
                    case 3:
                        Patient patient = new Patient();
                        patient = patient.logInPage();
                        if(patient != null){
                            System.out.println("Patient LoggedIn Successfully");
                            patient.patientMainMenu(patient);
                        }
                        maxWrongTries = 3;
                        break;
                    case 4:
                        System.out.println("THANK YOU !!");
                        System.exit(0);
                    default:
                        System.out.println("NOT A VALID CHOICE **");
                        break;
                }
            }catch (InputMismatchException inputMismatchException){
                System.out.println("NOT A VALID NUMBER **");
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES **");
        }
    }
    void displayMedicalManagementMainMenu(){
        System.out.println("1. AdminMenu");
        System.out.println("2. DoctorMenu");
        System.out.println("3. PatientMenu");
        System.out.println("4. Exit");
    }
    public static void main(String[] args) {
        MedicalManagement medicalManagement = new MedicalManagement();
    }
}
