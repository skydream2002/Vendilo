package ir.ac.kntu;

import java.time.LocalDateTime;

public class Transaction {
    private LocalDateTime date;
    private double amount;
    private String type; // Deposit or Withdraw
    private String description;

    public Transaction(double amount, String type, LocalDateTime date, String description) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.type = type;
    }

    public Transaction() {
    }

    public Transaction(double amount, String type, LocalDateTime date) {
        this.amount = amount;
        this.date = date;
        this.type = type;
    }

    public String trascationSummary() {
        return "Type: " + type + "|"
                + " Amount: " + amount
                + " Date: " + date.toString();
    }

    @Override
    public String toString() {
        return "Type: " + type + "|"
                + " Amount: " + amount + "|"
                + " Date: " + date.toString()
                + "|" + " Description: " + description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
