package com.choi.spring.IOC.myIOC.factory;

public interface BeanFactoryAware {
	void setBeanFactory(BeanFactory beanFactory) throws Exception;
}
