package com.choi.rabbitMQ.ps;

import com.choi.rabbitMQ.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author ChoiBin
 * @Date 2019-07-31 12:10
 * @Version 1.0
 */
public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();
        //声明交换机
        //fanout 表示不处理路由键
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        //发送消息
        String msg = "hello ps";
        channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());
        System.out.println("Send msg:" + msg);

        channel.close();
        connection.close();
    }
}
