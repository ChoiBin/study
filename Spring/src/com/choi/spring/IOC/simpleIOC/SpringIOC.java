package com.choi.spring.IOC.simpleIOC;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class SpringIOC {

	private Map<String, Object> beanMap = new HashMap<>();

	public SpringIOC(String location) throws Exception {
		loadBeans(location);
	}
	
	public Object getBean(String name) throws IllegalAccessException{
		Object bean = beanMap.get(name);
		if(bean == null){
			throw new IllegalAccessException();
		}
		return bean;
	}

	private void loadBeans(String location) throws Exception {

		//加载配置文件
		InputStream inputStream = new FileInputStream(location);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(inputStream);
		Element root = document.getDocumentElement();
		NodeList nodes = root.getChildNodes();

		//遍历bean标签
		for(int i = 0;i < nodes.getLength();i++){
			Node node = nodes.item(i);
			if(node instanceof Element){
				Element element = (Element) node;
				String id = element.getAttribute("id");
				String className = element.getAttribute("class");

				//加载beanClass
				Class beanClass = null;
				try {
					beanClass = Class.forName(className);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
				//创建bean
				Object bean = beanClass.newInstance();

				//遍历property标签
				NodeList propertyNodes = element.getElementsByTagName("property");
				for(int j = 0;j < propertyNodes.getLength();j++){
					Node propertyNode = propertyNodes.item(j);
					if(propertyNode instanceof Element){
						Element propertyElement = (Element) propertyNode;
						String name = propertyElement.getAttribute("name");
						String value = propertyElement.getAttribute("value");

						//利用反射将bean相关字段访问权限设为可访问
						Field declaredField = bean.getClass().getDeclaredField(name);
						declaredField.setAccessible(true);

						if(value != null && value.length() > 0){
							//将属性设置到相关字段中
							declaredField.set(bean, value);
						}else{
							String ref = propertyElement.getAttribute("ref");
							if(ref == null || ref.length() == 0){
								throw new IllegalArgumentException();
							}
							//将引用设置到相关字段
							declaredField.set(bean, getBean(ref));
						}
						//将bean注册到bean容器中
						registerBean(id,bean);
					}
				}

			}
		}


	}

	private void registerBean(String id, Object bean) {
		beanMap.put(id, bean);
	}

}
