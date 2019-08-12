package com.choi.interview;

/**
 * 查看当前JVM的运行参数
 * java -XX:+PrintComandLineFlags -version
 */
public class HelloGC {
    public static int oneAddone(int x,int y){
        return x+y;
    }
    public static void main(String[] args) throws InterruptedException {
//        int res = oneAddone(1,1);
//        System.out.println(res);
        System.out.println("helloGC");
        Thread.sleep(Integer.MAX_VALUE);
//
//        long totalMemory = Runtime.getRuntime().totalMemory();//返回Java虚拟机中的内存总量
//        long maxMemory = Runtime.getRuntime().maxMemory();//返回Java虚拟机试图使用的最大内存量
//        System.out.println("total_memory(-Xms) = " + totalMemory + "字节、" + (totalMemory / (double)1024 / 1024) + "MB");
//        System.out.println("max_memory(-Xmx) = " + maxMemory + "字节、" + (maxMemory / (double)1024 / 1024) + "MB");

        /**
         * 常用参数：
         * 1、 -Xms 初始大小内存，默认为物理内存的1/64 等价于-XX:InitialHeapSize
         * 2、 -Xmx 最大分配内存，默认为物理内存1/4 等价于-XX:MaxHeapSize
         * 3、 -Xss 设置单个线程栈的大小，一般默认为512k~1024k  等价于-XX:ThreadStackSize
         * 4、 -Xmn 设置年轻代大小
         * 5、-XX:MetaspaceSize 设置元空间大小，元空间的本质和永久代类似，都是对JVM规范中方法区的实现。
         *                      不过元空间与永久代之间最大的区别在于：元空间不在虚拟机中，而是使用本地内存。因此默认情况下，元空间的大小仅受本地内存限制
         * 6、-XX:+PrintGCDetails 输出详细GC收集日志信息
         * 7、-XX：SurvivorRatio  设置新生代中eden和SO/S1空间的比例，默认 -XX：SurvivorRatio=8，Eden:S0:S1 = 8 : 1: 1
         * 8、-XX：NewRatio 配置年轻代和老年代在堆结构的占比，默认 -XX：NewRatio=2新生代占1，老年代占2，年轻代占整个堆得1/3
         * 9、 -XX：MaxTenuringThreshold 设置垃圾最大年龄
         *
         */
    }
}
