package com.choi.spring.IOC.myIOC.xml;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.choi.spring.IOC.myIOC.BeanDefinition;
import com.choi.spring.IOC.myIOC.BeanPostProcessor;
import com.choi.spring.IOC.myIOC.BeanReference;
import com.choi.spring.IOC.myIOC.PropertyValue;
import com.choi.spring.IOC.myIOC.factory.BeanFactory;
import com.choi.spring.IOC.myIOC.factory.BeanFactoryAware;

/**
 * 
 * 该类用于实例化、初始化bean
 * @author Administrator
 *
 */
public class XmlBeanFactory implements BeanFactory {

	private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
	
	private List<String> beanDefinitionNames = new ArrayList<>();
	
	private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
	
	private XmlBeanDefinitionReader beanDefinitionReader;
	
	public XmlBeanFactory(String location)throws Exception {
		beanDefinitionReader = new XmlBeanDefinitionReader();
	}
	
	//获取bean
	@Override
	public Object getBean(String name) throws Exception {
		//先从map中找是否存在name的beanDefinition对象
		BeanDefinition beanDefinition = beanDefinitionMap.get(name);
		if(beanDefinition == null){
			throw new IllegalArgumentException();
		}
		//如果找到,就获取bean
		Object bean = beanDefinition.getBean();
		//如果bean为空，则需要根据beanDefinition信息实例出bean
		if(bean == null){
			//实例出bean
			bean = createBean(beanDefinition);
			//初始化bean
			bean = initializeBean(bean,name);
			//将bean设置到beanDefinition中
			beanDefinition.setBean(bean);
		}
		
		return bean;
	}

	/**
	 * //实例化bean
	 * @param beanDefinition
	 * @return
	 * @throws Exception
	 */
	private Object createBean(BeanDefinition beanDefinition) throws Exception {
		//根据beanDefiniton中的beanClass属性创建出一个空的bean实例
		Object bean = beanDefinition.getBeanClass().newInstance();
		//为这个空的bean实例设置属性
		applyPropertyValues(bean,beanDefinition);
		return bean;
	}
	
	/**
	 * 初始化bean
	 * @param bean
	 * @param name
	 * @return
	 * @throws Exception 
	 */
	private Object initializeBean(Object bean, String name) throws Exception {
		for(BeanPostProcessor beanPostProcessor : beanPostProcessors){
			bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
		}
		for(BeanPostProcessor beanPostProcessor : beanPostProcessors){
			bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
		}
		return bean;
	}


	/**
	 * 为bean设置属性
	 * @param bean
	 * @param beanDefinition
	 * @throws Exception
	 */
	private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception {

		//如果这个bean实现了BeanFactoryAware接口，则为其设置beanFactory
		if(bean instanceof BeanFactoryAware){
			((BeanFactoryAware) bean).setBeanFactory(this);
		}
		//遍历beanDefinition对象中的propertyValues
		for(PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()){
			//获取property的value值
			Object value = propertyValue.getValue();
			//如果value值是一个ref，也就是BeanReference对象
			if(value instanceof BeanReference){
				BeanReference beanReference = (BeanReference) value;
				//则先获取ref中的bean
				value = getBean(beanReference.getName());
			}
			
			try {
				//为bean设置属性值
				Method declaredMethod = bean.getClass().getDeclaredMethod("set" + propertyValue.getName().substring(0, 1).toUpperCase() 
						+ propertyValue.getName().substring(1),value.getClass());
				declaredMethod.setAccessible(true);
				
				declaredMethod.invoke(bean, value);
			} catch (Exception e) {
				Field declareField = bean.getClass().getDeclaredField(propertyValue.getName());
				declareField.setAccessible(true);
				declareField.set(bean, value);
				e.printStackTrace();
			}
		}
	}
	
	private void loadBeanDefinitions(String location)throws Exception{
		beanDefinitionReader.loadBeanDefinitions(location);
		registerBeanDefinition();
		registerBeanPostProcessor();
	}

	private void registerBeanDefinition() {
		for(Map.Entry<String, BeanDefinition> entry : beanDefinitionReader.getRegistry().entrySet()){
			String name = entry.getKey();
			BeanDefinition beanDefinition = entry.getValue();
			beanDefinitionMap.put(name, beanDefinition);
			beanDefinitionNames.add(name);
		}
	}
	
	public void registerBeanPostProcessor() throws Exception {
		List beans = getBeansForType(BeanPostProcessor.class);
		for(Object bean : beans){
			addBeanPostProcessor((BeanPostProcessor)bean);
		}
	}

	public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
		beanPostProcessors.add(beanPostProcessor);
		
	}

	public List getBeansForType(Class type) throws Exception {
		List beans = new ArrayList<>();
		for(String beanDefinitionName : beanDefinitionNames){
			if(type.isAssignableFrom(beanDefinitionMap.get(beanDefinitionName).getBeanClass())){
				beans.add(getBean(beanDefinitionName));
			}
		}
		return beans;
	}


}
