package com.choi.interview;

import java.util.Random;

/**
 *
 * 1  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseSerialGC
 * 2  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParNewGC
 * 3  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelGC
 * 4  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseParallelOldGC
 * 5  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC
 * 6  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:+UseG1GC
 *
 *  组合选择
 *      单CPU或者小内存，单机程序：-XX:+UseSerialGC
 *      多CPU，需要最大的吞吐量，如后台计算型应用:-XX:+UseParallelGC或者-XX:+UseParallelOldGC
 *      多CPU，追求低停顿时间，需要快速响应，如互联网应用：-XX:+UseConcMarkSweepGC  -XX:+ParnewGC
 *
 *      参数              新生代垃圾收集器            新生代算法               老年代垃圾收集器                老年代算法
 *  -XX:+UseSerialGC        SerialGC                    复制                    SerialOldGC                   标记整理
 *  -XX:+UseParNewGC        ParNew                      复制                    SerialOldGC                   标记整理
 *  -XX:+UseParallelGC/     Parallel                    复制                    Parallel Old                  标记整理
 *  -XX:+UseParallelOldGc
 *  -XX:+UseConcMarkSweepGC     ParNew                  复制                  CMS+Serial Old的收集器组合
 *                                                                         （Serial Old作为CMS出错的后背收集器）    标记清除
 *  -XX:+UseG1GC            G1整体上采用标记整理算法，局部是通过复制算法
 *                                  不会产生内存碎片
 *
 */

public class GCDemo {

    public static void main(String[] args) {

        System.out.println("****GCDemo hello");

        try{
            String str = "choibin";
            while(true){
                str += str + new Random().nextInt(333333) + new Random().nextInt(32131231);
                str.intern();
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
    }
}
