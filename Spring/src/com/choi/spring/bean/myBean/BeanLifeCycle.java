package com.choi.spring.bean.myBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycle {
	
	public static void main(String[] args) {
		System.out.println("现在开始初始化容器！！");
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println("初始化容器成功！！");
		Person person = applicationContext.getBean("person",Person.class);
		System.out.println(person);
		
		System.out.println("现在开始关闭容器！！");
		((ClassPathXmlApplicationContext)applicationContext).registerShutdownHook();
	}

}
