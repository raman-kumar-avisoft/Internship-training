package org.example;

public class Person {
    private String name;
    private int personId;
    private Certi certi;

    public Person(String name, int personId, Certi certi){
        this.name = name;
        this.personId = personId;
        this.certi = certi;
    }

    public String toString(){
        return name + ", " + personId + ", " + certi;
    }
}
