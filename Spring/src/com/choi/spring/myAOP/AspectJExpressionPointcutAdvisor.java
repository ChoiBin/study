package com.choi.spring.myAOP;

import org.aopalliance.aop.Advice;

public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

	private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

	private Advice advice;

	public void setExpression(String expression) {
		pointcut.setExpression(expression);
	}

	public void setAdvice(Advice advice) {
		this.advice = advice;
	}

	@Override
	public Pointcut getPointcut() {
		return pointcut;
	}

	@Override
	public Advice getAdvice() {
		return advice;
	}
}
