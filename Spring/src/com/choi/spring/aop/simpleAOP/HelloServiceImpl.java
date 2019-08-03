package com.choi.spring.aop.simpleAOP;

public class HelloServiceImpl implements HelloService {

	@Override
	public void sayHelloWorld() {
		System.out.println("hello,world!!");
	}

}
