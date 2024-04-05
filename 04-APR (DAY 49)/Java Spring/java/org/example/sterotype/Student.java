package org.example.sterotype;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component  // either don't give any name and camelcase class name in configuration file.
@Component("obj") // and if given use the same in configuration file.
public class Student {
    @Value("Raman Malik")
    private String studentName;
    @Value("Haryana")
    private String city;

    @Value("#{temp}")
    private List<String> address;

    public List<String> getAddress(){
        return address;
    }
    public void setAddress(List<String> address){
        this.address = address;
    }
    public String getStudentName(){
        return studentName;
    }
    public void setStudentName(String studentName){
        this.studentName = studentName;
    }
    public String getCity(){
        return city;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String toString(){
        return "Student Name is: " + studentName + ", student city is: " + city +", and addresses are: " + address;
    }
}
