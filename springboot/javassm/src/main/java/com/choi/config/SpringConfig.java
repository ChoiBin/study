package com.choi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * 该配置文件可以省略，在日常开发中写springmvc配置类即可
 */

/**
 * spring配置类
 * 扫描com.choi包
 * 除了@Controller注解
 */
@Configuration
@ComponentScan(basePackages = "com.choi",useDefaultFilters = true,excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Controller.class)})
public class SpringConfig {

}
