package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

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

        int count = 1;
        for (Product product : products) {
            System.out.println(count + "." + product.getName() + "- Price :" + product.getPrice() + "toman");
            count++;
        }

        double totalPrice = products.stream().mapToDouble(Product::getPrice).sum();
        System.out.println("Total price : " + totalPrice + "toman");
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
