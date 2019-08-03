package com.choi.xml;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author ChoiBin
 * @Date 2019-07-30 14:22
 * @Version 1.0
 */
@Configuration
@ImportResource(locations = "classpath:beans.xml")
public class WebMvcConfig {
}
