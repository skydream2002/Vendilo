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
        System.out.println("Enter product name (or press Enter to skip):");
        String name = scanner.nextLine().trim();

        System.out.println("Enter category (or press Enter to skip):");
        String category = scanner.nextLine().trim();

        Double minPrice = null;
        while (true) {
            System.out.println("Enter min price (or press Enter to skip):");
            String min = scanner.nextLine().trim();
            if (min.isEmpty()) {
                break;
            }
            try {
                minPrice = Double.parseDouble(min);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, try again.");
            }
        }

        Double maxPrice = null;
        while (true) {
            System.out.println("Enter max price (or press Enter to skip):");
            String max = scanner.nextLine().trim();
            if (max.isEmpty()) {
                break;
            }
            try {
                maxPrice = Double.parseDouble(max);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, try again.");
            }
        }

        List<Product> filteredProducts = filterProducts(name, category, minPrice, maxPrice);
        System.out.println(filteredProducts.size() + " product(s) found.");
        String sortType = selectSortType(scanner);
        sortProducts(filteredProducts, sortType);

        PaginationHelper<Product> pagination = new PaginationHelper<>();
        pagination.paginate(filteredProducts, scanner, (product, sc) -> {
            showDetailsAndAddToCart(product, customer, scanner);
        });

    }

    public static List<Product> getAllProducts() {
        List<Product> all = new ArrayList<>();
        for (Seller seller : UserRepository.getSellers()) {
            all.addAll(seller.getProducts());
        }
        return all;
    }

    private static List<Product> filterProducts(String productName, String category, Double minPrice, Double maxPrice) {
        return getAllProducts().stream()
                .filter(product -> productName.isEmpty()
                        || product.getName().toLowerCase().contains(productName.toLowerCase()))
                .filter(product -> category.isEmpty() || product.getType().name().equalsIgnoreCase(category))
                .filter(product -> minPrice == null || product.getPrice() >= minPrice)
                .filter(product -> maxPrice == null || product.getPrice() <= maxPrice)
                .toList();
    }

    private static String selectSortType(Scanner scanner) {
        System.out.println("Sort by price:");
        System.out.println("1. Ascending");
        System.out.println("2. Descending");
        System.out.println("3. No sorting");
        System.out.println("Choose :");
        String selection = scanner.nextLine().trim();
        return selection;
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

            if (answer.equals("y")) {
                customer.addToShoppingCart(product);
                System.out.println("Added to your cart.");
                return;
            } else if (answer.equals("n")) {
                return;
            } else {
                System.out.println("Invalid choice. Please type y or n.");
            }
        }
    }
}
