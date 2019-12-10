package com.choi.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author : choibin
 * @date : 23:33  2019/9/21 0021
 */
@Import({Circle.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})
@Configuration
public class MainConfig {
}
