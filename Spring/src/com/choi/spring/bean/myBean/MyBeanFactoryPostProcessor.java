package com.choi.spring.bean.myBean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	public MyBeanFactoryPostProcessor() {
		super();
		System.out.println("这是BeanFactoryPostProcessor实现类构造器！！");
	}
	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory arg0) throws BeansException {
		System.out.println("BeanFactoryPostProcessor调用postProcessBeanFactory方法");
		BeanDefinition beanDefinition = arg0.getBeanDefinition("person");
		System.out.println(beanDefinition + " bean properties: " + beanDefinition.getPropertyValues().toString());
		beanDefinition.getPropertyValues().addPropertyValue("phone","110");
	}

}
