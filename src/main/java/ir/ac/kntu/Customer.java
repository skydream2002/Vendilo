package ir.ac.kntu;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User{
    private List<Address> addresses = new ArrayList<>();
    private ShoppingCart shoppingCart;
    private List<Orders> orders = new ArrayList<>();
    private List<SupportRequest> supportRequests = new ArrayList<>();

    public void addToShoppingCart() {
        
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public List<SupportRequest> getSupportRequests() {
        return supportRequests;
    }

    public void setSupportRequests(List<SupportRequest> supportRequests) {
        this.supportRequests = supportRequests;
    }
}
