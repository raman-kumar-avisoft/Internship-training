import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class EmployeeMgmt{
    ArrayList<Employee> employeeList = new ArrayList<>();
    PriorityQueue<Employee> sortedEmployeeList = new PriorityQueue<>(Comparator.comparingDouble(Employee :: getSalary));

    EmployeeMgmt(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Employee Management System !!");
        boolean cond = true;
        while(cond){
            displayMainMenu();
            System.out.println("Select the option out of the following: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    System.out.println("Add employee");
                    addEmployee();
                    break;
                case 2:
                    System.out.println("Remove Employee");
                    removeEmployee();
                    break;
                case 3:
                    System.out.println("Display specific employee information");
                    displaySpecificEmployee();
                    break;
                case 4:
                    System.out.println("Display All employee information");
                    displayEmployee();
                    break;
                case 5:
                    System.out.println("Calculate total salary");
                    int salary = calculateTotalSalary();
                    System.out.println("Total Salary is: " + salary);
                    break;
                case 6:
                    System.out.println("Sort all employee based on salary");
                    sortEmployee();
                    break;
                case 7:
                    System.out.println("Update Employee information");
                    updateEmployee();
                    break;
                case 8:
                    cond = false;
                    break;
                default:
                    System.out.println("Wrong inputted value !! TRY AGAIN !!");
                    break;
            }
        }
    }
    void sortEmployee(){
        System.out.println("Displaying all employees after sorting is: ");
        while (!sortedEmployeeList.isEmpty()) {
            Employee obj = sortedEmployeeList.poll();
            obj.getDetails();
        }
    }
    void updateEmployee(){
        int index = searchById();
        if(index == -1){
            System.out.println("No Employee with this employee ID is found in the DB !!");
        }else{
            employeeList.get(index).setDetails();
        }
    }
    int calculateTotalSalary(){
        int sum = 0;
        for(int i=0; i<employeeList.size(); i++){
            sum += employeeList.get(i).getSalary();
        }
        return sum;
    }
    void displayEmployee(){
        System.out.println("All the Employee in the DB");
        for(int i=0; i<employeeList.size(); i++){
            employeeList.get(i).getDetails();
        }
    }
    void displaySpecificEmployee(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Display for specific UniqueId");
        System.out.println("2. Display for specific Name");
        System.out.println("3. Display for specific Position");
        System.out.print("Select one option out of the following: ");
        int choice = sc.nextInt();
        sc.nextLine();
        if(choice == 1){
            int index = searchById();
            if(index == -1){
                System.out.println("No Employee with this id is found in DB !!");
            }
            else{
                employeeList.get(index).getDetails();
            }
        }else if(choice == 2){
            System.out.print("Enter the Specific name you are searching in the DB: ");
            String empname = sc.nextLine();
            for(int i=0; i<employeeList.size(); i++){
                if(empname.equals(employeeList.get(i).getName())){
                    employeeList.get(i).getDetails();
                }
            }
        }else if(choice == 3){
            System.out.print("Enter the Specific position you are searching in the DB: ");
            String emppos = sc.nextLine();
            for(int i=0; i<employeeList.size(); i++){
                if(emppos.equals(employeeList.get(i).getPosition())){
                    employeeList.get(i).getDetails();
                }
            }
        }else{
            System.out.println("Wrong value inputted !!");
        }
    }
    void addEmployee(){
        Employee obj = new Employee();
        employeeList.add(obj);
        sortedEmployeeList.add(obj);
    }
    void removeEmployee(){
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Remove Using Employee Id");
        System.out.println("2. Using Name");
        System.out.println("3. Using Position");
        System.out.println("Choose one out of the following option");
        int choose = sc.nextInt();
        sc.nextLine();
        if(choose == 1){
            int i = searchById();
            if(i==-1){
                System.out.println("No employee with this id is found in the DB");
            }else{
                employeeList.remove(i);
                System.out.println("Employee is successfully removed");
            }
        }else if(choose == 2){
            removeByName();
        }else if(choose == 3){
            removeByPosition();
        }else{
            System.out.println("Wrong value inputted !!");
        }
    }
    void removeByPosition(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Position you want to delete from DB");
        String pos = sc.nextLine();
        for(int i=0; i<employeeList.size(); i++){
            if(pos.equals(employeeList.get(i).getPosition())){
                employeeList.remove(i);
                i--;
            }
        }
    }
    int searchById(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the id you are searching in employee list: ");
        int id = sc.nextInt();
        sc.nextLine();
        for(int i=0; i<employeeList.size(); i++){
            if(id == employeeList.get(i).getUniqueId()){
                return i;
            }
        }
        return -1;
    }
    void removeByName(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name you are searching in employee list: ");
        String nm = sc.nextLine();
        for(int i=0; i<employeeList.size(); i++){
            if(nm.equals(employeeList.get(i).getName())){
                employeeList.remove(i);
                i--;
            }
        }
    }
    void displayMainMenu(){
        System.out.println("\n1. Add Employee");
        System.out.println("2. Remove Employee");
        System.out.println("3. Display Specific Employee Information");
        System.out.println("4. Display All Employee Information");
        System.out.println("5. Calculate Total Salary");
        System.out.println("6. Sort all employee based on salary");
        System.out.println("7. Update employee Information");
        System.out.println("8. Exit");
    }

}