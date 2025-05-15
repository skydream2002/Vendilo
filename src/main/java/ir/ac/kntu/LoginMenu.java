package ir.ac.kntu;

import java.util.Scanner;

public class LoginMenu {
    private Scanner scanner = new Scanner(System.in);

    public void startMenu() {
        while (true) {
            System.out.println("-------Main Menu-------");
            System.out.println("-------1.Login---------");
            System.out.println("-------2.Sign up-------");
            System.out.println("--------3.Exit---------");
            int choice = scanner.nextInt();
            // complete this
            switch (choice) {
                case 1 -> login();
                case 2 -> System.out.println("Sign up");
                case 3 -> {
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
        
        for (User user : UserRepository.getAllUsers()) {
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                System.out.println("Login succesfully");
                user.usersMenu();
                return;
            }
        }
        System.out.println("User not found!");
    }
}
