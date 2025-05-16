package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private double accountBalance;
    private List<Transaction> transactions = new ArrayList<>();

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

    public boolean withdraw(double amount) {
        if (accountBalance >= amount) {
            accountBalance -= amount;
            System.out.println("Paid " + amount + " from your wallet.\nRemaining amount :" + accountBalance);
            return true;
        } else {
            System.out.println("Insufficient wallet balance!");
            return false;
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
