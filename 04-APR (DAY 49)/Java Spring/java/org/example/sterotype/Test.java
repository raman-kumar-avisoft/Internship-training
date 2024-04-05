package org.example.sterotype;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("sterotypeConfig.xml");
        Student student = context.getBean("ob",Student.class);
        System.out.println(student);
    }
}
