package com.choi.rabbitMQ.confirm;

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

//普通模式
public class Send1 {

    private static final String QUEUE_NAME = "test_queue_confirm1";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //生产者调用confirmSelect，将Channel设置为confirm模式
        channel.confirmSelect();

        String msg = "hello confirm";

        try {
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());

            if(!channel.waitForConfirms()){
                System.out.println("message send failed");
            }else{
                System.out.println("send msg:" + msg);

            }
        } catch (IOException | InterruptedException e) {
        }

        channel.close();
        connection.close();

    }
}
