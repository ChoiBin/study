package com.choi.spring.myAOP;

public interface PointcutAdvisor extends Advisor {

	Pointcut getPointcut();
	
}
