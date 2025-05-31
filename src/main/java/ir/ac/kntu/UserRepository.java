package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserRepository {
    private static final List<Customer> customers = new ArrayList<>();
    private static final List<Seller> sellers = new ArrayList<>();
    private static final List<Supporter> supports = new ArrayList<>();
    private static final List<SellerSignUpRequest> pendingRequests = new ArrayList<>();

    public static void signUp(String role, Scanner scanner) {
        if (isInvalidRole(role)) {
            return;
        }

        UserInput userInput = collectUserInput(scanner);

        if ("customer".equals(role)) {
            createCustomer(userInput);
        } else if ("seller".equals(role)) {
            createSellerRequest(scanner, userInput);
        }
    }

    private static boolean isInvalidRole(String role) {
        if ("support".equals(role)) {
            System.out.println("You can't sign up as a supporter");
            return true;
        }
        return false;
    }

    private static UserInput collectUserInput(Scanner scanner) {
        UserInput input = new UserInput();

        System.out.println("-----Sign up Menu-----");
        input.setFirstName(getInput(scanner, "Enter your first name:"));
        input.setLastName(getInput(scanner, "Enter your last name:"));
        input.setEmail(getValidEmail(scanner));
        input.setPassword(getValidPassword(scanner));
        input.setPhoneNumber(getValidPhoneNumber(scanner));

        return input;
    }

    private static String getInput(Scanner scanner, String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    private static String getValidEmail(Scanner scanner) {
        String email = getInput(scanner, "Enter your email:");
        while (!isValidEmail(email) || emailExists(email)) {
            email = getInput(scanner, "Invalid or duplicate email. Please enter a valid and unique email:");
        }
        return email;
    }

    private static String getValidPassword(Scanner scanner) {
        String password = getInput(scanner, "Enter password:");
        while (!isStrongPassword(password)) {
            System.out.println("Password must be at least 8 characters long and contain...");
            password = getInput(scanner, "Please enter a valid password:");
        }
        return password;
    }

    private static String getValidPhoneNumber(Scanner scanner) {
        String phone = getInput(scanner, "Enter phone number:");
        while (!isValidPhoneNumber(phone) || phoneExists(phone)) {
            phone = getInput(scanner, "Invalid or duplicate phone number. Please enter a valid one:");
        }
        return phone;
    }

    private static void createCustomer(UserInput input) {
        Customer customer = new Customer(
                input.getEmail(),
                input.getFirstName(),
                input.getLastName(),
                input.getPassword(),
                input.getPhoneNumber());
        customers.add(customer);
    }

    private static void createSellerRequest(Scanner scanner, UserInput input) {
        System.out.println("Enter your nationalCode:");
        int nationalCode = scanner.nextInt();
        scanner.nextLine();

        String province = getInput(scanner, "Enter your province:");
        String storeName = getInput(scanner, "Enter your Store title:");

        SellerSignUpRequest request = new SellerSignUpRequest(
                input.getEmail(),
                input.getFirstName(),
                input.getLastName(),
                nationalCode,
                input.getPassword(),
                input.getPhoneNumber(),
                province,
                storeName);

        pendingRequests.add(request);
        System.out.println("Your sign-up request has been submitted for review.");
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

    public static SellerSignUpRequest getRequestByEmail(String email) {
        for (SellerSignUpRequest request : pendingRequests) {
            if (request.getEmail().equals(email)) {
                return request;
            }
        }
        return null;
    }

    public static List<SellerSignUpRequest> getPendingSellerRequests() {
        return pendingRequests;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addSeller(Seller seller) {
        sellers.add(seller);
    }

    public static void addSupport(Supporter support) {
        supports.add(support);
    }

    public static List<Customer> getCustomers() {
        return customers;
    }

    public static List<Seller> getSellers() {
        return sellers;
    }

    public static List<Supporter> getSupports() {
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

class UserInput {
    private String firstName;
    private String lastName;
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    private String password;
    private String phoneNumber;
}
