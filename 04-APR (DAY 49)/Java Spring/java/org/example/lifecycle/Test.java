package org.example.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        System.out.println("hello World");
//        ApplicationContext context = new ClassPathXmlApplicationContext("lifecycleConfig.xml");
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("lifecycleConfig.xml"); // to make it work destroy method of spring bean we have to use abstractApplicationContext and use registerShutDownHook()
        context.registerShutdownHook();

//        Samosa samosa1 = (Samosa) context.getBean("s1");
//        System.out.println(samosa1);
//
//        System.out.println("++++++++++++++++++++++++++");
//
////        ApplicationContext contextPepsi = new ClassPathXmlApplicationContext("lifecycleConfig.xml");
//
//        Pepsi pepsi1 = (Pepsi) context.getBean("p1");
//        System.out.println(pepsi1);

        Example example = (Example) context.getBean("e1");
        System.out.println(example);
    }
}
