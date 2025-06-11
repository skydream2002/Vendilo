package ir.ac.kntu;

import ir.ac.kntu.util.SafeInput;
import java.util.Scanner;

public class DiscountByValue extends Discount {
    private double value;

    @Override
    public void showDiscountDetails(Scanner scanner) {
        while (true) {
            System.out.println("===== Discount By Value Details =====");
            System.out.println(this.getSummary());
            System.out.printf("Value: %.2f\n", value);
            System.out.printf("Minimum Order Price to apply: %.2f\n", value * 10);
            System.out.println("--- 0.back ---");
            int selection = SafeInput.getInt(scanner);
            if (selection == 0) {
                return;
            } else {
                System.out.println("Invalid selection.");
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