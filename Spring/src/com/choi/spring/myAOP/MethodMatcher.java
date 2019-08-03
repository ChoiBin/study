package com.choi.spring.myAOP;

import java.lang.reflect.Method;

//MethodMatcher限定在方法级别上
public interface MethodMatcher {

	Boolean matchers(Method method, Class beanClass);
}
