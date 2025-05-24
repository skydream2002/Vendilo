package ir.ac.kntu;

import java.util.Random;

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
}
