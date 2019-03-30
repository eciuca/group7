package rezolvate.threads;

import java.util.Queue;

public class Producator implements Runnable {
    private static Integer counter = 0;

    public Integer nextCounterValue() {
        synchronized (counter) {
            return counter++;
        }
    }

    private Queue<String> q;
    String name;

    public Producator(Queue<String> q, String name) {
        this.q = q;
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 200; i++) {
            String obiect = new String("Obiect numarul " + nextCounterValue());
            log(" Am creat: " + obiect);

            ThreadsApp.lock.lock();

            while (q.size() >= ThreadsApp.QUEUE_SIZE) {
                ThreadsApp.lock.unlock();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                ThreadsApp.lock.lock();
            }

            log(" (P)Inainte de offer");
            q.offer(obiect);
            log(" (P)Dupa offer " + q.size());

            ThreadsApp.lock.unlock();
            try {
                log(" (P)Inainte de sleep");
                Thread.sleep(10);
                log(" (P)Dupa sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void log(String s) {
//        System.out.println(name + s);
    }
}
