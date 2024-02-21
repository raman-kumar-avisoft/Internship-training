import java.util.Scanner;

class Student2 implements java.io.Serializable{
    private static int id = 1;
    private int uniqueId;
    private String name;
    private String group;

    void setDetails(String name, String group){
        setUniqueId();
        setName(name);
        setGroup(group);
        System.out.println("Student created Successfully !!");
    }
    void getDetails(){
        System.out.println("Student Unique Id is: " + getUniqueId());
        System.out.println("Student Name is: " + getName());
        System.out.println("Student Group is: " + getGroup());
    }
    void setUniqueId(){
        this.uniqueId = id++;
    }
    void setName(String name){
        this.name = name;
    }
    void setGroup(String group){
        this.group = group;
    }
    int getUniqueId(){
        return this.uniqueId;
    }
    String getName(){
        return this.name;
    }
    String getGroup(){
        return this.group;
    }
}