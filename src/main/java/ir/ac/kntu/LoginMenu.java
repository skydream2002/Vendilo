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
            scanner.nextLine();

            switch (choice) {
                case 1 -> login();
                case 2 -> {
                    scanner.nextLine();
                    System.out.println("Enter your role : (customer or seller)");
                    System.out.println("supporter can't sign up");
                    String role = scanner.nextLine().trim().toLowerCase();
                    UserRepository.signUp(role, scanner);
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

        SellerSignUpRequest request = UserRepository.getRequestByEmail(email);
        if (request != null && request.getPassword().equals(password)) {
            if (request.getReasonRejection() != null) {
                System.out.println("Your registration was rejected.");
                System.out.println("Reason: " + request.getReasonRejection());
                request.editInfoAndResubmit(scanner);
                return;
            } else {
                System.out.println("Your registration is pending approval.");
                return;
            }
        }

        for (User user : UserRepository.getAllUsers()) {
            if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
                System.out.println("Login succesfully");
                user.usersMenu(scanner);
                return;
            }
        }
        System.out.println("User not found!");
    }
}
