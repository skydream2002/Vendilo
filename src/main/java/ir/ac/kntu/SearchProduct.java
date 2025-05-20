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

    public static List<Product> getAllProducts() {
        List<Product> all = new ArrayList<>();
        for (Seller seller : UserRepository.getSellers()) {
            all.addAll(seller.getProducts());
        }
        return all;
    }

    public void searchByName(Scanner scanner) {
        System.out.println("Enter  product name :");
        String name = scanner.nextLine();

        boolean ascending = askingToSortProducts(scanner);

        List<Product> results = getAllProducts().stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .sorted(ascending ? java.util.Comparator.comparingDouble(Product::getPrice)
                        : java.util.Comparator.comparingDouble(Product::getPrice).reversed())
                .toList();

    }

    private boolean askingToSortProducts(Scanner scanner) {
        System.out.print("Sort by price ascending? (yes/no): ");
        String answer = scanner.nextLine().trim().toLowerCase();
        return answer.equals("yes");
    }
}
