package ir.ac.kntu;

import java.util.Scanner;

public abstract class Discount {
    private String code;
    private int usageLimit;

    public abstract void showDiscountDetails(Scanner scanner);

    public String getSummary() {
        return "Code: " + code + " | usage limit : " + usageLimit;
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

    public int getusageLimit() {
        return usageLimit;
    }

    public void setusageLimit(int usageLimit) {
        this.usageLimit = usageLimit;
    }

}
