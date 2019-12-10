package com.choi.mybatis2.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author : choibin
 * @date : 17:16  2019/12/3 0003
 */
@Configuration
@MapperScan(basePackages = "com.choi.mybatis2.mapper2",
sqlSessionFactoryRef = "sqlSessionFactory2",sqlSessionTemplateRef = "sqlSessionTemplate2")
public class MyBatisConfigTwo {

    @Resource(name = "dsTwo")
    DataSource dataSource;

    @Bean
    SqlSessionFactory sqlSessionFactory2(){
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        try {
            bean.setDataSource(dataSource);
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    SqlSessionTemplate sqlSessionTemplate2(){
        return new SqlSessionTemplate(sqlSessionFactory2());
    }
}
