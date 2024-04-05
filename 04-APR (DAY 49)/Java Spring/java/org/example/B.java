package org.example;

public class B {
    private int y;

    public void setY(int y){
        this.y = y;
    }
    public int getY(){
        return this.y;
    }

    public B(int y){
        super();
        this.y = y;
    }
    public B(){
        super();
    }

    public String toString(){
        return "value of y is: " + y;
    }
}
