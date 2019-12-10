package com.choi.mybatis2;

import com.choi.mybatis2.bean.Dept;
import com.choi.mybatis2.mapper1.DeptMapper1;
import com.choi.mybatis2.mapper2.DeptMapper2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Mybatis2ApplicationTests {

    @Autowired
    DeptMapper1 deptMapper1;
    @Autowired
    DeptMapper2 deptMapper2;
    @Test
    public void contextLoads() {
        List<Dept> allUsers = deptMapper1.getAllDept();
        System.out.println(allUsers);
        List<Dept> allUsers1 = deptMapper2.getAllDept();
        System.out.println(allUsers1);
    }

}
