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
                case 1 -> System.out.println("Login as customer");
                case 2 -> System.out.println("Login as seller");
                case 3 -> System.out.println("Login as supporter");
                case 4 -> System.out.println("Sign up");
                case 5 -> {
                    scanner.close();
                    return;
                }
                default -> System.out.println("invalid choice");
            }
        }
    }

    private void login() {
        System.out.println("Enter email :");
        String email = scanner.nextLine();
        System.out.println("Enter password :");
        String password = scanner.nextLine();
    }

}

