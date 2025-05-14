package ir.ac.kntu;
import java.util.ArrayList;
import java.util.List;

public abstract class Product {
    private String name;
    private double price;
    private ProductType type;
    private Seller seller;
    private List<Double> ratings = new ArrayList<>(); 
    
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
}
