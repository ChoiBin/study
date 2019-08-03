package com.choi.spring.myAOP;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.aopalliance.intercept.MethodInterceptor;

public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {

	public JdkDynamicAopProxy(AdvisedSupport advised) {
		super(advised);
	}
	
	@Override
	public Object getProxy() {
		return Proxy.newProxyInstance(getClass().getClassLoader(), advisedSupport.getTargetSource().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		MethodMatcher methodMatcher = advisedSupport.getMethodMatcher();
		
		if(methodMatcher != null && methodMatcher.matchers(method, advisedSupport.getTargetSource().getTargetClass())){
			MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
			
			return methodInterceptor.invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(), method, args));
		}
		
		return method.invoke(advisedSupport.getTargetSource().getTarget(), args);
	}
}
