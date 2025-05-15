package ir.ac.kntu;

import java.util.Scanner;

public class Login {
    private Scanner scanner = new Scanner(System.in);

    public void startMenu() {
        while (true) {
            System.out.println("-------Main Menu-------");
            System.out.println("--1.Login as customer--");
            System.out.println("---2.Login as seller---");
            System.out.println("--3.Login as supporter-");
            System.out.println("-------4.Sign up-------");
            System.out.println("--------5.Exit---------");
            int choice = scanner.nextInt();
            // complete this
            switch (choice) {
                case 1 -> login("customer");
                case 2 -> login("seller");
                case 3 -> login("support");
                case 4 -> System.out.println("Sign up");
                case 5 -> {
                    scanner.close();
                    return;
                }
                default -> System.out.println("invalid choice");
            }
        }
    }

    private void login(String role) {
        System.out.println("Enter email :");
        String email = scanner.nextLine();
        
        System.out.println("Enter password :");
        String password = scanner.nextLine();

        switch (role) {
            case "customer":
                for (Customer customer : UserRepository.getCustomers()) {
                    if (email.equals(customer.getEmail()) && password.equals(customer.getPassword())) {
                        System.out.println("Login succesfully");
                        // customer menu
                    }
                    System.out.println("Invalid customer credentials.");
                    break;
                }
            case "seller":
                for (Seller seller : UserRepository.getSellers()) {
                    if (email.equals(seller.getEmail()) && password.equals(seller.getPassword())) {
                        System.out.println("Login succesfully");
                        // seller menu
                    }
                    System.out.println("Invalid seller credentials.");
                    break;
                }
            case "support":
                for (Support support : UserRepository.getSupports()) {
                    if (email.equals(support.getEmail()) && password.equals(support.getPassword())) {
                        System.out.println("Login succesfully");
                        // support menu
                    }
                    System.out.println("Invalid support credentials.");
                    break;
                }
            default : 
                System.out.println("Invalid role.");
        }
    }

}
