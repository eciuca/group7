package live.threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {
        // se va executa cand cele doua thread-uri vor ajunge la linia barrier2.await()
        Runnable barrierAction1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Am ajuns la bariera 1");
            }
        };

        // se va executa cand cele doua thread-uri vor ajunge la linia barrier2.await()
        Runnable barrierAction2 = () -> System.out.println("Am ajuns la bariera 2");

        CyclicBarrier barrier1 = new CyclicBarrier(2, barrierAction1);
        CyclicBarrier barrier2 = new CyclicBarrier(2, barrierAction2);

        Thread t1 = new Thread(new CyclicBarrierRunnable(barrier1, barrier2));
        Thread t2 = new Thread(new CyclicBarrierRunnable(barrier1, barrier2));

        t1.start();
        t2.start();
    }

    static class CyclicBarrierRunnable implements Runnable {

        private CyclicBarrier barrier1;
        private CyclicBarrier barrier2;

        public CyclicBarrierRunnable(CyclicBarrier barrier1, CyclicBarrier barrier2) {
            this.barrier1 = barrier1;
            this.barrier2 = barrier2;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " ruleaza partea 1...");
                // va astepta ca toate threadurile sa ajunga cu executia in acest punct
                barrier1.await();

                System.out.println(Thread.currentThread().getName() + " ruleaza partea a 2-a...");
                // va astepta ca toate threadurile sa ajunga cu executia in acest punct
                barrier2.await();

                System.out.println(Thread.currentThread().getName() + " a terminat!");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
