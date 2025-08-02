package ir.ac.kntu;

import ir.ac.kntu.util.SafeInput;
import java.util.Scanner;

public class DiscountByValue extends Discount {
    private double value;

    @Override
    public void showDiscountDetails(Customer customer, Scanner scanner) {
        while (true) {
            System.out.println("===== Discount By Value Details =====");
            System.out.println(this.getSummary());
            System.out.printf("Value: %.2f\n", value);
            System.out.printf("Minimum Order Price to apply: %.2f\n", value * 10);
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

    public DiscountByValue(double value, String code, int usageLimit) {
        super(code, usageLimit);
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder discount = new StringBuilder();
        discount.append("DiscountWithValue{");
        discount.append("value :").append(value);
        discount.append("| code : ").append(super.getCode());
        discount.append("| usage limit : ").append(super.getUsageLimit());
        discount.append('}');
        return discount.toString();
    }

}