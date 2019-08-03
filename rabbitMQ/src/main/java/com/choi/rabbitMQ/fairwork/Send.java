package com.choi.rabbitMQ.fairwork;

import com.choi.rabbitMQ.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author ChoiBin
 * @Date 2019-07-30 19:57
 * @Version 1.0
 */
public class Send {

    private static final String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtils.getConnection();
        Channel channel = connection.createChannel();
        /**
         *第二参数如果为false表示关闭持久化，true开启持久化
         * 如果已经是定义好的队列，那么不能重新定义这个队列，也就是如果之前没开启持久化，那么不能通过修改这个参数，开启持久化，只能在后台删除队列，重新创建这个队列
         */
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //每个消费者发生确认消息之前，消息队列不发生下一个消息到消费者，一次处理一个消息
        //限制发送给同一个消费者不得超过一条消息
        channel.basicQos(1);
        for(int i = 0;i < 50;i++){
            String msg = "hello choibin" + i;
            System.out.println("msg send:" + msg);
            channel.basicPublish("",QUEUE_NAME,null, msg.getBytes());
            Thread.sleep(i * 20);
        }
        channel.close();
        connection.close();
    }
}
