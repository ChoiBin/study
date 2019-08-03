package com.choi.spring.aop.simpleAOP;

public class SimpleAOPTest {
	public static void main(String[] args) {
		//1 创建一个MethodInvocation实现类
		MethodInvocation logTask = () -> System.out.println("log task start");
		HelloService helloService = new HelloServiceImpl();
		
		//2 创建一个Advice
		Advice beforAdivce = new BeforeAdvice(helloService, logTask);
		
		//3 为目标对象生成代理对象
		HelloService helloServiceProxy = (HelloService) SimpleAOP.getProxy(helloService, beforAdivce);
		
		helloServiceProxy.sayHelloWorld();
	}
}
