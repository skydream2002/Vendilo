package ir.ac.kntu;

import java.util.Random;
import java.util.Scanner;

public class SellerSignUpRequest {
    private String storeName;
    private int nationalCode;
    private String province;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private String agentCode;
    private String reasonRejection;

    @Override
    public String toString() {
        return "Store name : " + storeName
                + " seller's email : " + email
                + " seller's name : " + firstName + " " + lastName
                + "\n" + "phone number : " + phoneNumber
                + " province : " + province;
    }

    public SellerSignUpRequest(String email, String firstName, String lastName, int nationalCode, String password,
            String phoneNumber, String province, String storeName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.province = province;
        this.storeName = storeName;
        reasonRejection = null;
    }

    public void editInfoAndResubmit(Scanner scanner) {
        System.out.println("Edit Store Name: ");
        setStoreName(scanner.nextLine());

        System.out.println("Edit National Code: ");
        setNationalCode(Integer.parseInt(scanner.nextLine()));

        System.out.println("Edit Province: ");
        setProvince(scanner.nextLine());

        setReasonRejection(null);
    }

    public String generateAgentCode() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            code.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return code.toString();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(int nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getReasonRejection() {
        return reasonRejection;
    }

    public void setReasonRejection(String reasonRejection) {
        this.reasonRejection = reasonRejection;
    }
}
