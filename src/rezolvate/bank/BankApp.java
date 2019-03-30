package rezolvate.bank;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankApp {

    enum OptiuneMeniu {
        IESIRE, CONT_NOU, CAUTA_CONT, LISTEAZA_CONTURI
    }

    private static List<ContBancar> conturi = new ArrayList<>();

    public static void main(String[] args) {

//        incarcaConturi();

        OptiuneMeniu optiuneMeniu;

        do {
            optiuneMeniu = afiseazaSiCitesteOptiuneMeniu();

            switch (optiuneMeniu) {
                case CONT_NOU:
                    creeazaCont();
                    break;
                case CAUTA_CONT:
                    cautaCont();
                    break;
                case LISTEAZA_CONTURI:
                    listeazaConturi();
                    break;
                case IESIRE:
                    System.out.println("Bye!");
            }

        } while (optiuneMeniu != OptiuneMeniu.IESIRE);
    }

    private static void incarcaConturi() {
        try {
            List<String> liniiConturi = Files.readAllLines(Paths.get("bank/accounts.txt"));
            for (String linieCont : liniiConturi) {
                String[] arrayCont = linieCont.split(",");

                String titular = arrayCont[0];
                BigDecimal balanta = new BigDecimal(arrayCont[1]);
                ContBancar contBancar = new ContBancar(titular, balanta);

                conturi.add(contBancar);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listeazaConturi() {
        for (ContBancar cont : conturi) {
            System.out.println(cont);
        }
    }

    private static void cautaCont() {
        Scanner scanner = new Scanner(System.in);
        String owner = scanner.nextLine();

        for (ContBancar cont : conturi) {
            if (cont.getTitular().equalsIgnoreCase(owner)) {
                System.out.println(cont);
            }
        }
    }

    private static void creeazaCont() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduceti titularul contului: ");
        String owner = scanner.nextLine();

        System.out.print("Introduceti suma: ");
        BigDecimal suma = scanner.nextBigDecimal();

        ContBancar account = new ContBancar(owner, suma);
        conturi.add(account);

        //region SCRIE_IN_FISIER
//        try (FileOutputStream fos = new FileOutputStream("bank/accounts.txt", true)) {
//
//            String accountAsString = account.getTitular() + "," + account.getBalanta() + "\n3";
//            fos.write(accountAsString.getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //endregion SCRIE_IN_FISIER
    }

    private static OptiuneMeniu afiseazaSiCitesteOptiuneMeniu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------------------------------");
        System.out.println("1. Cont nou");
        System.out.println("2. Cauta cont dupa nume");
        System.out.println("3. Listeaza conturi");
        System.out.println("0. Iesire");
        System.out.print("\nCe operatiune doriti sa efectuati ? ");

        int indexOptiune = scanner.nextInt();
        System.out.println();
        return OptiuneMeniu.values()[indexOptiune];
    }
}
