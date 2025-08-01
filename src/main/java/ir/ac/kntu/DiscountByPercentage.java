package ir.ac.kntu;

import ir.ac.kntu.util.SafeInput;
import java.util.Scanner;

public class DiscountByPercentage extends Discount {
    private double percentage;

    @Override
    public void showDiscountDetails(Customer customer, Scanner scanner) {
        while (true) {
            System.out.println("===== Discount By percentage Details =====");
            System.out.println(this.getSummary());
            System.out.println(" percentage : %" + percentage);
            System.out.println("--- 1. Apply this discount ---");
            System.out.println("--- 0.back ---");
            int selection = SafeInput.getInt(scanner);

            switch (selection) {
                case 1 -> {
                    customer.setSelectedDiscount(this);
                    return;
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid selection.");
            }
        }
    }

    public DiscountByPercentage(double percentage, String code, int usageLimit) {
        super(code, usageLimit);
        this.percentage = percentage;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        StringBuilder discount = new StringBuilder();
        discount.append("DiscountWithValue{");
        discount.append("percentage :").append(percentage).append("%");
        discount.append("| code : ").append(super.getCode());
        discount.append("| usage limit : ").append(super.getUsageLimit());
        discount.append('}');
        return discount.toString();
    }
}
