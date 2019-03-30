package live.bank;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankingApp {

    private static List<Account> accounts = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        incarcaConturi();

        afiseazaMeniu();

        Scanner scanner = new Scanner(System.in);

        int option = scanner.nextInt();

        while (option != 0) {
            switch (option) {
                case 1:
                    creeazaCont();
                    break;
                case 2:
                    afiseazaConturi();
                    break;
                case 3:
                    creeazaBackupConturi();
                    break;
                default:
                    System.out.println("Please choose one of the displayed options");
            }
            afiseazaMeniu();
            option = scanner.nextInt();
        }

        System.out.println("Bye!");
    }

    private static void creeazaBackupConturi() throws IOException {
        // folosim un input stream pentru a citi fisierul pe care il vom copia
        FileInputStream fis = new FileInputStream("accounts.txt");

        // am creat timestampul pentru numele fisierului de backup
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String dateString = now.format(formatter);
        String backupFilename = "accounts-" + dateString + ".txt";

        //folosim numele de fisier pentru
        // a crea un nou fisier folosind numele creat anterior
        FileOutputStream fos = new FileOutputStream(backupFilename);

        //initializam variabila in care citim fiecare byte din fisier folosind
        // FileInputStream
        int data = fis.read();

        // citim fiecare byte pana cand ajungem la sfarsitul fisierului
        // (in acel moment metoda read() din FileInputStream va returna -1
        do {
            fos.write(data);
            //citim urmatorul byte din sursa
            data = fis.read();
        } while (data != -1);

        //inchidem cele doua streamuri pentru a elibera resursele sistemului
        fis.close();
        fos.close();

        System.out.println("Am facut backup in fisierul " + backupFilename);
    }

    private static void incarcaConturi() throws IOException {
//        FileInputStream fis = null;
//        Il vom folosi intr-un exemplu cu copiere de fisere unde putem citi byte cu byte
        BufferedReader reader = null; //definim
        try {
            //Cream legatura intre Java si fisier
//            fis = new FileInputStream("accounts.txt");
            reader = new BufferedReader(new FileReader("accounts.txt")); //atribuim

            while (reader.ready()) {
                //citim o linie din fisier
                String accountsLine = reader.readLine();
                System.out.println(accountsLine);

                //impartim linia pe coloane delimitate de virgula
                String[] accountInfo = accountsLine.split(",");

                //extragem campurile necesare din array
                String owner = accountInfo[0];
                BigDecimal balance = new BigDecimal(accountInfo[1]);

                //cream o instante de account
                Account account = new Account(owner, balance);

                //adaugam instanta in lista de conturi folosita de aplicatie
                accounts.add(account);
            }

        } catch (FileNotFoundException e) {
            System.out.println("I can't find the file :( Exiting...");
        } catch (IOException e) {
            System.out.println("I cannot read to the file");
        } finally {
//            fis.close();
            reader.close();
        }
    }

    private static void afiseazaMeniu() {
        System.out.println("1. Add new account");
        System.out.println("2. List accounts");
        System.out.println("3. Creeaza back-up lista conturi");
        System.out.println("0. Exit\n");
        System.out.print("Please choose an option: ");
    }

    private static void creeazaCont() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Specify the owner name: ");
        String owner = scanner.nextLine();

        System.out.print("Specify how much money he wants to deposit: ");
        BigDecimal balance = scanner.nextBigDecimal();

        Account account = new Account(owner, balance);
        accounts.add(account);

        scrieContInFisier(account);
    }

    private static void scrieContInFisier(Account account) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("accounts.txt", true);

            String accountString = account.getOwner() + "," + account.getBalance() + "\n";
            System.out.println(accountString);

            fos.write(accountString.getBytes());

        } catch (FileNotFoundException e) {
            System.out.println("I can't find the file :( Exiting...");
        } catch (IOException e) {
            System.out.println("I cannot write to the file");
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                System.out.println("I couldnt close the stream! :(");
            }
        }
    }

    private static void afiseazaConturi() {
        System.out.println("The accounts list is: ");
        for (Account account : accounts) {
            System.out.println(account);
        }

        if (accounts.isEmpty()) {
            System.out.println("empty");
        }
    }
}
