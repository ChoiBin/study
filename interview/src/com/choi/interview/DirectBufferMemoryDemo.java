package com.choi.interview;

import java.nio.ByteBuffer;

public class DirectBufferMemoryDemo {

    /**
     * 配置参数:
     * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
     *
     * 故障现象：
     * Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
     *
     * 导致原因：
     * 写NIO层序经常使用ByteBuffer来读取或者写入数据，这是一种基于通道和缓冲区的I/O方式
     * 它可以使用Native函数库直接分配堆外内存，然后通过一个存储在Java堆里面的DirectByteBuffer对象来作为这块内存的引用进行操作
     * 这样能在一些场景中显著提高性能，避免了在Java堆与native堆中来回复制数据
     *
     * ByteBuffer.allocate(capability) 第一种方式是分配jvm堆内存，数据GC管辖范围，由于需要拷贝所以速度相对比较慢
     * ByteBuffer.allocateDirect(capability) 这种方式是分配OS本地内存，不属于GC管辖范围，由于不需要内存拷贝所以速度相对比较快
     *
     * 但是如果不断分配本地内存，堆内存使用很少，那么JVM就不需要执行GC，DirectBuffer对象就不会被回收
     * 这时候堆内存充足，但是本地内存可能已经使用光了，再次尝试分配本地内存就会出现OutOfMemoryError，那么程序就会崩溃
     *
     */

    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory：" + (sun.misc.VM.maxDirectMemory() / (double)1024 / 1024) + "MB");

        try{
            Thread.sleep(3000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
