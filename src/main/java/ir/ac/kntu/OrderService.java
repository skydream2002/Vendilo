package ir.ac.kntu;

import java.util.List;
import java.util.Scanner;

public class OrderService {

    public static void orderMenu(User user, List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("==== Order Menu ====");
            int number = 1;
            for (Order order : orders) {
                System.out.println(number + ". " + order.getOrderDate() + " Total Price :" + order.calculateTotalPrice());
                number++;
            }
            System.out.println("0. back");
            System.out.print("Choose an order to see details or back: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                return;
            } else if (choice > 0 && choice <= orders.size()) {
                showOrderDetail(user, orders.get(choice - 1));
            } else {
                System.out.println("invalid choice!");
            }
        }
    }

    public static void showOrderDetail(User user, Order order) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-------- Order Details ---------");
            System.out.println("-------- Products List ---------");

            showProductsList(order.getProducts());
            System.out.println("Address: " + order.getShippingAddress().toString());
            System.out.println("Date: " + order.getOrderDate().toString());
            System.out.println("Total Price: " + order.calculateTotalPrice());
            System.out.println("---- 0. back ----");
            System.out.println("---- 1. rating ----");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    if (user instanceof Customer customer) {
                        rateProducts(customer, order);
                    } else {
                        System.out.println("You are not a customer who can rate.");
                    }
                }
            }
        }
    }

    private static void rateProducts(Customer customer, Order order) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            showProductsList(order.getProducts());
            System.out.println("Select the product to rate.");
            System.out.println("0. back");
            int selection = scanner.nextInt();
            scanner.nextLine();

            if (selection == 0) {
                return;
            } else if (selection > 0 && selection <= order.getProducts().size()) {
                System.out.println("Enter your rate (1 to 5):");

                double rate = scanner.nextDouble();
                scanner.nextLine();

                if (rate >= 1 && rate <= 5) {
                    order.getProducts().get(selection - 1).addRatings(customer.getEmail(), rate);
                } else {
                    System.out.println("Please Enter rate in range :");
                    rate = scanner.nextDouble();
                    scanner.nextLine();
                }
            } else {
                System.out.println("invalid selection");
            }
        }
    }

    private static void showProductsList(List<Product> products) {
        int number = 1;
        for (Product product : products) {
            System.out.println(number + ". " + product.getName()
                    + " | Category: " + product.getType()
                    + " | Price: " + product.getPrice()
                    + " | Seller: " + product.getSeller().getLastName()
                    + " | Avg Rating: " + product.getAverageRating());
            number++;
        }
    }
}
