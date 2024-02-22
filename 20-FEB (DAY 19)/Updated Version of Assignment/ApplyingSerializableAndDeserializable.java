
//package JAVA INTERSHIOP PROGRAM;

import java.io.*;
import java.lang.String;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


class Student3 implements Serializable {
    private int studentId;
    private String name;
    private double marks;

    Student3(int studentId, String name, double marks){
        this.studentId = studentId;
        this.name = name;
        this.marks = marks;
    }
    public String toString(){
        return this.studentId+", "+this.name+", "+this.marks;
    }
}
public class ApplyingSerializableAndDeserializable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        ArrayList<Student3> studentArrayList = new ArrayList<Student3>();
        File file = new File("C:\\Users\\Raman\\OneDrive\\Desktop\\JAVA INTERSHIOP PROGRAM\\20-FEB (DAY 19)\\Updated Version of Assignment\\text.txt");
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
        boolean cond = true;
        while(cond){
            try{
                displayMainMenu();
                int choice = sc.nextInt();
                switch (choice){
                    case 1:
                        if(file.isFile()){
                            ois = new ObjectInputStream(new FileInputStream(file));
                            studentArrayList = (ArrayList<Student3>) ois.readObject();
                            ois.close();
                        }
                        addStudent(studentArrayList);
                        oos = new ObjectOutputStream(new FileOutputStream(file));
                        oos.writeObject(studentArrayList);
                        oos.close();
                        System.out.println("Data entered successfully !!");
                        break;
                    case 2:
                        if(file.isFile()){
                            ois = new ObjectInputStream(new FileInputStream(file));
                            studentArrayList = (ArrayList<Student3>) ois.readObject();
                            ois.close();
                        }
                        for(int i=0; i<studentArrayList.size(); i++){
                            System.out.println(studentArrayList.get(i));
                        }
                        break;
                    case 3:
                        cond = false;
                        System.out.println("ThankYou !!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Enter a value out of option given !!");
                        break;
                }
            }catch(InputMismatchException e){
                System.out.println("Enter a valid option !!");
            }
        }
    }
    static void addStudent(ArrayList<Student3> studentArrayList){
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        int student = 0;
        System.out.println("Enter the number of student you want to insert");
        student = sc.nextInt();

        for(int i=0; i<student; i++){
            System.out.print("Enter a student Id: ");
            int studentId = sc.nextInt();

            System.out.print("Enter a student Name: ");
            String studentName = sc2.nextLine();

            System.out.print("Enter a student Marks: ");
            double studentMarks = sc.nextInt();

            Student3 obj = new Student3(studentId, studentName, studentMarks);
            studentArrayList.add(obj);
        }
    }
    static void displayMainMenu(){
        System.out.println();
        System.out.println("1. Adding student in a file");
        System.out.println("2. Displaying student details from the file");
        System.out.println("3. Exit");
    }
}




