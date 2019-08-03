package com.choi.spring.myAOP;

import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;

import com.choi.spring.IOC.myIOC.BeanPostProcessor;
import com.choi.spring.IOC.myIOC.factory.BeanFactory;
import com.choi.spring.IOC.myIOC.factory.BeanFactoryAware;
import com.choi.spring.IOC.myIOC.xml.XmlBeanFactory;

public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {

	private XmlBeanFactory xmlBeanFactory;
	

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) {
		// TODO Auto-generated method stub
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
		
		if(bean instanceof AspectJExpressionPointcutAdvisor){
			return bean;
		}
		//MethodInterceptor接口被用来拦截指定的方法，对方法进行增强
		if(bean instanceof MethodInterceptor){
			return bean;
		}
		
		try {
			List<AspectJExpressionPointcutAdvisor> advisors = xmlBeanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);
			for(AspectJExpressionPointcutAdvisor advisor : advisors){
				if(advisor.getPointcut().getClassFilter().matchers(bean.getClass())){
					
					ProxyFactory advisedSupport = new ProxyFactory();
					//设置MethodInterceptor拦截器，用于拦截指定方法
					advisedSupport.setMethodInterceptor((MethodInterceptor)advisor.getAdvice());
					//设置MethodMatcher，用于匹配指定方法
					advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
					//创建TargetSource，存储将要被代理的类、类的字节码信息、类的接口，用于动态生成代理类
					TargetSource targetSource = new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces());
					//设置TargetSource
					advisedSupport.setTargetSource(targetSource);
					//创建代理类
					return advisedSupport.getProxy();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}
	
	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws Exception{
		xmlBeanFactory = (XmlBeanFactory) beanFactory;
	}

}
