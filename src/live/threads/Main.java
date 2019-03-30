package live.threads;

import java.time.Instant;

public class Main {

    public static void main(String[] args) {
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();

        // async
        System.out.println(Instant.now().toString());
        t1.start();
        t2.start();
        System.out.println(Instant.now().toString());

        // sync
        System.out.println(Instant.now().toString());
        t1.run();
        t2.run();
        System.out.println(Instant.now().toString());
    }
}
