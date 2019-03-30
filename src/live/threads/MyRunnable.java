package live.threads;

public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Salut din thread: " + Thread.currentThread().getName());
    }
}
