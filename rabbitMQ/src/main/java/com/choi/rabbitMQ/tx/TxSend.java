package com.choi.rabbitMQ.tx;

import com.choi.rabbitMQ.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author ChoiBin
 * @Date 2019-07-31 13:39
 * @Version 1.0
 */
public class TxSend {

    private static final String QUEUE_NAME = "test_queue_tx";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        String msg = "hello tx";

        try {
            channel.txSelect();

            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());

            System.out.println("send msg:" + msg);

            int i = 1 / 0;
            channel.txCommit();
        } catch (IOException e) {
            System.out.println("send txRollback");
            channel.txRollback();
        }

        channel.close();
        connection.close();

    }
}
