package com.choi.mybatis.mapper;

import com.choi.mybatis.entity.DeptEmp;

import java.util.List;

public interface DeptEmpMapper {

    List<DeptEmp> getEmpTotalByDept();

}
