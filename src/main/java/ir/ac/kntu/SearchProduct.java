package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchProduct {

    public static void showSearchMenu(Customer customer, Scanner scanner) {
        while (true) {
            System.out.println("----- Product Search Menu -----");
            System.out.println("1. Search");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> searching(scanner, customer);
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public static void searching(Scanner scanner, Customer customer) {
        SearchCriteria criteria = collectSearchCriteria(scanner);
        List<Product> filteredProducts = filterProducts(criteria);

        System.out.println(filteredProducts.size() + " product(s) found.");
        String sortType = selectSortType(scanner);
        sortProducts(filteredProducts, sortType);

        PaginationHelper<Product> pagination = new PaginationHelper<>();
        pagination.paginate(filteredProducts, scanner, (product, sc) -> {
            showDetailsAndAddToCart(product, customer, scanner);
        });

    }

    private static SearchCriteria collectSearchCriteria(Scanner scanner) {
        SearchCriteria criteria = new SearchCriteria();

        criteria.setName(getOptionalInput(scanner, "Enter product name (or press Enter to skip):"));
        criteria.setCategory(getOptionalInput(scanner, "Enter category (or press Enter to skip):"));
        criteria.setMinPrice(getOptionalDouble(scanner, "Enter min price (or press Enter to skip):"));
        criteria.setMaxPrice(getOptionalDouble(scanner, "Enter max price (or press Enter to skip):"));

        return criteria;
    }

    private static String getOptionalInput(Scanner scanner, String prompt) {
        System.out.println(prompt);
        return scanner.nextLine().trim();
    }

    private static Double getOptionalDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                return null;
            }

            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, try again.");
            }
        }
    }

    public static List<Product> getAllProducts() {
        List<Product> all = new ArrayList<>();
        for (Seller seller : UserRepository.getSellers()) {
            all.addAll(seller.getProducts());
        }
        return all;
    }

    private static List<Product> filterProducts(SearchCriteria criteria) {
        return new ArrayList<>(getAllProducts().stream()
                .filter(product -> criteria.getName() == null
                        || product.getName().toLowerCase().contains(criteria.getName().toLowerCase().trim()))
                .filter(product -> criteria.getCategory() == null
                        || product.getType().name().equalsIgnoreCase(criteria.getCategory().trim()))
                .filter(product -> criteria.getMinPrice() == null || product.getPrice() >= criteria.getMinPrice())
                .filter(product -> criteria.getMaxPrice() == null || product.getPrice() <= criteria.getMaxPrice())
                .toList());
    }

    private static String selectSortType(Scanner scanner) {
        while (true) {
            System.out.println("Sort by price:");
            System.out.println("1. Ascending");
            System.out.println("2. Descending");
            System.out.println("3. No sorting");
            System.out.print("Choose: ");
            String input = scanner.nextLine().trim();

            if (input.matches("[1-3]")) {
                return input;
            }
            System.out.println("Invalid choice. Please enter 1, 2 or 3.");
        }
    }

    private static void sortProducts(List<Product> products, String sortType) {
        switch (sortType) {
            case "1" -> products.sort(java.util.Comparator.comparingDouble(Product::getPrice));
            case "2" -> products.sort(java.util.Comparator.comparingDouble(Product::getPrice).reversed());
            default -> {
            }
        }
    }

    public static void showDetailsAndAddToCart(Product product, Customer customer, Scanner scanner) {
        product.showDetails();

        while (true) {
            System.out.println("Do you want to add this product to your cart? (y/n)");
            String answer = scanner.nextLine().trim().toLowerCase();

            if ("y".equals(answer)) {
                customer.addToShoppingCart(product);
                System.out.println("Added to your cart.");
                return;
            } else if ("n".equals(answer)) {
                return;
            } else {
                System.out.println("Invalid choice. Please type y or n.");
            }
        }
    }
}

class SearchCriteria {
    private String name;
    private String category;
    private Double minPrice;
    private Double maxPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

}
