package com.choi.mystarter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : choibin
 * @date : 16:28  2019/11/15 0015
 */
@ConfigurationProperties(prefix = "choi")
public class HelloProperties {

    private static final String DEFAULT_NAME = "CHOIBIN";
    private static final String DEFAULT_MSG = "hello world";
    private String name = "DEFAULT_NAME";
    private String msg = "DEFAULT_MSG";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
