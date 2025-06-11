package ir.ac.kntu;

import ir.ac.kntu.util.SafeInput;
import main.java.ir.ac.kntu.Discount;

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
        StringBuilder sb = new StringBuilder();
        sb.append("DiscountWithValue{");
        sb.append("value :").append(value);
        sb.append("| code : ").append(super.getCode());
        sb.append("| usage limit : ").append(super.getusageLimit());
        sb.append('}');
        return sb.toString();
    }

}