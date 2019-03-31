package live.threads;

import java.util.concurrent.Semaphore;

public class ExempluSemafor {

    static Integer suma = 0;

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);

        Thread t1 = new Adunare(semaphore);
        Thread t2 = new Scadere(semaphore);

        t1.start();
        t2.start();

        Thread.sleep(1000);
        System.out.println("Suma este " + suma);
    }

    static abstract class OperatieMatematica extends Thread {

        private Semaphore semaphore;

        public OperatieMatematica(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        abstract void executaOperatie();

        @Override
        public void run() {
            try {
                // cerem semaforului un permis sa lase threadul current sa continue executia
                semaphore.acquire();

                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " executa operatie...");
                    executaOperatie();
                }

                // returnam permisul semaforului pentru a putea fi folosit de alte threaduri
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Adunare extends OperatieMatematica {

        public Adunare(Semaphore semaphore) {
            super(semaphore);
        }

        @Override
        void executaOperatie() {
            suma = suma + 1;
        }
    }

    static class Scadere extends OperatieMatematica {

        public Scadere(Semaphore semaphore) {
            super(semaphore);
        }

        @Override
        void executaOperatie() {
            suma = suma - 1;
        }
    }
}
