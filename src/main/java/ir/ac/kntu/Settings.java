package ir.ac.kntu;

import java.util.Scanner;

public class Settings {

    public void settingMenu(Customer customer, Scanner scanner) {
        while (true) {
            System.out.println("----- Setting Menu -----");
            System.out.println("-- 1. Edit first name --");
            System.out.println("--- 2.Edit last name ---");
            System.out.println("----- 3.Edit email -----");
            System.out.println("--- 4. Edit password ---");
            System.out.println("- 5. Edit phone number -");
            System.out.println("-------- 0. back -------");
            System.out.println("Select your choice :");
            int selection = scanner.nextInt();
            scanner.nextLine();
            switch (selection) {
                case 1 -> editFirstName(scanner, customer);
                case 2 -> editLastName(scanner, customer);
                case 3 -> editEmail(scanner, customer);
                case 4 -> editPassword(scanner, customer);
                case 5 -> editPhoneNumber(scanner, customer);
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid selection.");
            }
        }
    }

    public void editFirstName(Scanner scanner, Customer customer) {
        System.out.println("Enter new first name :");
        String firstName = scanner.nextLine();
        customer.setFirstName(firstName);
        System.out.println("First name updated.");
    }

    public void editLastName(Scanner scanner, Customer customer) {
        System.out.println("Enter new last name :");
        String lastName = scanner.nextLine();
        customer.setLastName(lastName);
        System.out.println("Last name updated.");
    }

    public void editEmail(Scanner scanner, Customer customer) {
        while (true) {
            System.out.print("Enter new email: ");
            String email = scanner.nextLine();

            if (!UserRepository.isValidEmail(email)) {
                System.out.println("Invalid email format.");
            } else if (UserRepository.emailExists(email)) {
                System.out.println("Email already exists. Try another.");
            } else {
                customer.setEmail(email);
                System.out.println("Email updated.");
                break;
            }
        }
    }

    public void editPassword(Scanner scanner, Customer customer) {
        while (true) {
            System.out.print("Enter new password: ");
            String password = scanner.nextLine();

            if (!UserRepository.isStrongPassword(password)) {
                System.out.println(
                        "Password must be at least 8 characters long, with upper/lowercase letters, a digit, and a special character.");
            } else {
                customer.setPassword(password);
                System.out.println("Password updated.");
                break;
            }
        }
    }

    public void editPhoneNumber(Scanner scanner, Customer customer) {
        while (true) {
            System.out.println("Enter new phone number:");
            String phoneNumber = scanner.nextLine();

            if (!UserRepository.isValidPhoneNumber(phoneNumber)) {
                System.out.println("Invalid phone number format.");
            } else if (UserRepository.phoneExists(phoneNumber)) {
                System.out.println("Phone number already exists.");
            } else {
                customer.setPhoneNumber(phoneNumber);
                System.out.println("Phone number updated.");
                break;
            }
        }
    }
}
