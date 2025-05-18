package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer extends User {
    private List<Address> addresses = new ArrayList<>();
    private ShoppingCart shoppingCart;
    private List<Order> orders = new ArrayList<>();
    private List<SupportRequest> supportRequests = new ArrayList<>();

    @Override
    public void usersMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----Customer Menu----");
            System.out.println("---1.Shopping Cart---");
            System.out.println("-2.Searching Products");
            System.out.println("-----3.Addresses-----");
            System.out.println("------4.Wallet-------");
            System.out.println("------5.Orders-------");
            System.out.println("------6.Setting------");
            System.out.println("--------7.back-------");
            System.out.println("choose:");
            int choice = scanner.nextInt();
            // complete this
            switch (choice) {
                case 1 -> {
                    if (shoppingCart == null) {
                        shoppingCart = new ShoppingCart();
                    }
                    shoppingCart.shoppingCartMenu();
                }
                case 2 -> System.out.println("search");
                case 3 -> System.out.println("address");
                case 4 -> this.getWallet().walletMenu();
                case 5 -> {
                    OrderService.orderMenu(this, orders);
                }
                case 6 -> System.out.println("settings");
                case 7 -> {
                    return;
                }
                default -> System.out.println("invalid choice.");

            }
        }
    }

    public void addToShoppingCart() {

    }

    public void addAddress(Address address) {
        addresses.add(address);
    }

    public Customer(String email, String firstName, String lastName, String password, String phoneNumber) {
        super(email, firstName, lastName, password, phoneNumber);
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<SupportRequest> getSupportRequests() {
        return supportRequests;
    }

    public void setSupportRequests(List<SupportRequest> supportRequests) {
        this.supportRequests = supportRequests;
    }
}
