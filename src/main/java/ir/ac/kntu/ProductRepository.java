package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    public static List<Product> getAllProducts() {
    List<Product> all = new ArrayList<>();
    for (Seller seller : UserRepository.getSellers()) {
        all.addAll(seller.getProducts());
    }
    return all;
}
}
