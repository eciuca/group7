package live.threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadsWithSharedData {

    public static final int ITERATIONS = 10;
    static Integer x = 0;
    static Integer y = 0;
    static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new AddingRunnable());
        Thread t2 = new Thread(new AddingRunnable());
        Thread t3 = new Thread(new AddingRunnableWithLock());
        Thread t4 = new Thread(new AddingRunnableWithLock());

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println("Valoarea lui x este: " + x);
        System.out.println("Valoarea lui y este: " + y);
    }

    static class AddingRunnable implements Runnable {

        @Override
        public void run() {
            try {
//                Thread.sleep(100);
                for (int i = 0; i < ITERATIONS; i++) {
                    Thread.sleep(1);
                    //citeste x
                    //incrementeaza x
                    //salveaza x
                    x = x + 1;
                    System.out.println(Thread.currentThread().getName() + " x = " + x);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class AddingRunnableWithLock implements Runnable {

        @Override
        public void run() {
            try {
                for (int i = 0; i < ITERATIONS; i++) {
                    Thread.sleep(1);
                    //citeste x
                    //incrementeaza x
                    //salveaza x

                    // synchronize v
//                    lock.lock();
//                    y = y + 1;
//                    lock.unlock();

                    // synchronize v2
//                    synchronized (y) {
//                        y = y + 1;
//                    }

                    // synchronize v3
                    addOneToY();

                    System.out.println(Thread.currentThread().getName() + " y = " + y);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    synchronized static void addOneToY() {
        y = y + 1;
    }
}
