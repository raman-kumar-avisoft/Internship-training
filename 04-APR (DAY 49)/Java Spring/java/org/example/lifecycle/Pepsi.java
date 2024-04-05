package org.example.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Pepsi implements InitializingBean, DisposableBean {
    private double price;

    public void setPrice(double price){
        this.price = price;
    }
    public double getPrice(){
        return price;
    }
    public Pepsi(){
        super();
    }
    public String toString(){
        return "Pepsi: [Price: " + price +"]";
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy() method of pepsi");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("init() method of pepsi");
    }
}
