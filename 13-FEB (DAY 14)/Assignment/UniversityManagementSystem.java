import java.util.ArrayList;
import java.util.Scanner;
abstract class Person{
    private static int id = 1;
    private int uniqueid;
    private String name;
    private int age;

    Person(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the details of the Person");
        setUniqueId();
        System.out.print("Enter the Name of the Person: ");
        setName(sc.nextLine());
        System.out.print("Enter the Age of the Person: ");
        setAge(sc.nextInt());
        sc.nextLine();
    }
    void setUniqueId(){
        this.uniqueid = id++;
    }
    void setName(String data){
        this.name = data;
    }
    void setAge(int data){
        this.age = data;
    }
    int getUniqueId(){
        return this.uniqueid;
    }
    String getName(){
        return this.name;
    }
    int getAge(){
        return this.age;
    }
    abstract void getDetails();
}
class StudentClass extends Person{
    private int studentId;
    private ArrayList<Course> enrolledCourses = new ArrayList<>();
    StudentClass(){
        super();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the StudentId: ");
        setStudentId(sc.nextInt());
    }
    void getDetails(){
        System.out.println("Name is: " + getName());
        System.out.println("Student Id is: " + getStudentId());
        System.out.println("Age is: " + getAge());
        System.out.print("Courses Student Enrolled in: ");
        if(enrolledCourses.size() > 0){
            for(int i=0; i<enrolledCourses.size(); i++){
                if (i == enrolledCourses.size()-1){
                    System.out.print(enrolledCourses.get(i).getCourseName()+".");
                }else{
                    System.out.print(enrolledCourses.get(i).getCourseName()+", ");
                }
            }
        }else{
            System.out.print(" -- ");
        }
    }
    void setStudentId(int data){
        this.studentId = data;
    }
    void enrollCourse(Course obj){
        enrolledCourses.add(obj);
    }
    int getStudentId(){
        return this.studentId;
    }
    ArrayList<Course> getCoursesList(){
        return this.enrolledCourses;
    }
}
class Faculty extends Person{
    private int employeeId;
    private ArrayList<Course> taughtCourses = new ArrayList<>();

    Faculty(){
        super();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the EmployeeId: ");
        setEmployeeId(sc.nextInt());
    }
    void getDetails(){
        System.out.println("\nName is: " + getName());
        System.out.println("Employee Id is: " + getEmployeeId());
        System.out.println("Age is: " + getAge());
        System.out.print("Courses Student Enrolled in: ");
        if(taughtCourses.size()>0){
            for(int i=0; i<taughtCourses.size(); i++){
                if(i == taughtCourses.size()-1){
                    System.out.print(taughtCourses.get(i).getCourseName() + ".");
                }else{
                    System.out.print(taughtCourses.get(i).getCourseName() + ", ");
                }
            }
        }else{
            System.out.println(" -- ");
        }
    }
    void setTaughtCourses(Course obj){
        taughtCourses.add(obj);
    }
    ArrayList<Course> getTaughtCourses(){
        return this.taughtCourses;
    }
    void setEmployeeId(int data){
        this.employeeId = data;
    }
    int getEmployeeId(){
        return this.employeeId;
    }
    ArrayList<Course> getCoursesList(){
        return this.taughtCourses;
    }
}
class Course{
    private int courseCode;
    private String courseName;
    private double credits;

    Course(){
        System.out.println("Enter the details of the Course You want to add");
        insertDetails();
    }
    void insertDetails(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the course code: ");
        setCourseCode(sc.nextInt());
        sc.nextLine();
        System.out.print("Enter the course name: ");
        setCourseName(sc.nextLine());
        System.out.print("Enter the credits for the particular course: ");
        setCredits(sc.nextDouble());
        sc.nextLine();
    }
    void setCourseCode(int data){
        this.courseCode = data;
    }
    int getCourseCode(){
        return this.courseCode;
    }
    void setCourseName(String data){
        this.courseName = data;
    }
    String getCourseName(){
        return this.courseName;
    }
    void setCredits(double data){
        this.credits = data;
    }
    double getCredits(){
        return this.credits;
    }
}
class University{
    ArrayList<StudentClass> studentsList = new ArrayList<>();
    ArrayList<Course> coursesList = new ArrayList<>();
    ArrayList<Faculty> facultyList = new ArrayList<>();
    University(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to University Management System");
        boolean cond = true;
        while(cond){
            displayMainMenu();
            System.out.print("Select the option out of the following: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch){
                case 1:
                    System.out.println("Add/Remove Student");
                    System.out.println("1. add student");
                    System.out.println("2. remove student");
                    System.out.println("choose the option out of the following: ");
                    int op = sc.nextInt();
                    if(op == 1){
//                        System.out.println("Adding a student");
                        addStudent();
                    }else if(op == 2){
//                        System.out.println("removing a student");
                        removeStudent();
                    }else{
                        System.out.println("Wrong inputted value!!");
                    }
                    break;
                case 2:
                    System.out.println("Add/Remove Faculty");
                    System.out.println("1. add Faculty");
                    System.out.println("2. remove Faculty");
                    System.out.println("choose the option out of the following: ");
                    int op2 = sc.nextInt();
                    if(op2 == 1){
//                        System.out.println("For adding a faculty");
                        addFaculty();
                    }else if(op2 == 2){
//                        System.out.println("For removing a faculty");
                        removeFaculty();
                    }else{
                        System.out.println("Wrong inputted value!!");
                    }
                    break;
                case 3:
                    System.out.println("Add/Remove Course");
                    System.out.println("1. add Course");
                    System.out.println("2. remove Course");
                    System.out.println("choose the option out of the following: ");
                    int op3 = sc.nextInt();
                    if(op3 == 1){
//                        System.out.println("For adding a course");
                        addCourse();
                    }else if(op3 == 2){
//                        System.out.println("For removing a course");
                        removeCourse();
                    }else{
                        System.out.println("Wrong inputted value!!");
                    }
                    break;
                case 4:
                    System.out.println("Display Data");
                    System.out.println("1. Student Data");
                    System.out.println("2. Faculty Data");
                    System.out.println("3. Courses Offered by University");
                    System.out.println("4. Count of People");
                    System.out.print("Select the option out of the following: ");
                    int op4 = sc.nextInt();
                    sc.nextLine();

                    if(op4 == 1){
                        System.out.println("display student");
                        displayStudent();
                    }else if(op4 == 2){
                        System.out.println("Display faculty");
                        displayFaculty();
                    }else if(op4 == 3){
                        System.out.println("display course");
                        displayCourse();
                    }else if(op4 == 4){
                        System.out.println("Total Students: " + studentsList.size());
                        System.out.println("Total Faculty: " + facultyList.size());
                        System.out.println("Total Person Associated with University: " + (studentsList.size() + facultyList.size()));
                    }else{
                        System.out.println("Wrong inputted value");
                    }
                    break;
                case 5:
                    cond = false;
                    break;
                default:
                    System.out.println("Wrong value inputted !!");
                    break;
            }
        }
    }
    void removeFaculty(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Employee ID you want to remove: ");
        int empid = sc.nextInt();
        int j = -1;
        for(int i=0; i<facultyList.size(); i++){
            if(empid == facultyList.get(i).getEmployeeId()){
                j = i;
                facultyList.remove(j);
                System.out.println("Faculty Removed");
                break;
            }
        }
        if(j == -1){
            System.out.println("No student is found with this student Id");
        }
    }
    void displayFaculty(){
        if(facultyList.size()>0){
            System.out.println("\nTotal Faculty in the university are: " + facultyList.size() + "\n");
            for (int i=0; i<facultyList.size(); i++){
                facultyList.get(i).getDetails();
            }
        }else{
            System.out.println("FacultyList is empty");
        }
    }
    void addFaculty(){
        Scanner sc = new Scanner(System.in);
        Faculty obj = new Faculty();
        boolean cond = true;

        while(cond){
            System.out.print("Faculty Teaching some course: 1. YES  2. NO :  ");
            int opp = sc.nextInt();
            sc.nextLine();
            if(opp == 1){
                System.out.println("Enter the course id you want the faculty to teach: ");
                int cid = sc.nextInt();
                sc.nextLine();
                int j = -1;
                for(int i=0; i<coursesList.size(); i++){
                    if(coursesList.get(i).getCourseCode() == cid){
                        j = i;
                        break;
                    }
                }
                if(j==-1){
                    System.out.println("No course is found with this course id");
                }else{
                    obj.setTaughtCourses(coursesList.get(j));
                    System.out.println("course added successfully");
                }
            }
            else if(opp == 2){
                break;
            }else{
                System.out.println("wrong value inputted !!");
                continue;
            }
        }
        facultyList.add(obj);
    }
    void displayStudent(){
        if(studentsList.size()>0){
            System.out.println("\nTotal Student in the university are: " + studentsList.size() + "\n");
            for (int i=0; i<studentsList.size(); i++){
                studentsList.get(i).getDetails();
            }
        }else{
            System.out.println("StudentList is empty");
        }
    }
    void addCourse(){
        coursesList.add(new Course());
    }
    void removeCourse(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Course you want to remove: ");
        int cid = sc.nextInt();
        int j = -1;
        for(int i=0; i<coursesList.size(); i++){
            if(cid == coursesList.get(i).getCourseCode()){
                j = i;
                coursesList.remove(j);
                System.out.println("Course removed");
                break;
            }
        }
        if(j == -1){
            System.out.println("No course is found with this course Id");
        }
    }
    void removeStudent(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Student ID you want to remove: ");
        int stuid = sc.nextInt();
        int j = -1;
        for(int i=0; i<studentsList.size(); i++){
            if(stuid == studentsList.get(i).getStudentId()){
                j = i;
                studentsList.remove(j);
                System.out.println("Student Removed");
                break;
            }
        }
        if(j == -1){
            System.out.println("No student is found with this student Id");
        }
    }
    void displayCourse(){
        if(coursesList.size()>0){
            System.out.println("\nTotal courses available are: " + coursesList.size() + "\n");
            for(int i=0; i<coursesList.size(); i++){
                System.out.println("Course Code is: " + coursesList.get(i).getCourseCode());
                System.out.println("Course Name is: " + coursesList.get(i).getCourseName());
                System.out.println("Course Credits are: " + coursesList.get(i).getCredits());
                System.out.println();
            }
        }else{
            System.out.println("Course List is empty");
        }
    }
    void addStudent(){
        Scanner sc = new Scanner(System.in);
        StudentClass obj = new StudentClass();
        boolean cond = true;

        while(cond){
            System.out.print("Enroll Student in course: 1. YES  2. NO :  ");
            int opp = sc.nextInt();
            sc.nextLine();
            if(opp == 1){
                System.out.println("Enter the course id you want to enroll the student in: ");
                int cid = sc.nextInt();
                sc.nextLine();
                int j = -1;
                for(int i=0; i<coursesList.size(); i++){
                    if(coursesList.get(i).getCourseCode() == cid){
                        j = i;
                        break;
                    }
                }
                if(j==-1){
                    System.out.println("No course is found with this course id");
                }else{
                    obj.enrollCourse(coursesList.get(j));
                    System.out.println("course added successfully");
                }
            }
            else if(opp == 2){
                break;
            }else{
                System.out.println("wrong value inputted !!");
                continue;
            }
        }
        studentsList.add(obj);
    }
    void displayMainMenu(){
        System.out.println();
        System.out.println("1. Add/Remove Student");
        System.out.println("2. Add/Remove Faculty");
        System.out.println("3. Add/Remove Course");
        System.out.println("4. Display Data");
        System.out.println("5. Exit");
    }
}
public class UniversityManagementSystem {
    public static void main(String[] args) {
        new University();
    }
}