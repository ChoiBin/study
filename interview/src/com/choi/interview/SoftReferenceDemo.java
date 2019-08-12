package com.choi.interview;

import java.lang.ref.SoftReference;

/**
 * 软引用适用场景：
 *  假如有个应用需要读取大量的本地图片，如果每次读取图片都要从硬盘中读取则会严重影响性能，
 *  如果一次性全部加载到内存中又可能会造成内存溢出
 *  此时使用软引用就可以解决这个问题，设计思路是：用一个HashMap来保存图片的路径和相应图片对象关联的软引用
 *  之间的映射关系，在内存不足时，JVM就会自动回收这些缓存图片对象所占用的空间，从而有效地避免了OOM的问题。
 *  Map<String,SoftReference<Bitmap>> imageCache = new HashMap<String,SoftReference<Bitmap>>();
 */
public class SoftReferenceDemo {
    /*
    * 内存够用的时候就保留，不够用就回收
    * */
    public static void softRef_Memory_Enough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();
        System.out.println(o1);
        System.out.println(softReference.get());
    }

    /**
     * JVM配置，故意产生大对象并配置小的内存，让它内存不够导致OOM
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRef_Memory_NotEnough(){
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;

        try{
            byte[] bytes = new byte[30*1024*1024];
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }

    public static void main(String[] args){
        //softRef_Memory_Enough();
        softRef_Memory_NotEnough();
    }
}
