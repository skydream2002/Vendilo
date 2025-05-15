package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserRepository {
    private static List<Customer> customers = new ArrayList<>();
    private static List<Seller> sellers = new ArrayList<>();
    private static List<Support> supports = new ArrayList<>();

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
        
        System.out.println("Enter email :");
        String email = scanner.nextLine();

        System.out.println("Enter password :");
        String password = scanner.nextLine();

        System.out.println("Enter phone numbor :");
        String phoneNumbor = scanner.nextLine();

        for (User user : getAllUsers()) {
            if(email.equals(user.getEmail())) {
                System.out.println("This email is already registered.");
                return;
            }
        }

        if(role.equals("customer")) {
            Customer customer = new Customer(email, firstName, lastName, password, phoneNumbor);
            customers.add(customer);
        } else if (role.equals("seller")) {
            Seller seller = new Seller(email, firstName, lastName, password, phoneNumbor);
            sellers.add(seller);
        } else {
            System.out.println("invalid role");
        }
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
