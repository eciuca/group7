package live.threads;

public class SimpleThreadExample {

    static int x = 0;

    public static void main(String[] args) {
        System.out.println("Salut din thread: " + Thread.currentThread().getName());

//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Salut din thread: " + Thread.currentThread().getName());
//            }
//        });
//
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Salut din thread: " + Thread.currentThread().getName());
//            }
//        });

        Thread t1 = new Thread(new MyRunnable());
        Thread t2 = new Thread(new MyRunnable());

        t1.start(); //async
        t2.start(); //async

//        t1.run(); //sync
//        t2.run(); //sync
    }
}
