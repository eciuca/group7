package live.threads;

public class MyThread extends Thread {

    @Override
    public void run() {
        try {
            System.out.println("Salut din thread: " + Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
