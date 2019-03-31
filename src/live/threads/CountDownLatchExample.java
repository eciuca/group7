package live.threads;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Am terminat executia " + Thread.currentThread().getName());
                latch.countDown();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Am terminat executia " + Thread.currentThread().getName());
                latch.countDown();
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Am terminat executia " + Thread.currentThread().getName());
                latch.countDown();
            }
        });

        t1.start();
        t2.start();
        t3.start();

        latch.await();

        System.out.println("Toate threadurile au terminat");
    }
}
