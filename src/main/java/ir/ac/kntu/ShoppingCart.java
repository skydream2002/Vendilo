package ir.ac.kntu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ShoppingCart {
    private List<Product> products = new ArrayList<>();
    private Customer customer;

    public ShoppingCart(Customer customer) {
        this.customer = customer;
    }

    public void shoppingCartMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("====== Shopping Cart Menu ====== ");
            System.out.println("---------1.view products---------");
            System.out.println("--------2.Remove a product-------");
            System.out.println("------------3.Checkout-----------");
            System.out.println("-----------4.Clear cart----------");
            System.out.println("-------------5.Back--------------");
            System.out.println("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewProducts();
                case 2 -> removeFromCart();
                case 3 -> checkout(scanner);
                case 4 -> clearCart();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Invalid option!");
            }

        }
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println(product.getName() + " added to cart.");
    }

    public void removeProduct(Product product) {
        products.remove(product);
        System.out.println(product.getName() + " removed from cart");
    }

    public void viewProducts() {
        if (products.isEmpty()) {
            System.out.println("shopping cart is empty");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        double totalPrice = getTotalPrice();
        System.out.println("Total price : " + totalPrice + "toman");

        PaginationHelper<Product> pagination = new PaginationHelper();
        pagination.paginate(products, scanner, (product, sc) -> {
            while (true) {
                product.showDetails();
                System.out.println("----- 1. Back -----");
                System.out.print("Choose: ");
                String choice = sc.nextLine();

                if (choice.equals("1")) {
                    break;
                } else {
                    System.out.println("Invalid choice.");
                }
            }
        });
    }

    public void removeFromCart() {
        if (products.isEmpty()) {
            System.out.println("shopping cart is empty");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("----- Products in Cart -----");
        PaginationHelper<Product> pagination = new PaginationHelper<>();
        pagination.paginate(products, scanner, (product, sc) -> {
            System.out.println("Are you sure you want to remove \"" + product.getName() + "\" from the cart?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.print("Choose: ");
            String input = sc.nextLine();
            if (input.equals("1")) {
                removeProduct(product);
            } else {
                System.out.println("Cancelled.");
            }
        });
    }

    private Address selectAddressWithPagination(Scanner scanner) {
        List<AddressOption> options = new ArrayList<>();

        for (Address address : customer.getAddresses()) {
            options.add(new AddressOption(address));
        }

        options.add(new AddressOption());

        Address[] selected = new Address[1];
        PaginationHelper<AddressOption> pagination = new PaginationHelper<>();
        pagination.paginate(options, scanner, (option, sc) -> {
            if (option.isAddNew()) {
                Address.addingAddress(sc, customer);
            } else {
                selected[0] = option.getAddress();
            }
        });

        return selected[0];
    }

    public void checkout(Scanner scanner) {
        if (products.isEmpty()) {
            System.out.println("Shopping cart is empty.");
            return;
        }
        while (true) {
            System.out.println("---- Checkout Menu ----");
            System.out.println("--- Total Price : " + getTotalPrice());

            Address selectedAddress = selectAddressWithPagination(scanner);
            if (selectedAddress == null) {
                return;
            }

            confirmPayment(selectedAddress, scanner);
            return;
        }
    }

    public void confirmPayment(Address selectedAddress, Scanner scanner) {
        if (!checkStockAvailability()) {
            return;
        }

        double shippingCost = handlePriceWithAddress(selectedAddress);
        double totalCost = getTotalPrice() + shippingCost;

        System.out.println("Shipping cost: " + shippingCost + " toman");
        System.out.println("Total cost (with shipping): " + totalCost + " toman");
        System.out.println("Your wallet balance: " + customer.getWallet().getAccountBalance() + " toman");
        System.out.println("Do you want to confirm payment? (y/n)");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (confirm.equals("y")) {
            if (customer.getWallet().getAccountBalance() < totalCost) {
                System.out.println("Insufficient wallet balance.");
                return;
            }

            Map<Seller, List<Product>> sellerProductsMap = new HashMap<>();
            for (Product product : products) {
                sellerProductsMap
                        .computeIfAbsent(product.getSeller(), k -> new ArrayList<>())
                        .add(product);
            }

            for (Map.Entry<Seller, List<Product>> entry : sellerProductsMap.entrySet()) {
                Seller seller = entry.getKey();
                List<Product> sellerProducts = entry.getValue();

                Order order = new Order(sellerProducts, customer, LocalDateTime.now(), selectedAddress);
                seller.getOrders().add(order);

                double sellerShare = 0;
                for (Product product : sellerProducts) {
                    product.setStock(product.getStock() - 1);
                    sellerShare += product.getPrice() * 0.9;
                }

                seller.getWallet().deposit(sellerShare, "Product sold to " + customer.getEmail());
            }

            customer.getWallet().withdraw(totalCost, "Buying");
            Order newOrder = new Order(products, customer, LocalDateTime.now(), selectedAddress);
            customer.getOrders().add(newOrder);
            clearCart();

            System.out.println("Order completed successfully!");
        } else {
            System.out.println("Checkout canceled.");
        }
    }

    private boolean checkStockAvailability() {
        for (Product product : products) {
            if (product.getStock() <= 0) {
                System.out.println("Product " + product.getName() + " is out of stock.");
                return false;
            }
        }
        return true;
    }

    private double handlePriceWithAddress(Address customerAddress) {
        final double NORMAL_SHIPPING_COST = 30000;
        boolean allInSameProvince = true;

        for (Product product : products) {
            Seller seller = product.getSeller();
            String sellerAddress = seller.getProvince();
            if (!customerAddress.getProvince().equalsIgnoreCase(sellerAddress)) {
                allInSameProvince = false;
                break;
            }
        }
        return allInSameProvince ? NORMAL_SHIPPING_COST / 3.0 : NORMAL_SHIPPING_COST;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    public void clearCart() {
        products.clear();
        System.out.println("Shopping cart has been cleared.");
    }
}
