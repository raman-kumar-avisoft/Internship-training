package task.management.system;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Task {
    private String taskTitle;
    private String taskDescription;
    private int priorityLevel;
    private LocalDateTime dueDate;
    private String status;
    private boolean taskCreated = false;

    Task(){                                                                                                             // INITIALIZING ATTRIBUTES PARENT METHOD.
        setAttributes();
    }
    void setAttributes(){
        Scanner scannerInteger = new Scanner(System.in);
        Scanner scannerString = new Scanner(System.in);
        int maxWrongTries = 3;

        while(--maxWrongTries >= 0){
            try{
                System.out.print("Enter the Task Title: ");
                String taskTitle = scannerString.nextLine();
                if(taskTitle.length() <= 3){
                    throw new CustomException("TASK TITLE LENGTH MUST BE GREATER THAN 3 CHARACTERS **");
                } else if(taskTitle.length() >= 20){
                    throw new CustomException("TASK TITLE LENGTH MUST BE LESS THAN 20 CHARACTERS **");
                }
                setTaskTitle(taskTitle);

                System.out.print("Enter the Task Description: ");
                String taskDescription = scannerString.nextLine();
                if(taskDescription.length() < 10){
                    throw new CustomException("TASK DESCRIPTION LENGTH MUST BE GREATER THAN 10 CHARACTERS **");
                } else if(taskDescription.length() >= 200){
                    throw new CustomException("TASK DESCRIPTION LENGTH MUST BE LESS THAN 200 CHARACTERS **");
                }
                setTaskDescription(taskDescription);

                System.out.print("Enter the Priority Level: ");
                int priorityLevel = scannerInteger.nextInt();
                if(priorityLevel < 0){
                    throw new CustomException("TASK PRIORITY CANNOT BE LESS THAN 1 **");
                }else if(priorityLevel > 3){
                    throw new CustomException("TASK PRIORITY CANNOT BE MORE THAN 3 **");
                }
                setPriorityLevel(priorityLevel);

                LocalDateTime currentDateTime = LocalDateTime.now();                                                    // GET CURRENT DATE AND TIME
                System.out.print("Enter the Due Date(yyyy-MM-dd HH:mm:ss): ");
                String dueDateString = scannerString.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dueDateTime = LocalDateTime.parse(dueDateString, formatter);
                int comparison = dueDateTime.compareTo(currentDateTime);

                if (comparison < 0 || comparison == 0) {
                    throw new CustomException("DUE DATE MUST A FUTURE DATE AND TIME **");
                }
                setDueDate(dueDateTime);

                setStatus("Pending");
                setTaskCreated(true);

                break;
            } catch(InputMismatchException inputMismatchException){
                System.out.println("NOT A VALID NUMBER **");
            } catch(DateTimeParseException dateTimeParseException){
                System.out.println("NOT A VALID DATE AND TIME **");
            } catch(CustomException customException){
                System.out.println(customException.getMessage());
            }
        }
        if(maxWrongTries < 0){
            System.out.println("TOO MANY WRONG TRIES **");
        }
    }
    void setTaskTitle(String taskTitle){
        this.taskTitle = taskTitle;
    }
    void setTaskDescription(String description){
        this.taskDescription = description;
    }
    void setPriorityLevel(int priorityLevel){
        this.priorityLevel = priorityLevel;
    }
    void setDueDate(LocalDateTime dueDate){
        this.dueDate = dueDate;
    }
    void setStatus(String status){
        this.status = status;
    }
    void setTaskCreated(boolean taskCreated){
        this.taskCreated = taskCreated;
    }
    String getTaskTitle(){
        return taskTitle;
    }
    String getTaskDescription(){
        return taskDescription;
    }
    int getPriorityLevel(){
        return priorityLevel;
    }
    LocalDateTime getDueDate(){
        return dueDate;
    }
    String getStatus(){
        return status;
    }
    boolean getTaskCreated(){
        return taskCreated;
    }

    void printDetails(){
        System.out.println("Task Title is: " + this.taskTitle);
        System.out.println("Task Description is: " + this.taskDescription);
        System.out.println("Task Priority Level is: " + this.priorityLevel);
        System.out.println("Task Due Date is: " + this.dueDate);
        System.out.println("Task Status is: " + this.status);
    }
}
