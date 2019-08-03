package com.choi.rabbitMQ.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author ChoiBin
 * @Date 2019-07-30 19:20
 * @Version 1.0
 */
public class ConnectionUtils {

    /**
     * 获取MQ连接
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        //定义一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置一个服务地址
        factory.setHost("192.168.25.133");
        //设置连接端口号
        factory.setPort(5672);
        //设置连接哪一个
        factory.setVirtualHost("/vhost_mmr");
        //用户名
        factory.setUsername("choibin");
        //密码
        factory.setPassword("6680124");
        return factory.newConnection();
    }



}
