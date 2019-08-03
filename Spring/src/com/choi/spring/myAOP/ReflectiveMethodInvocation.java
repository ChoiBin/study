package com.choi.spring.myAOP;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;


public class ReflectiveMethodInvocation implements MethodInvocation {

	protected Object target;
	
	protected Method method;
	
	protected Object[] arguments;
	
		
	public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
		super();
		this.target = target;
		this.method = method;
		this.arguments = arguments;
	}

	/**
	 * MethodInvocation需要重写的方法
	 */
	@Override
	public Object[] getArguments() {
		// TODO Auto-generated method stub
		return arguments;
	}

	@Override
	public AccessibleObject getStaticPart() {
		// TODO Auto-generated method stub
		return method;
	}

	@Override
	public Object getThis() {
		// TODO Auto-generated method stub
		return target;
	}

	@Override
	public Object proceed() throws Throwable {
		// TODO Auto-generated method stub
		return method.invoke(target, arguments);
	}

	@Override
	public Method getMethod() {
		// TODO Auto-generated method stub
		return method;
	}

}
