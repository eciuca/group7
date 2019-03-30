package live.bank;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankingApp {

    private static List<Account> accounts = new ArrayList<>();

    public static void main(String[] args) {

        afiseazaMeniu();

        Scanner scanner = new Scanner(System.in);

        int option = scanner.nextInt();

        while (option != 0) {
            switch (option) {
                case 1:
                    creeazaCont();
                    break;
                case 2:
                    afiseaszaConturi();
                    break;
                case 0:
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Please choose one of the displayed options");
            }
            afiseazaMeniu();
            option = scanner.nextInt();
        }
    }

    private static void afiseazaMeniu() {
        System.out.println("1. Add new account");
        System.out.println("2. List accounts");
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
    }

    private static void afiseaszaConturi() {
        System.out.println("The accounts list is: ");
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}
