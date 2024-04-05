package org.example;

public class A {
    private int x;
    private B b;

//    for setter or property injection
    public void setX(int x){
        this.x = x;
    }
    public void setB(B b){
        this.b = b;
    }
    public int getX(){
        return this.x;
    }
    public B getB(){
        return this.b;
    }

    public A(int x, B b){
        super();
        this.x = x;
        this.b = b;
    }
    public A(){
        super();
    }
    public String toString(){
        return "Value of x is: " + x + " and object of class b is: " + b;
    }
}
