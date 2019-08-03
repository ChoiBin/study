package com.choi.spring.IOC.myIOC;

/**
 * 存放bean标签下的property标签的属性的名称和值 
 * @author Administrator
 *
 */
public class PropertyValue {
	
	//property标签的name值
	private final String name;
	
	//property标签的value值
	private final Object value;
	
	public PropertyValue(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public Object getValue() {
		return value;
	}
	
	

}
