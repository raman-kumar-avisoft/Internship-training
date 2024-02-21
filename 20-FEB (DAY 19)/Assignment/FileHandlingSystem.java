import java.io.*;
import java.util.*;

public class FileHandlingSystem {
    public static void main(String[] args) {
        ArrayList<Student2> student2ArrayList;
        System.out.println("File Handling System !!");
        Scanner sc = new Scanner(System.in);
        boolean cond = true;
        while(cond){
            try{
                displayMainMenu();
                System.out.println("Select an option from the above: ");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice){
                    case 1:
                        System.out.println("Adding a student in the database !!");
                        addStudent();
                        break;
                    case 2:
                        System.out.println("Reading from a file !!");
                        student2ArrayList = displayRecords();
                        System.out.println("hello");
                        for(int i=0; i<student2ArrayList.size(); i++){
                            student2ArrayList.get(i).getDetails();
                        }
                        break;
                    case 3:
                        System.out.println("ThankYou !!");
                        System.exit(1);
                        break;
                    default:
                        System.out.println("Select a valid number");
                        break;
                }
            }catch(InputMismatchException e){
                System.out.println("Enter a valid number !!");
            }
        }
    }
//
    static void addStudent() {
        try {
            Scanner sc = new Scanner(System.in);
            FileOutputStream file = new FileOutputStream("C:\\Users\\Raman\\OneDrive\\Desktop\\JAVA INTERSHIOP PROGRAM\\20-FEB (DAY 19)\\Assignment\\text.txt", true);
            System.out.println("NICE");
            ObjectOutputStream objj = new ObjectOutputStream(file);

            System.out.print("Enter the student name: ");
            String name = sc.nextLine();
            System.out.print("Enter the group name: ");
            String group = sc.nextLine();

            Student2 obj = new Student2();
            obj.setDetails(name, group);

//        openFile();
            objj.writeObject(obj);
            objj.close();
            file.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    static ArrayList<Student2> displayRecords() {
        ArrayList<Student2> studentList = new ArrayList<>();

        try {
            FileInputStream fileInput = new FileInputStream("C:\\Users\\Raman\\OneDrive\\Desktop\\JAVA INTERSHIOP PROGRAM\\20-FEB (DAY 19)\\Assignment\\text.txt");
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);

            while (true) {
                try {
                    Student2 student = (Student2) objectInput.readObject();
                    studentList.add(student);
                } catch (EOFException e) {
                    // End of file reached

                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("we are here");
            System.out.println(e.getMessage());
        }

        return studentList;
    }

    static void displayMainMenu(){
        System.out.println("\n1. Add Student");
        System.out.println("2. Read Data From the DB");
        System.out.println("3. EXIT");
    }
}