package com.choi.condition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author : choibin
 * @date : 16:36  2019/11/16 0016
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext();
        configApplicationContext.getEnvironment().getSystemProperties().put("people","南方人");
        configApplicationContext.register(JavaConfig.class);
        configApplicationContext.refresh();
        Food food = (Food) configApplicationContext.getBean("food");
        System.out.println(food.showName());
    }
}
