package ir.ac.kntu;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Scanner;

public abstract class Product {
    private String name;
    private double price;
    private ProductType type;
    private Seller seller;
    private int stock;
    private Map<String, Double> ratings = new HashMap<>();

    public abstract void showDetails();

    public abstract void addProduct(Scanner scanner, Seller seller);

    public String getAverageRating() {
        if (ratings == null || ratings.isEmpty()) {
            return "-";
        }
        OptionalDouble averageRates = ratings.values().stream().mapToDouble(Double::doubleValue).average();
        return averageRates.isPresent() ? (String.format("%.2f", averageRates.getAsDouble())) : "-";
    }

    public void increaseStock(int count) {
        for (int i = 0; i < count; i++) {
            stock++;
        }
        System.out.println("Product inventory increased by " + count + ".");
    }

    public void decreaseStock(int count) {
        if (count > stock) {
            System.out.println("Cannot decrease stock by more than available amount.");
        }
        for (int i = 0; i < count; i++) {
            stock--;
        }
        System.out.println("Product inventory decreased by " + count + ".");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Map<String, Double> getRatings() {
        return ratings;
    }

    public void addRatings(String email, double rate) {
        if (ratings.containsKey(email)) {
            System.out.println("This customer has already rated");
        } else if (rate >= 1 && rate <= 5) {
            ratings.put(email, rate);
            System.out.println("Thank you! Your rating has been submitted.");
        } else {
            System.out.println("Invalid rating. Must be between 1 and 5.");
        }
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
