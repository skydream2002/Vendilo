package ir.ac.kntu;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public abstract class Product {
    private String name;
    private double price;
    private ProductType type;
    private Seller seller;
    private List<Double> ratings = new ArrayList<>(); 

    public abstract void showDetails();

    public String getAverageRating() {
        if (ratings == null || ratings.isEmpty()) {
            return "-";
        }
        OptionalDouble averageRates = ratings.stream().mapToDouble(Double::doubleValue).average();
        return averageRates.isPresent() ? (String.format("%.2f",averageRates.getAsDouble())) : "-";
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

    public List<Double> getRatings() {
        return ratings;
    }

    public void setRatings(List<Double> ratings) {
        this.ratings = ratings;
    }
}
