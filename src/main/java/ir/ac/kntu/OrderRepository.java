package ir.ac.kntu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class OrderRepository {
    private static final List<Order> orders = new ArrayList<>();

    public static void addOrder(Order order) {
        orders.add(order);
    }

    public static List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public static List<Order> getOrdersByCustomerEmail(String email) {
        return orders.stream()
                .filter(o -> o.getCustomer().getEmail().equalsIgnoreCase(email))
                .toList();
    }

    public static List<Order> getOrdersByDateRange(String start, String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return orders.stream()
                .filter(o -> !o.getOrderDate().toLocalDate().isBefore(startDate) && 
                !o.getOrderDate().toLocalDate().isAfter(endDate))
                .toList();
    }
}

