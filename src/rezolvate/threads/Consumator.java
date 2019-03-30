package rezolvate.threads;

import java.util.Queue;

public class Consumator implements Runnable {
    private Queue<String> q;
    private String name;

    public Consumator(Queue<String> q, String name) {
        this.q = q;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            String obj;
            log(" (C) Inainte de syncronized");
            ThreadsApp.lock.lock();
            if (q.isEmpty()) {
                log(" (C) q is empty");
                try {
                    ThreadsApp.lock.unlock();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            log(" (C) Inainte de poll");
            obj = q.poll();
            log(" (C) Dupa poll " + q.size());
            ThreadsApp.lock.unlock();
            log(" Am consumat: " + obj);
            //TODO some work with obj
        }
    }

    private void log(String s) {
        System.out.println(name + s);
    }
}
