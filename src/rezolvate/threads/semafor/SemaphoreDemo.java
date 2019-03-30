package rezolvate.threads.semafor;

import java.util.concurrent.Semaphore;

// Driver class
public class SemaphoreDemo {
    public static void main(String args[]) throws InterruptedException {
        // creating a Semaphore object
        // with number of permits 1
        Semaphore sem = new Semaphore(2);

        // creating two threads with name A and B
        // Note that thread A will increment the count
        // and thread B will decrement the count
        MyThread mt1 = new AddThread(sem, "A");
        MyThread mt2 = new SubtractThread(sem, "B");
        MyThread mt3 = new AddThread(sem, "C");
        MyThread mt4 = new AddThread(sem, "D");

        // stating threads A and B
        mt1.start();
        mt2.start();
        mt3.start();
        mt4.start();

        // waiting for threads A and B
        mt1.join();
        mt2.join();
        mt3.join();
        mt4.join();


        // count will always remain 0 after
        // both threads will complete their execution
        System.out.println("count: " + Shared.count);
    }
}