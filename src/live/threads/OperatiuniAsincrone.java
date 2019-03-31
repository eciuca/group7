package live.threads;

import java.util.Random;
import java.util.concurrent.*;

public class OperatiuniAsincrone {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // verifica in mod asincron care este starea vremii (este executat de un thread separat)
        Callable<String> intreabaCumEVremea = new Callable<String>() {

            @Override
            public String call() throws Exception {
                // intreaba un server
                Random random = new Random();
                int probabilitate = random.nextInt(10);
                if (probabilitate > 5) {
                    return "Vremea va fi frumoasa";
                } else {
                    return "Vremea va fi urata";
                }
            }
        };

        // verifica in mod asincron care este disponibilitatea pentru a putea lua concediu
        Callable<String> intreabaSeful = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                // intreaba un server
                Random random = new Random();
                int probabilitate = random.nextInt(10);
                if (probabilitate > 5) {
                    return "Poti sa iti iei concediu";
                } else {
                    return "NU poti sa iti iei concediu";
                }
            }
        };

        FutureTask<String> stareaVremii = new FutureTask<String>(intreabaCumEVremea);
        FutureTask<String> verficaDisponiblitate = new FutureTask<String>(intreabaSeful);

        // cream threaduri care sa ne execute task-urile
//        new Thread(stareaVremii).start();
//        new Thread(verficaDisponiblitate).start();
//
        // cream un set de threaduri care sa ne execute task-urile
        ExecutorService executor = Executors.newFixedThreadPool(2);
        // ii dam executorului primul task ca parametru
        executor.submit(stareaVremii); // echivalent cu new Thread(stareaVremii).start();
        // ii dam executorului al doilea task ca parametru
        executor.submit(verficaDisponiblitate); // new Thread(verficaDisponiblitate).start();

        String rezultat = stareaVremii.get();
        System.out.println(rezultat);

//        if (rezultat.equalsIgnoreCase("Vremea va fi frumoasa")) {
        System.out.println("Suspans....");
        String rezultatDisponibilitate = verficaDisponiblitate.get();
        System.out.println(rezultatDisponibilitate);

        // inchidem executor service pentru a putea finaliza executia programului
        executor.shutdown();
//        }
    }
}
