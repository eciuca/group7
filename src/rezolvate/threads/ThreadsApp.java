package rezolvate.threads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadsApp {
    public static Lock lock = new ReentrantLock();
    public static final Integer QUEUE_SIZE = 20;
    
    public static void main(String[] args)
    {
        Queue<String> q = new LinkedList<>();


        Producator p1 = new Producator(q,"P1");
        Producator p2 = new Producator(q,"P2");
        Producator p3 = new Producator(q,"P3");

        Thread p1t = new Thread(p1);
        p1t.start();
        Thread p2t = new Thread(p2);
        p2t.start();
        Thread p3t = new Thread(p3);
        p3t.start();

        Consumator c1 = new Consumator(q,"C1");
        Consumator c2 = new Consumator(q,"C2");
        Thread c1t = new Thread(c1);
        c1t.start();
        Thread c2t = new Thread(c2);
        c2t.start();

    }

}
