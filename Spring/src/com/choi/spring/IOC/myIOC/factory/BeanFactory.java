package com.choi.spring.IOC.myIOC.factory;

public interface BeanFactory {
	Object getBean(String beanId) throws Exception;
}
