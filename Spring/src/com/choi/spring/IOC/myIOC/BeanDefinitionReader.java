package com.choi.spring.IOC.myIOC;

import java.io.FileNotFoundException;

/**
 * 用于加载解析XML文件，遍历每一个bean标签，从标签中获取bean的id，class属性和propery标签下的属性
 * 并将刚获取到的id,class和propery标签下的属性保存到BeanDefinition对象中。
 * @author Administrator
 *
 */
public interface BeanDefinitionReader {

	void loadBeanDefinitions(String location) throws FileNotFoundException,Exception;
}
