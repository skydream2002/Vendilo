package ir.ac.kntu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Wallet {
    private double accountBalance;
    private final List<Transaction> transactions = new ArrayList<>();
    private LocalDateTime filterStartDate = null;
    private LocalDateTime filterEndDate = null;
    private UserType userType;

    public Wallet(UserType userType) {
        this.userType = userType;
    }

    public void walletMenu(Scanner scanner) {

        while (true) {
            System.out.println("--------- Wallet Menu ---------");
            System.out.println("-- Account Balance: " + accountBalance + " --");

            int optionNumber = 1;

            if (userType == UserType.CUSTOMER) {
                System.out.println("--------- " + optionNumber + ". Deposit ----------");
                optionNumber++;
            }
            if (userType == UserType.SELLER) {
                System.out.println("--------- " + optionNumber + ". Withdraw ---------");
                optionNumber++;
            }
            System.out.println("------ " + optionNumber + ". Transactions --------");
            optionNumber++;
            System.out.println("---------- 0. back ------------");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                return;
            }
            if (userType == UserType.CUSTOMER && choice == 1) {
                System.out.println("Enter the amount you want to deposit.");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Enter the description for transaction");
                String describtion = scanner.nextLine();
                deposit(amount, describtion);
            } else if (userType == UserType.SELLER && choice == 1) {
                System.out.println("Enter the amount you want to withdraw.");
                double amount = scanner.nextDouble();
                scanner.nextLine();
                System.out.println("Enter the description for transaction");
                String description = scanner.nextLine();
                withdraw(amount, description);
            } else if ((userType == UserType.CUSTOMER && choice == 2) ||
                    (userType == UserType.SELLER && choice == 2)) {
                transactionMenu(scanner);
            } else {
                System.out.println("Invalid choice!");
            }

        }
    }

    public void transactionMenu(Scanner scanner) {
        while (true) {
            System.out.println("------ Transaction Menu -----");
            System.out.println("1. Show transactions");
            System.out.println("F. Filter by date range.");
            System.out.println("C. Clear filter.");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "0" -> {
                    return;
                }
                case "1" -> filtering(scanner);
                case "f" -> {
                    setFilterRange(scanner);
                    System.out.println("Filter applied successfully.");
                }
                case "c" -> {
                    clearFilter();
                    System.out.println("Filter cleared.");
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void filtering(Scanner scanner) {
        List<Transaction> filtered = getFilteredTransactions();
        if (filtered.isEmpty()) {
            System.out.println("No transactions found for the current filter.");
        } else {

            if (filterStartDate != null && filterEndDate != null) {
                System.out.println("Filter active: " + filterStartDate.toLocalDate()
                        + " to " + filterEndDate.toLocalDate());
            } else {
                System.out.println("No date filter applied.");
            }

            PaginationHelper<Transaction> pagination = new PaginationHelper<>() {
                @Override
                public String formatItem(Transaction transaction) {
                    return transaction.trascationSummary();
                }
            };
            pagination.paginate(filtered, scanner, (transaction, sc) -> {
                transactionDetails(sc, transaction);
            });
        }
    }

    public void transactionDetails(Scanner scanner, Transaction transaction) {
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
    }

    public void withdraw(double amount, String description) {
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        if (accountBalance >= amount) {
            accountBalance -= amount;
            transactions.add(new Transaction(-amount, "Withdraw", LocalDateTime.now(), description));
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
