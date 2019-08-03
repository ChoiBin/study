package com.choi.spring.IOC.simpleIOC;

import org.junit.Test;

public class SimpleIOCTest {
	@Test
	public void getBean() throws Exception {
		String location = SpringIOC.class.getClassLoader().getResource("springIOCTest.xml").getFile();
		SpringIOC bf = new SpringIOC(location);
		Wheel wheel = (Wheel) bf.getBean("wheel");
		System.out.println(wheel);
		Car car = (Car) bf.getBean("car");
		System.out.println(car);
	}
}
