package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AmbiguityResolver {
    private int a;
    private int b;
    public AmbiguityResolver(double a , double b){
        this.a = (int) a;
        this.b = (int) b;
        System.out.println("Constructor: double, double");
    }
    public AmbiguityResolver(int a, int b){
        this.a = a;
        this.b = b;
        System.out.println("Constructor: int, int");
    }

    public void doSum(){
        System.out.println("Sum is-> " + (this.a + this.b));
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config5.xml");
        AmbiguityResolver ambiguityResolver = (AmbiguityResolver) context.getBean("ambiguityResolver");
        System.out.println(ambiguityResolver);

        ambiguityResolver.doSum();
    }
}

