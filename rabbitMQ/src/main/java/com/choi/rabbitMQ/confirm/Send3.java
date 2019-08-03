package com.choi.rabbitMQ.confirm;

import com.choi.rabbitMQ.util.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.TimeoutException;

/**
 * @Author ChoiBin
 * @Date 2019-07-31 13:39
 * @Version 1.0
 */

//异步模式
public class Send3 {

    private static final String QUEUE_NAME = "test_queue_confirm3";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //生产者调用confirmSelect，将Channel设置为confirm模式
        channel.confirmSelect();

        SortedSet<Long> confirmSet = Collections.synchronizedSortedSet(new TreeSet<Long>());

        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                if(b){
                    System.out.println("---handleNack-----multiple");
                    confirmSet.headSet(l + 1).clear();
                }else {
                    System.out.println("---handleNack-----multiple false");
                    confirmSet.remove(l);
                }
            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {
                if(b){
                    System.out.println("---handleNack-----multiple");
                    confirmSet.headSet(l + 1).clear();
                }else {
                    System.out.println("---handleNack-----multiple false");
                    confirmSet.remove(l);
                }
            }
        });

        String msg = "hello world";

        while (true){
            long seqNo = channel.getNextPublishSeqNo();
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
            confirmSet.add(seqNo);
        }
    }
}
