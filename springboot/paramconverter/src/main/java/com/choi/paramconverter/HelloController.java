package com.choi.paramconverter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author ChoiBin
 * @Date 2019-07-30 15:13
 * @Version 1.0
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public void hello(Date date){
        System.out.println(date);
    }
}
