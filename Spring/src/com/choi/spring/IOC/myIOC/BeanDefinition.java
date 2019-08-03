package com.choi.spring.IOC.myIOC;

/**
 * 存放bean信息
 * @author Administrator
 *
 */
public class BeanDefinition {
	
	private Object bean;
	
	//存放bean标签中的class属性值
	private Class beanClass;
	
	//存放bean标签中的id属性值
	private String beanClassName;
	
	//用于存放每一个property标签
	private PropertyValues propertyValues = new PropertyValues();

	public BeanDefinition() {
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	public Class getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class beanClass) {
		this.beanClass = beanClass;
	}

	public String getBeanClassName() {
		return beanClassName;
	}

	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
		//根据className创建出beanClass
		try {
			this.beanClass = Class.forName(beanClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public PropertyValues getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(PropertyValues propertyValues) {
		this.propertyValues = propertyValues;
	}
	
	

}
