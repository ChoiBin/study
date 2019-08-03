package com.choi.spring.myAOP;

import org.aopalliance.intercept.MethodInterceptor;

public class AdvisedSupport {
	
	private TargetSource targetSource;
	
	//MethodInterceptor接口被用来拦截指定的方法，对方法进行增强
	private MethodInterceptor methodInterceptor;
	
	//用于匹配指定方法
	private MethodMatcher methodMatcher;

	public TargetSource getTargetSource() {
		return targetSource;
	}

	public void setTargetSource(TargetSource targetSource) {
		this.targetSource = targetSource;
	}

	public MethodInterceptor getMethodInterceptor() {
		return methodInterceptor;
	}

	public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
		this.methodInterceptor = methodInterceptor;
	}

	public MethodMatcher getMethodMatcher() {
		return methodMatcher;
	}

	public void setMethodMatcher(MethodMatcher methodMatcher) {
		this.methodMatcher = methodMatcher;
	}
	
	

}
