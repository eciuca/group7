package rezolvate.threads.communication;

public class SimpleThreadExample {

    static Integer x = 0;
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> incrementeazaCu100());
        Thread t2 = new Thread(() -> incrementeazaCu100());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(x);

    }

    private static void incrementeazaCu100() {
        for (int i = 0; i < 100; i++) {
            sleep();
//            System.out.println(Thread.currentThread().getName());
//            synchronized (x) {
                x = x + 1;
//            }
        }

    }

    private static void decrementeazaCu100() {
        for (int i = 0; i < 100; i++) {
            sleep();
//            System.out.println(Thread.currentThread().getName());
            synchronized (x) {
                x = x + 1;
            }
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}