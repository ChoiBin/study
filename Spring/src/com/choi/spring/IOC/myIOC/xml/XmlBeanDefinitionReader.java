package com.choi.spring.IOC.myIOC.xml;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.choi.spring.IOC.myIOC.BeanDefinition;
import com.choi.spring.IOC.myIOC.BeanDefinitionReader;
import com.choi.spring.IOC.myIOC.BeanReference;
import com.choi.spring.IOC.myIOC.PropertyValue;

/**
 * 读取XML文件，读取设置bean信息
 * @author Administrator
 *
 */

public class XmlBeanDefinitionReader implements BeanDefinitionReader {

	private Map<String, BeanDefinition> registry;
	
	public XmlBeanDefinitionReader() {
		registry = new HashMap<>();
	}
	
	/**
	 * 读取XML文件
	 */
	@Override
	public void loadBeanDefinitions(String location) throws FileNotFoundException, Exception {
		//根据location建立文件字节流，获取字节流信息
		InputStream inputStream = new FileInputStream(location);
		//创建一个工厂，用户创建DocumentBuilder对象
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//创建DocumentBuilder对象
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		//创建Document对象，用于获取文件信息
		Document document = documentBuilder.parse(inputStream);
		//获取文件信息
		Element element = document.getDocumentElement();
		
		parseBeanDefinitions(element);
	}

	/**
	 * 遍历XML文件信息
	 * @param element
	 */
	private void parseBeanDefinitions(Element element) {
		NodeList nodes = element.getChildNodes();
		for(int i = 0;i < nodes.getLength();i++){
			Node node = nodes.item(i);
			if(node instanceof Element){
				Element ele = (Element) node;
				parseBeanDefinition(ele);
			}
		}
	}

	/**
	 * 从XML文件中获取bean标签中的id、class属性
	 * @param ele
	 */
	private void parseBeanDefinition(Element ele) {
		//获取id属性值
		String name = ele.getAttribute("id");
		//获取class属性值
		String className = ele.getAttribute("class");
		//创建一个BeanDefinition对象，用于存放bean信息
		BeanDefinition beanDefinition = new BeanDefinition();
		//设置bean的class属性
		beanDefinition.setBeanClassName(className);
		
		//设置每一个property标签下的属性值
		processProperty(ele,beanDefinition);
		
		//将bean存放在map中
		registry.put(name,beanDefinition);
	}

	private void processProperty(Element ele, BeanDefinition beanDefinition) {
		//获取bean标签下的property标签
		NodeList propertyNodes = ele.getElementsByTagName("property");
		//遍历每一个property标签
		for(int i = 0;i <propertyNodes.getLength();i++){
			Node propertyNode = propertyNodes.item(i);
			if(propertyNode instanceof Element){
				Element propertyElement = (Element) propertyNode;
				//获取property标签下的name属性值
				String name = propertyElement.getAttribute("name");
				//获取property标签下的value属性值
				String value = propertyElement.getAttribute("value");
				//如果value不为空且长度大于0，创建一个PropertyValue对象，用于存放property标签的属性值
				//并且将PropertyValue对象添加到ProperyValues这个列表中
				if(value != null && value.length() > 0){
					beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));			
				}
				//如果property中有ref属性
				else{
					String ref = propertyElement.getAttribute("ref");
					if(ref == null || ref.length() == 0){
						throw new IllegalArgumentException();
					}
					//创建一个BeanReference对象，用于存放ref属性
					BeanReference beanReference = new BeanReference(ref);
					//创建一个PropertyValue对象，用于存放property标签的属性值
					//并且将PropertyValue对象添加到ProperyValues这个列表中
					beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
				}
			}
		}
	}
	
	public Map<String, BeanDefinition> getRegistry(){
		return registry;
	}

}
