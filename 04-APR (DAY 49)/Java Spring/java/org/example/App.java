package org.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
//        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
//        Student student1 = (Student) context.getBean("student2");
//        System.out.println(student1);

//        System.out.println( "Hello World!" );
//        ApplicationContext context = new ClassPathXmlApplicationContext("config2.xml");
//        Emp emp1 = (Emp) context.getBean("emp1");
//        System.out.println(emp1.getName());
//        System.out.println(emp1.getPhones());
//        System.out.println(emp1.getCourses());
//        System.out.println(emp1.getAddresses());

//        System.out.println( "Hello World!" );
//        ApplicationContext context = new ClassPathXmlApplicationContext("config3.xml");
//        A a = (A) context.getBean("a1");
//        System.out.println(a);

        System.out.println( "Hello World!" );
        ApplicationContext context = new ClassPathXmlApplicationContext("config4.xml");
        Person person = (Person) context.getBean("person");
        System.out.println(person);
    }
}