package ir.ac.kntu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Customer customer;
    private List<Product> products = new ArrayList<>();
    private Address shippingAddress;
    private LocalDateTime orderDate;
    private double finalCost;

    public Order(List<Product> products, Customer customer, LocalDateTime orderDate, Address shippingAddress, double finalCost) {
        this.customer = customer;
        this.orderDate = orderDate;
        this.shippingAddress = shippingAddress;
        this.products = products;
        this.finalCost = finalCost;
    }

    public Order(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double getFinalCost() {
        return finalCost;
    }

    public void setFinalCost(double finalCost) {
        this.finalCost = finalCost;
    }

    public double calculateTotalPrice() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public Order() {
    }

    public Order(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
