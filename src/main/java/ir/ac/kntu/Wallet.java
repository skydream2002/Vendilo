package ir.ac.kntu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Wallet {
    private double accountBalance;
    private List<Transaction> transactions = new ArrayList<>();
    private LocalDateTime filterStartDate = null;
    private LocalDateTime filterEndDate = null;

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
                    System.out.println("Enter the describtion for transaction");
                    String describtion = scanner.nextLine();
                    deposit(amount, describtion);
                }
                case 2 -> {
                    System.out.println("Enter the amount you want to withdraw.");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Enter the description for transaction");
                    String description = scanner.nextLine();
                    withdraw(amount, description);
                }
                case 3 -> transactionMenu();
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    public void transactionMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("------ Transaction Menu -----");

            List<Transaction> filteredTransactions = getFilteredTransactions();

            if (filteredTransactions.isEmpty()) {
                System.out.println("No transactions found.");
            } else {

                if (filterStartDate != null && filterEndDate != null) {
                    System.out.println("Filter active: " + filterStartDate.toLocalDate()
                            + " to " + filterEndDate.toLocalDate());
                } else {
                    System.out.println("Filter not active.");
                }

                int number = 1;
                for (Transaction transaction : filteredTransactions) {
                    System.out.println(number + ". " + transaction.trascationSummary());
                    number++;
                }
            }
            int baseOption = filteredTransactions.size();
            System.out.println((baseOption + 1) + ". Filter by date range.");
            System.out.println((baseOption + 2) + ". Clear filter.");
            System.out.println("---------- 0. back ----------");
            System.out.println("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                return;
            } else if (choice == baseOption + 1) {
                setFilterRange(scanner);
                System.out.println("Filter applied successfully.");
            } else if (choice == baseOption + 2) {
                clearFilter();
            } else if (choice > 0 && choice <= baseOption) {
                transactionDetails(scanner, filteredTransactions.get(choice - 1));
            } else {
                System.out.println("Invalid choice!");
            }

        }
    }

    public void transactionDetails(Scanner scanner , Transaction transaction) {
        while (true) { 
            System.out.println("----- Transaction Details -----");
            System.out.println(transaction);
            System.out.println("----------- 0. back -----------");
            System.out.println("Enter your selection: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void setFilterRange(Scanner scanner) {
        System.out.println("Enter start date (yyyy-mm-dd):");
        String startInput = scanner.nextLine();
        System.out.println("Enter end date (yyyy-mm-dd):");
        String endInput = scanner.nextLine();

        try {
            filterStartDate = LocalDateTime.parse(startInput + "T00:00:00");
            filterEndDate = LocalDateTime.parse(endInput + "T23:59:59");
        } catch (Exception e) {
            System.out.println("Invalid date format.");
            filterStartDate = null;
            filterEndDate = null;
        }
    }

    private void clearFilter() {
        filterStartDate = null;
        filterEndDate = null;
        System.out.println("Filter cleared.");
    }

    private List<Transaction> getFilteredTransactions() {
        if (filterStartDate == null || filterEndDate == null) {
            return transactions;
        }

        List<Transaction> result = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (!transaction.getDate().isBefore(filterStartDate) && !transaction.getDate().isAfter(filterEndDate)) {
                result.add(transaction);
            }
        }
        return result;
    }

    public Wallet() {
        this.accountBalance = 0;
    }

    public Wallet(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void deposit(double amount, String description) {
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        accountBalance += amount;
        transactions.add(new Transaction(amount, "Deposit", LocalDateTime.now(), description));
        System.out.println("The deposit was successful.");
    }

    public void withdraw(double amount, String describtion) {
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        if (accountBalance >= amount) {
            accountBalance -= amount;
            transactions.add(new Transaction(-amount, "Withdraw", LocalDateTime.now(), describtion));
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
