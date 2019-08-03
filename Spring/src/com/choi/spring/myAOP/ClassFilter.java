package com.choi.spring.myAOP;

//ClassFilter限定在类级别上
public interface ClassFilter {
	
	boolean matchers(Class beanClass) throws Exception;
	
}
