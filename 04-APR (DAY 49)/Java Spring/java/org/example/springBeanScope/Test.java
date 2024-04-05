package org.example.springBeanScope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("springBeanScope.xml");
        Student student = context.getBean("obj", Student.class);
//        System.out.println(student);

        System.out.println(student.hashCode());
        Student student2 = context.getBean("obj", Student.class);
        System.out.println(student2.hashCode());

        Teacher teacher = (Teacher) context.getBean("teacher");
        Teacher teacher2 = (Teacher) context.getBean("teacher");
        System.out.println(teacher.hashCode());
        System.out.println(teacher2.hashCode());
    }
}