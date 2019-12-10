package com.choi.aop.service;

import org.springframework.stereotype.Service;

/**
 * @author : choibin
 * @date : 13:38  2019/11/26 0026
 */
@Service
public class UserService {

    public String getUsernameById(Integer id){
        int i = 1 / 0;
        System.out.println("getUsernameByID");
        return "choibin";
    }

    public void deleteUserById(Integer id){
        System.out.println("deleteUserById");
    }

}
