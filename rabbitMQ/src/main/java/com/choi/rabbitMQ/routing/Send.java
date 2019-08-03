package com.choi.rabbitMQ.routing;

import com.choi.rabbitMQ.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author ChoiBin
 * @Date 2019-07-31 12:40
 * @Version 1.0
 */
public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        String msg = " hello direct";

        channel.basicPublish(EXCHANGE_NAME,"error",null,msg.getBytes());

        System.out.println("Send msg:" + msg);

        channel.close();
        connection.close();

    }
}
