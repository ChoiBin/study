package com.choi.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class AnnotationApplication {


    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Circle circle = applicationContext.getBean(Circle.class);
        Triangle triangle = applicationContext.getBean(Triangle.class);
        Rectangle rectangle = applicationContext.getBean(Rectangle.class);
        rectangle.syaHi();
        triangle.sayHi();
        circle.sayHi();
//        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConditionConfig.class);
//        ConditionBean conditionBean = applicationContext.getBean(ConditionBean.class);
//        conditionBean.sayHi();
    }

}
