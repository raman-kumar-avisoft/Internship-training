package org.example.auto.wiring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Emp {
    private Address address;
    public Address getAddresses(){
        return address;
    }
    @Autowired
    @Qualifier("address1")
    public void setAddress(Address address){
        System.out.println("Setting values");
        this.address = address;
    }
    public Emp(Address address){
        super();
        System.out.println("Inside constructor");
        this.address = address;
    }
    public Emp(){
        super();
    }
    public String toString(){
        return "Emp" + address;
    }
}
