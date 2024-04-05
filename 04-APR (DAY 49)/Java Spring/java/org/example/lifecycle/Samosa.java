package org.example.lifecycle;

import java.util.SortedMap;

public class Samosa {
    private double price;

    public void setPrice(double price){
        System.out.println("Setting Property");
        this.price = price;
    }
    public double getPrice(){
        return price;
    }

    public Samosa(){
        super();
    }
    public String toString(){
        return "Samosa: [Price: " + price +"]";
    }

    public void init(){
        System.out.println("Inside init() method of Samosa");
    }
    public void destroy(){
        System.out.println("Inside destroy() method");
    }
}
