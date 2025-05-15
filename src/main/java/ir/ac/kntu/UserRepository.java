package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static List<Customer> customers = new ArrayList<>();
    private static List<Seller> sellers = new ArrayList<>();
    private static List<Support> supports = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addSeller(Seller seller) {
        sellers.add(seller);
    }

    public void addsellerupport(Support support) {
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
