package com.choi.mystarter;

/**
 * @author : choibin
 * @date : 16:34  2019/11/15 0015
 */
public class HelloService {

    private String msg;
    private String name;
    public String sayHello(){
        return name + " say" + msg + "!";
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
