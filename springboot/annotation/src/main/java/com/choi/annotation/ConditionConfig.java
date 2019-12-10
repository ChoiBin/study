package com.choi.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author : choibin
 * @date : 23:45  2019/9/21 0021
 */
@Configuration
@Conditional(MyCondition.class)
public class ConditionConfig {

    @Bean
    public ConditionBean conditionBean(){
        return new ConditionBean();
    }

}
