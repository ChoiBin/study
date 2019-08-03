package com.choi.spring.myAOP;

public interface Pointcut {

	ClassFilter getClassFilter();
	
	MethodMatcher getMethodMatcher();
	
}
