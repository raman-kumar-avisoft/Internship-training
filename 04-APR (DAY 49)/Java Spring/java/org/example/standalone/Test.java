package org.example.standalone;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("standAloneConfig.xml");

        Person person1 = (Person) context.getBean("person", Person.class);
        System.out.println(person1);
        System.out.println(person1.getFriends().getClass().getName());

    }
}
