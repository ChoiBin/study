package com.kaikeba.test;


import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kaikeba.beans.Dept;
import com.kaikeba.dao.DeptMapper;

public class TestMain_01 {
    private SqlSession session;
    @Before
	public void start(){
		try{
			InputStream inputStream = Resources.getResourceAsStream("myBatis-config.xml");
			SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(inputStream);
			session=factory.openSession();
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
    @Test
    public void test01(){
    	DeptMapper dao=session.getMapper(DeptMapper.class);
    	List list=dao.deptFind("SALES");
    }
    
    @Test
    public void test02(){
    	try{
    		DeptMapper dao=session.getMapper(DeptMapper.class);
        	List list=dao.deptFind2("dept2");
        	System.out.println("***************"+list.size());
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    }
    
   
    @After
    public void end(){
    	if(session!=null){
    		session.close();
    	}
    }
    
}
