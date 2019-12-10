package com.choi.aop;

import com.choi.aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : choibin
 * @date : 13:38  2019/11/26 0026
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test1")
    public String getUsernameByID(Integer id){
        return userService.getUsernameById(id);
    }

    @GetMapping("/test2")
    public void deleteUserById(Integer id){
        userService.deleteUserById(id);
    }




}
