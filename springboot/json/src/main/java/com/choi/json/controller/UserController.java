package com.choi.json.controller;

import com.choi.json.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author ChoiBin
 * @Date 2019-07-28 14:28
 * @Version 1.0
 */
@RestController
public class UserController {

    @GetMapping("/user")
    public List<User> getUser(){
        List<User> users = new ArrayList<>();

        for(int i = 0;i < 10;i++){
            User user = new User();
            user.setId(i);
            user.setUsername("choibin" + i);
            user.setAddress("abc" + i);
            users.add(user);
            user.setBirthDay(new Date());
        }

        return users;
    }

}
