package com.choi.rabbitMQ.topic;

import com.choi.rabbitMQ.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author ChoiBin
 * @Date 2019-07-31 13:19
 * @Version 1.0
 */
public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"topic");

        String msg = "商品...";
        channel.basicPublish(EXCHANGE_NAME,"goods.delete",null,msg.getBytes());
        System.out.println("send msg:" + msg);

        channel.close();
        connection.close();
    }


}
