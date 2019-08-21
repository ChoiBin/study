package com.choi.jdbctemplate.service;

import com.choi.jdbctemplate.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer addUser(User user){
        return jdbcTemplate.update("insert into user (username,address) values (?,?);",user.getUsername(),user.getAddress());
    }

}
