package ir.ac.kntu;

import java.util.Scanner;

public abstract class Discount {
    private String code;
    private int usageLimit;

    public abstract void showDiscountDetails(Customer customer, Scanner scanner);

    public String getSummary() {
        return "Code: " + code + " | Remaining uses : " + usageLimit;
    }

    public Discount(String code, int usageLimit) {
        this.code = code;
        this.usageLimit = usageLimit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(int usageLimit) {
        this.usageLimit = usageLimit;
    }

}
