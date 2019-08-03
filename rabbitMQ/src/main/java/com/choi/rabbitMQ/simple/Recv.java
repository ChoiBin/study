package com.choi.rabbitMQ.simple;

import com.choi.rabbitMQ.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author ChoiBin
 * @Date 2019-07-30 19:32
 * @Version 1.0
 */

/**
 * 简单队列的不足：
 *  耦合性高，生产者一一对应消费者（如果我想有多个消费者消费队列中的消息，那么久不支持了)
 */
//消费者
public class Recv {

    private static final String QUEUE_NAME = "test_simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        //旧版本的消费方式
//        Connection connection = ConnectionUtils.getConnection();
//
//        Channel channel = connection.createChannel();
//
//        QueueingConsumer consumer = new QueueingConsumer(channel);
//
//        channel.basicConsume(QUEUE_NAME,true,consumer);
//
//        while (true){
//            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//
//            String msgString = new String(delivery.getBody());
//            System.out.println("recv msg:" + msgString);
//        }


        //新版本
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "utf-8");
                System.out.println("recv:" + msg);
            }
        };
        //监听队列
        channel.basicConsume(QUEUE_NAME,true,consumer);
    }

}
