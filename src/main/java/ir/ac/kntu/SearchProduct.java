package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchProduct {

    public void showSearchMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("----- Product Search Menu -----");
            System.out.println("1. Search by Name");
            System.out.println("2. Search by Category");
            System.out.println("3. Search by Name and Price Range");
            System.out.println("4. Search by Category and Price Range");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                // case 1 -> searchByName(scanner);
                // case 2 -> searchByCategory(scanner);
                // case 3 -> searchByNameAndPrice(scanner);
                // case 4 -> searchByCategoryAndPrice(scanner);
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public void searching(Scanner scanner)  {
        System.out.println("Enter product name keyword (or press Enter to skip):");
        String name = scanner.nextLine().trim();

        System.out.println("Enter category (or press Enter to skip):");
        String category = scanner.nextLine().trim();

        System.out.println("Enter min price (or press Enter to skip):");
        String min = scanner.nextLine().trim();
        Double minPrice = min.isEmpty() ? null : Double.parseDouble(min);

        System.out.println("Enter max price (or press Enter to skip):");
        String max = scanner.nextLine().trim();
        Double maxPrice = max.isEmpty() ? null : Double.parseDouble(max);

        List<Product> filteredProducts = filterProducts(name, category, minPrice, maxPrice);
        String sortType = selectSortType(scanner);
        sortProducts(filteredProducts, sortType);

    }

    public static List<Product> getAllProducts() {
        List<Product> all = new ArrayList<>();
        for (Seller seller : UserRepository.getSellers()) {
            all.addAll(seller.getProducts());
        }
        return all;
    }

    private List<Product> filterProducts(String productName, String category, Double minPrice, Double maxPrice) {
        return getAllProducts().stream()
                .filter(product -> productName.isEmpty()|| product.getName().toLowerCase().contains(productName.toLowerCase()))
                .filter(product -> category.isEmpty() || product.getType().name().equalsIgnoreCase(category))
                .filter(product -> minPrice == null || product.getPrice() >= minPrice)
                .filter(product -> maxPrice == null || product.getPrice() <= maxPrice)
                .toList();
    }

    private String selectSortType(Scanner scanner) {
        System.out.println("Sort by price:");
        System.out.println("1. Ascending");
        System.out.println("2. Descending");
        System.out.println("3. No sorting");
        System.out.println("Choose :");
        String selection = scanner.nextLine().trim();
        return selection;
    }

    private void sortProducts(List<Product> products, String sortType) {
        switch (sortType) {
            case "1" -> products.stream().sorted(java.util.Comparator.comparingDouble(Product::getPrice));
            case "2" -> products.stream().sorted(java.util.Comparator.comparingDouble(Product::getPrice).reversed());
            default -> {}
        }
    }
}
