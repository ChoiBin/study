package com.choi.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author : choibin
 * @date : 16:34  2019/11/16 0016
 */
@Configuration
public class JavaConfig {

    @Bean("food")
    @Conditional(RiceCondition.class)
    Food rice(){
        return new Rice();
    }

    @Bean("food")
    @Conditional(NoodlesCondition.class)
    Food noodles(){
        return new Noodles();
    }
}
