package live.threads;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Un thread care produce valori intr-o lista
// Un thread care consuma valori din lista
// Cand lista este full threadul producator asteapta
// ca lista sa se elibereze
// Cand lista este goala consumatorul asteapta sa apara
// elemente noi
public class ThreadCommunicationExample {

    static List<Integer> valori = new ArrayList<>();
    static final int CAPACITATE_LISTA = 2;

    public static void main(String[] args) {
        ProducatorConsumator pc = new ProducatorConsumator();

        Thread threadProducator = new Thread(new Runnable() {
            @Override
            public void run() {
                pc.produce();
            }
        });
        Thread threadConsumator = new Thread(new Runnable() {
            @Override
            public void run() {
                pc.consume();
            }
        });

        threadProducator.start();
        threadConsumator.start();
    }

    static class ProducatorConsumator {

        public void produce() {
            while (true) {
                synchronized (this) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // generam o valoare random
                    Random random = new Random();
                    int valoare = random.nextInt();

                    //verificam daca am atins capacicatea listei
                    if (valori.size() >= CAPACITATE_LISTA) {

                        // ii spunem thread-ului curent sa astepte
                        try {
                            System.out.println("Producatorul " + Thread.currentThread().getName() + " asteapta...");
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // adauga valoare in lista
                    valori.add(valoare);
                    System.out.println("Am adaugat nr " + valoare + " in lista");


                    //notifica thread-ul consumator ca poate continua executia
                    notify();
                }
            }
        }
//    }
//
//    static class Consumator implements Runnable {

        public void consume() {
            while (true) {
                synchronized (this) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // verificam daca lista e goala si in acest caz oprim executia thread-ului
                    if (valori.isEmpty()) {
                        // ii spunem thread-ului curent sa astepte
                        try {
                            System.out.println("Consumatorul " + Thread.currentThread().getName() + " asteapta...");
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // consumam o valoare din lista
                    int valoareConsumata = valori.remove(0);
                    System.out.println("Am consumat nr " + valoareConsumata + " din lista");

                    //notifica thread-ul producator ca poate continua executia
                    notify();
                }
            }
        }
    }

}
