package org.example.auto.wiring;

public class Address {
    private String street;
    private String city;

    public void setStreet(String street){
        this.street = street;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getStreet(){
        return street;
    }
    public String getCity(){
        return city;
    }
    public String toString(){
        return "Address [street=" + street + ", city=" + city + "]";
    }
}
