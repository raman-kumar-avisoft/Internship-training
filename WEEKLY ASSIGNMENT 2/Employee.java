import java.util.Scanner;

class Employee{
//    public int compareTo(Employee e){
//        if(this.getSalary() < e.getSalary()){
//            return -1;
//        }else if(this.getSalary() > e.getSalary()){
//            return 1;
//        }
//        return 0;
//    }
    private static int id = 1;
    private int uniqueId;
    private String name;
    private String position;
    private double salary;

    Employee(){
        System.out.println("Adding employee to the DB");
        setUniqueId(id++);
        setDetails();
    }
    void setDetails(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of the employee: ");
        setName((sc.nextLine()));
        System.out.print("Enter the Position of the employee: ");
        setPosition(sc.nextLine());
        System.out.print("Enter the salary of the employee: ");
        setSalary(sc.nextDouble());
        sc.nextLine();
    }
    void getDetails(){
        System.out.println("\nEmployee Id is: " + this.getUniqueId());
        System.out.println("Employee Name is: " + this.getName());
        System.out.println("Employee Position is: " + this.getPosition());
        System.out.println("Employee Salary is: " + this.getSalary());
        System.out.println();
    }
    void setUniqueId(int data){
        this.uniqueId = data;
    }
    void setName(String data) {
        this.name = data;
    }
    void setPosition(String data){
        this.position = data;
    }
    void setSalary(double data){
        this.salary = data;
    }
    int getUniqueId(){
        return this.uniqueId;
    }
    String getName(){
        return this.name;
    }
    String getPosition(){
        return this.position;
    }
    double getSalary(){
        return this.salary;
    }

    public static void main(String[] args) {

    }
}