package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserRepository {
    private static final List<Customer> customers = new ArrayList<>();
    private static final List<Seller> sellers = new ArrayList<>();
    private static final List<Support> supports = new ArrayList<>();

    public static void signUp(String role) {
        Scanner scanner = new Scanner(System.in);

        if (role.equals("support")) {
            System.out.println("you can't sign up as a supporter");
            return;
        }

        System.out.println("-----Sign up Menu-----");

        System.out.println("Enter you first name :");
        String firstName = scanner.nextLine();

        System.out.println("Enter you last name :");
        String lastName = scanner.nextLine();

        System.out.println("Enter your email :");
        String email = scanner.nextLine();
        while (!isValidEmail(email) || emailExists(email)) {
            System.out.println("Invalid or duplicate email. Please enter a valid and unique email:");
            email = scanner.nextLine();
        }

        System.out.println("Enter password :");
        String password = scanner.nextLine();
        while (!isStrongPassword(password)) {
            System.out.println(
                    "Password must be at least 8 characters long and contain at least one uppercase letter, \none lowercase letter, \none digit, \nand one special character.");
            password = scanner.nextLine();
        }

        System.out.println("Enter phone number :");
        String phoneNumbor = scanner.nextLine();
        while (!isValidPhoneNumber(phoneNumbor) || phoneExists(phoneNumbor)) {
            System.out.println("Invalid or duplicate phone number. Please enter a valid and unique phone number:");
            phoneNumbor = scanner.nextLine();
        }

        if (role.equals("customer")) {
            Customer customer = new Customer(email, firstName, lastName, password, phoneNumbor);
            customers.add(customer);
        } else if (role.equals("seller")) {
            Seller seller = new Seller(email, firstName, lastName, password, phoneNumbor);
            sellers.add(seller);
        } else {
            System.out.println("invalid role");
        }
    }

    public static boolean isStrongPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$");
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^09\\d{9}$");
    }

    public static boolean emailExists(String email) {
        for (User user : getAllUsers()) {
            if (user.getEmail().equals(email)) {
                System.out.println("This email is already registered.");
                return true;
            }
        }
        return false;
    }

    public static boolean phoneExists(String phone) {
        for (User user : getAllUsers()) {
            if (user.getPhoneNumber().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addSeller(Seller seller) {
        sellers.add(seller);
    }

    public void addSupport(Support support) {
        supports.add(support);
    }

    public static List<Customer> getCustomers() {
        return customers;
    }

    public static List<Seller> getSellers() {
        return sellers;
    }

    public static List<Support> getSupports() {
        return supports;
    }

    public static List<User> getAllUsers() {
        List<User> all = new ArrayList<>();
        all.addAll(customers);
        all.addAll(sellers);
        all.addAll(supports);
        return all;
    }

}
