package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Wallet {
    private double accountBalance;
    private List<Transaction> transactions = new ArrayList<>();

    public void walletMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("--------- Wallet Menu ---------");
            System.out.println("Account Balance: " + accountBalance);
            System.out.println("--------- 1. Deposit ----------");
            System.out.println("--------- 2. Withdraw ---------");
            System.out.println("------ 3. Transactions --------");
            System.out.println("---------- 0. back ------------");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    System.out.println("Enter the amount you want to deposit.");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    deposit(amount);
                }
                case 2 -> {
                    System.out.println("Enter the amount you want to withdraw.");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    withdraw(amount);
                }
                // case 3 ->
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    public void transactionMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("------ Transaction Menu -----");

            if (transactions.isEmpty()) {
                System.out.println("No transactions found.");
            }

            int number = 1;
            for (Transaction transaction : transactions) {
                System.out.println(number + ". " + transaction.trascationSummary());
                number++;
            }
            System.out.println((transactions.size() + 2) + ". Filter by date range.");
            System.out.println("---------- 0. back ----------");
            System.out.println("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                return;
            } else if (choice == transactions.size() + 2) {
                // filter
            } else if (choice > 0 && choice <= transactions.size()) {
                transactions.get(choice - 1).toString();
            } else {
                System.out.println("Invalid choice!");
            }

        }
    }

    public Wallet() {
        this.accountBalance = 0;
    }

    public Wallet(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void deposit(double amount) {
        accountBalance += amount;
        System.out.println("The deposit was successful.");
    }

    public void withdraw(double amount) {
        if (accountBalance >= amount) {
            accountBalance -= amount;
            System.out.println("Paid " + amount + " from your wallet.\nRemaining amount :" + accountBalance);
        } else {
            System.out.println("Insufficient wallet balance!");
        }
    }

    public void showBalance() {
        System.out.println("Wallet balance: " + accountBalance + " toman");
    }

    public boolean hasEnoughBalance(double amount) {
        return accountBalance >= amount;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

}
