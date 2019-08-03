package com.choi.spring.IOC.myIOC;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建一个用于存放多个PropertyValue 列表
 * @author Administrator
 *
 */
public class PropertyValues {
	
	private final List<PropertyValue> propertyValueList = new ArrayList<>();
	
	/**
	 * 添加propertyValue
	 * @param propertyValue
	 */
	public void addPropertyValue(PropertyValue propertyValue){
		this.propertyValueList.add(propertyValue);
	}
	
	/**
	 * 获取propertyValues列表
	 * @return
	 */
	public List<PropertyValue> getPropertyValues(){
		return this.propertyValueList;
	}
}
