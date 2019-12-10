package com.choi.mybatis;

import com.choi.mybatis.bean.Dept;
import com.choi.mybatis.mapper.DeptMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisApplicationTests {

    @Autowired
    DeptMapper deptMapper;

    @Test
    void contextLoads() {
        List<Dept> depts = deptMapper.getAllDept();
        System.out.println(depts);
    }
}
