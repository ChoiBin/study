package com.choi.spring.myAOP;

//记录将要被代理的类、该类的字节码信息、该类的接口
public class TargetSource {

	private Class<?> targetClass;
	
	private Class<?>[] interfaces;
	
	private Object target;
	
	public TargetSource(Object target, Class<?> targetClass, Class<?>... interfaces) {
		this.target = target;
		this.targetClass = targetClass;
		this.interfaces = interfaces;
	}
	
	public Class<?> getTargetClass(){
		return targetClass;
	}
	
	public Object getTarget(){
		return target;
	}
	
	public Class<?>[] getInterfaces(){
		return interfaces;
	}
}
