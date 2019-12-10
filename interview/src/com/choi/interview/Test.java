package com.choi.interview;

/**
 * @author : choibin
 * @date : 20:46  2019/9/21 0021
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

        MyRunable myRunable = new MyRunable();

        for(int i = 0;i < 1000;i++){
            Thread thread = new Thread(myRunable);
            thread.start();
        }

        Thread.sleep(2000);

        System.out.println(myRunable.i);

    }
}
 class MyRunable implements  Runnable{

     int i = 0;

    @Override
    public void run() {
        for(int j = 0;j < 10;j++){
            i++;
        }
    }
}
