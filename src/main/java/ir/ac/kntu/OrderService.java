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
            displayOrderInformation(user, order);
            int choice = getMenuChoice(scanner);

            if (!processUserChoice(choice, user, order, scanner)) {
                break;
            }
        }
    }

    private static void displayOrderInformation(User user, Order order) {
        printOrderHeader();
        showProductsList(order.getProducts());
        printOrderDetails(order);
        CustomerEmailIfNeeded(user, order);
        printMenuOptions(user);
    }

    private static void printOrderHeader() {
        System.out.println("-------- Order Details ---------");
        System.out.println("-------- Products List ---------");
    }

    private static void printOrderDetails(Order order) {
        System.out.println("Address: " + order.getShippingAddress());
        System.out.println("Date: " + order.getOrderDate());
        System.out.println("Total Price: " + order.calculateTotalPrice());
    }

    private static void CustomerEmailIfNeeded(User user, Order order) {
        if (!(user instanceof Customer)) {
            System.out.println("Customer's email: " + order.getCustomer().getEmail());
        }
    }

    private static void printMenuOptions(User user) {
        System.out.println("---- 0. back ----");
        if (user instanceof Customer) {
            System.out.println("---- 1. rating ----");
        }
    }

    private static int getMenuChoice(Scanner scanner) {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private static boolean processUserChoice(int choice, User user, Order order, Scanner scanner) {
        switch (choice) {
            case 0:
                return false;
            case 1:
                handleRatingOption(user, order, scanner);
                return true;
            default:
                System.out.println("Invalid choice.");
                return true;
        }
    }

    private static void handleRatingOption(User user, Order order, Scanner scanner) {
        if (user instanceof Customer customer) {
            rateProducts(customer, order, scanner);
        } else {
            System.out.println("You are not a customer who can rate.");
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
