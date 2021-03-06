package com.choi.rabbitMQ.fairwork;

import com.choi.rabbitMQ.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author ChoiBin
 * @Date 2019-07-30 20:00
 * @Version 1.0
 */
public class Recv2 {

    private static final String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //保证一次只分发一个
        channel.basicQos(1);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("recv2 msg:" + msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("recv2 done");
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };

        channel.basicConsume(QUEUE_NAME,false,consumer);
    }

}
