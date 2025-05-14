package ir.ac.kntu;
import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private double accountBalance;
    private List<Transaction> transactions = new ArrayList<>();

    public void deposit(double amount) {
        accountBalance += amount;
    }

    public void withdraw(double amount) {
        accountBalance -= amount;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    
}
