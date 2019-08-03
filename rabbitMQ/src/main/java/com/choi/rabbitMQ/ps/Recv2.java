package com.choi.rabbitMQ.ps;

import com.choi.rabbitMQ.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author ChoiBin
 * @Date 2019-07-31 12:15
 * @Version 1.0
 */
public class Recv2 {

    private static final String QUEUE_NAME = "test_queue_fanout_sms";
    private static final String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //绑定队列到交换机转换器上
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,"");
        //保证每次只分发一个
        channel.basicQos(1);
        //定义一个消费者
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("recv2 msg :" + msg);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    System.out.println("recv2 done");
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        //开启自动应答
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
