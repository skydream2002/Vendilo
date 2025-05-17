package ir.ac.kntu;

import java.util.HashMap;
import java.util.Map;
import java.util.OptionalDouble;

public abstract class Product {
    private String name;
    private double price;
    private ProductType type;
    private Seller seller;
    private Map<String, Double> ratings = new HashMap<>();

    public abstract void showDetails();

    public String getAverageRating() {
        if (ratings == null || ratings.isEmpty()) {
            return "-";
        }
        OptionalDouble averageRates = ratings.values().stream().mapToDouble(Double::doubleValue).average();
        return averageRates.isPresent() ? (String.format("%.2f", averageRates.getAsDouble())) : "-";
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
}
