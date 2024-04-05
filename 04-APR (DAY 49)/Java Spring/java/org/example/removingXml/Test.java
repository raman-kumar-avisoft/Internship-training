package org.example.removingXml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JavaConfig.class);
        Student student = context.getBean("getStudent", Student.class);
        System.out.println(student);
        student.study();

//        context.close(); // if we want to close then reference is also of classPathXmlApplicationContext type as well.
    }
}
