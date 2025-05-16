package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShoppingCart {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        System.out.println(product.getName() + " added to cart.");
    }

    public void removeProduct(Product product) {
        products.remove(product);
        System.out.println(product.getName() + " removed from cart");
    }

    public void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("shopping cart is empty");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            double totalPrice = products.stream().mapToDouble(Product::getPrice).sum();
            System.out.println("Total price : " + totalPrice + "toman");

            System.out.println("----- Products in Cart -----");
            int count = 1;
            for (Product product : products) {
                System.out.println(count + "." + product.getName() + "-- Price :" + product.getPrice()
                        + "toman" + "--Category : " + product.getType());
                count++;
            }
            System.out.println("0.back");
            System.out.println("Enter product number to see more details");

            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice > 0 && choice <= products.size()) {
                products.get(choice - 1).showDetails();
            } else if (choice == 0) {
                return;
            } else {
                System.out.println("invalid choice");
            }
        }
    }

    public void removeFromCart() {
        if (products.isEmpty()) {
            System.out.println("shopping cart is empty");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("----- Products in Cart -----");
            int count = 1;
            for (Product product : products) {
                System.out.println(count + "." + product.getName() + "-- Price :" + product.getPrice()
                        + "toman" + "--Category : " + product.getType());
                count++;
            }
            System.out.println("---0.back---");
            System.out.println("please enter your choice to remove from shopping cart");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                return;
            } else if (choice > 0 && choice <= products.size()) {
                removeProduct(products.get(choice - 1));
            } else {
                System.out.println("invalid choice");
            }
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public void clearCart() {
        products.clear();
    }
}
