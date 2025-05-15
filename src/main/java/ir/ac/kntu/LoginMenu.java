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

            switch (choice) {
                case 1 -> login();
                case 2 -> {
                    scanner.nextLine();
                    System.out.println("Enter your role : (customer or seller)");
                    System.out.println("supporter can't sign up");
                    String role = scanner.nextLine().trim().toLowerCase();
                    UserRepository.signUp(role);
                }
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
