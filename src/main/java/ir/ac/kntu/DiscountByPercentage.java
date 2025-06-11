package ir.ac.kntu;

import ir.ac.kntu.util.SafeInput;
import java.util.Scanner;

public class DiscountByPercentage extends Discount {
    private double percentage;

    @Override
    public void showDiscountDetails(Scanner scanner) {
        while (true) {
            System.out.println("--- Discount by percentage ----");
            System.out.println(this.getSummary());
            System.out.println(" percentage : " + percentage);
            System.out.println("--- 0.back ---");
            int selection = SafeInput.getInt(scanner);
            if (selection == 0) {
                return;
            } else {
                System.out.println("Invalid selection.");
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
        StringBuilder sb = new StringBuilder();
        sb.append("DiscountWithValue{");
        sb.append("percentage :").append(percentage).append("%");
        sb.append("| code : ").append(super.getCode());
        sb.append("| usage limit : ").append(super.getusageLimit());
        sb.append('}');
        return sb.toString();
    }
}
