package com.choi.jdbctemplate.service;

import com.choi.jdbctemplate.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer addUser(User user){
        return jdbcTemplate.update("insert into user (username,address) values (?,?);",user.getUsername(),user.getAddress());
    }

    public Integer updateUsernameByAddress(User user){
        return jdbcTemplate.update("update user set username = ? where address = ?",user.getUsername(),user.getAddress());
    }

    public Integer deleteUserByAddress(Integer id){
        return jdbcTemplate.update("delete from user where address = ?",id);
    }

    public List<User> getAllUsers(){
        return jdbcTemplate.query("select * from user", new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String address = resultSet.getString("address");
                user.setUsername(username);
                user.setAddress(address);
                return user;
            }
        });
    }
}
