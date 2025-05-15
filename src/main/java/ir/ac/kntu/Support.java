package ir.ac.kntu;

import java.util.Scanner;

import main.java.ir.ac.kntu.User;

public class Support extends User {
    private String userName;

    @Override
    public void usersMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("--------Support Menu--------");
            System.out.println("--1.Authentication Request--");
            System.out.println("----2.Follow-up Requests----");
            System.out.println("----------3.Orders----------");
            System.out.println("-----------4.back-----------");
            System.out.println("choose :");
            int choice = scanner.nextInt();
            // complete this
            switch (choice) {
                case 1 -> System.out.println("Authentication Request");
                case 2 -> System.out.println("Follow-up Requests");
                case 3 -> System.out.println("-3.Orders-");
                case 4 -> {
                    scanner.close();
                    return;
                }
                default -> System.out.println("invalid choice");
            }
        }
    }

    public Support(String email, String firstName, String lastName, String password, String phoneNumber) {
        super(email, firstName, lastName, password, phoneNumber);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}