public class Demo {

    public static void main(String[] args) {
        System.out.println("main thread-1");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("sub thread:" + Thread.currentThread().getName());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("sub thread:" + Thread.currentThread().getName());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println("main thread");
    }

}
