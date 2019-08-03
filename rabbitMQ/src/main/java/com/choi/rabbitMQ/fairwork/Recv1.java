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
public class Recv1 {

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
                System.out.println("recv1 msg:" + msg);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("recv1 done");
                    //处理完消息，要发送一个ack给MQ
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        //自动应答改为false
        //第二个变量：消息应答
        //值为true时 表示自动确认模式，一旦MQ将消息分发给消费者时，就将消息会从内存中删除
        //      如果正在执行的消费者宕机了，就会丢失正在处理的消息
        //值为false时，表示手动确认模式，当消费者宕机了，就会将内存中的这个消息发给其他消费者
        //      MQ中支持消息应答，消费者消费完一个消息，会告诉MQ，我这个消息消费完了，你可以删了，然后MQ才将内存中的这个消息删除
        //默认情况下，消息应答是打开的，也就是值为false

        //如果是MQ宕机了，我们的消息仍然会丢失
        //rabbitMQ中支持持久化
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }

}
