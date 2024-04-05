package org.example;

public class Student {
    private int studentId;
    private String studentName;
    private String studentAddress;

    public void setStudentId(int studentId){
        System.out.println("setting id");
        this.studentId = studentId;
    }
    public void setStudentName(String studentName) {
        System.out.println("setting name");
        this.studentName = studentName;
    }
    public void setStudentAddress(String studentAddress) {
        System.out.println("setting address");
        this.studentAddress = studentAddress;
    }

    public Student(int studentId, String studentName, String studentAddress){
        super();
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAddress = studentAddress;
    }
    public Student() {
        super();
    }
    public String toString(){
        return "Student [studentId= " + studentId + ", studentName= " + studentName + ", student address= " + studentAddress + "]";
    }
}
