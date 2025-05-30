package ir.ac.kntu;

import java.util.List;
import java.util.Scanner;

public class OrderService {

    public static void orderMenu(User user, List<Order> orders, Scanner scanner) {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        PaginationHelper<Order> pagination = new PaginationHelper<>() {
            @Override
            public String formatItem(Order order) {
                return order.getOrderDate() + " | Total Price: " + order.calculateTotalPrice();
            }
        };
        pagination.paginate(orders, scanner, (order, sc) -> {
            showOrderDetail(user, order, scanner);
        });
    }

    public static void showOrderDetail(User user, Order order, Scanner scanner) {
        while (true) {
            System.out.println("-------- Order Details ---------");
            System.out.println("-------- Products List ---------");

            showProductsList(order.getProducts());
            System.out.println("Address: " + order.getShippingAddress().toString());
            System.out.println("Date: " + order.getOrderDate().toString());
            System.out.println("Total Price: " + order.calculateTotalPrice());
            if (!(user instanceof Customer)) {
                System.out.println("Customer's email : " + order.getCustomer().getEmail());
            }
            System.out.println("---- 0. back ----");
            if (user instanceof Customer) {
                System.out.println("---- 1. rating ----");
            }
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    if (user instanceof Customer customer) {
                        rateProducts(customer, order, scanner);
                    } else {
                        System.out.println("You are not a customer who can rate.");
                    }
                }
                default -> System.out.println("Invalid chioce.");
            }
        }
    }

    private static void rateProducts(Customer customer, Order order, Scanner scanner) {
        PaginationHelper<Product> pagination = new PaginationHelper<>() {
            @Override
            public String formatItem(Product product) {
                return product.getName()
                        + " | Category: " + product.getType()
                        + " | Price: " + product.getPrice()
                        + " | Seller: " + product.getSeller().getLastName()
                        + " | Avg Rating: " + product.getAverageRating();
            }
        };

        pagination.paginate(order.getProducts(), scanner, (product, sc) -> {
            double rate = -1;
            while (rate < 1 || rate > 5) {
                System.out.println("Enter your rate (1 to 5):");
                rate = sc.nextDouble();
                sc.nextLine();

                if (rate < 1 || rate > 5) {
                    System.out.println("Invalid input. Rate must be between 1 and 5.");
                }
            }

            product.addRatings(customer.getEmail(), rate);
            System.out.println("Rating submitted.");
        });
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
