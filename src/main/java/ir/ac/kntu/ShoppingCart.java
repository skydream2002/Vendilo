package ir.ac.kntu;

import ir.ac.kntu.util.SafeInput;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ShoppingCart {
    private List<Product> products = new ArrayList<>();
    private Customer customer;

    public ShoppingCart(Customer customer) {
        this.customer = customer;
    }

    public void shoppingCartMenu(Scanner scanner) {
        while (true) {
            System.out.println("====== Shopping Cart Menu ====== ");
            System.out.println("---------1.view products---------");
            System.out.println("--------2.Remove a product-------");
            System.out.println("------------3.Checkout-----------");
            System.out.println("-----------4.Clear cart----------");
            System.out.println("-------------5.Back--------------");
            System.out.println("Choose an option: ");
            int choice = SafeInput.getInt(scanner);

            switch (choice) {
                case 1 -> viewProducts(scanner);
                case 2 -> removeFromCart(scanner);
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

    public void viewProducts(Scanner scanner) {
        if (products.isEmpty()) {
            System.out.println("shopping cart is empty");
            return;
        }

        double totalPrice = getFinalPriceWithDiscount();
        System.out.println("Total price : " + totalPrice + "toman");

        PaginationHelper<Product> pagination = new PaginationHelper<>() {
            @Override
            public String formatItem(Product product) {
                double finalPrice = customer.getVendoliPlus().isActive()
                        ? product.getPrice() * 0.95
                        : product.getPrice();

                return product.getName() +
                        " | Price : " + finalPrice + " toman" +
                        " | Category : " + product.getType().name();
            }
        };
        pagination.paginate(products, scanner, (product, sc) -> {
            while (true) {
                product.showDetails(customer);
                System.out.println("----- 1. Back -----");
                System.out.print("Choose: ");
                String choice = sc.nextLine();

                if ("1".equals(choice)) {
                    break;
                } else {
                    System.out.println("Invalid choice.");
                }
            }
        });
    }

    public void removeFromCart(Scanner scanner) {
        if (products.isEmpty()) {
            System.out.println("shopping cart is empty");
            return;
        }

        System.out.println("----- Products in Cart -----");
        PaginationHelper<Product> pagination = new PaginationHelper<>();
        pagination.paginate(products, scanner, (product, sc) -> {
            System.out.println("Are you sure you want to remove \"" + product.getName() + "\" from the cart?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.print("Choose: ");
            String input = sc.nextLine();
            if ("1".equals(input)) {
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

        PaymentInfo paymentInfo = calculatePaymentInfo(selectedAddress);
        displayPaymentSummary(paymentInfo);

        handleDiscountSelection(scanner);

        double finalCost = applyingDiscount();
        if (finalCost == -1) {
            finalCost = paymentInfo.totalCost;
        }

        if (!confirmPayment(scanner, finalCost)) {
            return;
        }

        processPayment(selectedAddress, finalCost);
    }

    private double applyingDiscount() {
        if (!isApplyDiscount()) {
            return -1;
        }

        double finalCost = getFinalPriceWithDiscount();

        System.out.println("Total cost with discount : " + finalCost);
        return finalCost;
    }

    private void handleDiscountSelection(Scanner scanner) {
        System.out.println("Do you want to apply a discount? (y/n)");
        String selection = scanner.nextLine().trim().toLowerCase();

        if ("y".equals(selection)) {
            customer.discountMenu(scanner);
        } else if (!"n".equals(selection)) {
            System.out.println("Invalid selection.");
            handleDiscountSelection(scanner);
        }
    }

    private boolean isApplyDiscount() {
        if (customer.getSelectedDiscount() == null) {
            System.out.println("No discount selected.");
            return false;
        }
        if (customer.getSelectedDiscount().getUsageLimit() <= 0) {
            System.out.println("Discount code is out of stock.");
            return false;
        }
        if (customer.getSelectedDiscount() instanceof DiscountByValue discount) {
            if (!(discount.getValue() * 10 < getTotalPrice())) {
                System.out.println("The purchase must be more than ten times the discount.");
                return false;
            }
        }
        return true;
    }

    private PaymentInfo calculatePaymentInfo(Address address) {
        double totalProductPrice = getFinalPriceWithDiscount();
        double shippingCost = handlePriceWithAddress(address);
        double totalCost = totalProductPrice + shippingCost;
        return new PaymentInfo(shippingCost, totalCost);
    }

    private void displayPaymentSummary(PaymentInfo paymentInfo) {
        System.out.println("Shipping cost: " + paymentInfo.shippingCost + " toman");
        System.out.println("Total cost (with shipping): " + paymentInfo.totalCost + " toman");
        System.out.println("Your wallet balance: " + customer.getWallet().getAccountBalance() + " toman");
    }

    private boolean confirmPayment(Scanner scanner, double totalCost) {
        System.out.println("Do you want to confirm payment? (y/n)");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (!"y".equals(confirm)) {
            System.out.println("Checkout canceled.");
            return false;
        }

        if (customer.getWallet().getAccountBalance() < totalCost) {
            System.out.println("Insufficient wallet balance.");
            return false;
        }
        return true;
    }

    private void processPayment(Address address, double totalCost) {
        Map<Seller, List<Product>> sellerProducts = groupProductsBySeller();

        sellerProducts.forEach((seller, products) -> {
            createSellerOrder(seller, products, address);
            processSellerPayment(seller, products);
        });

        completeCustomerOrder(address, totalCost);
        clearCart();
        System.out.println("Order completed successfully!");
    }

    private Map<Seller, List<Product>> groupProductsBySeller() {
        Map<Seller, List<Product>> map = new HashMap<>();
        products.forEach(product -> map.computeIfAbsent(product.getSeller(), k -> new ArrayList<>()).add(product));
        return map;
    }

    private void createSellerOrder(Seller seller, List<Product> products, Address address) {
        Order order = new Order(products, customer, LocalDateTime.now(), address, getTotalPrice());
        seller.getOrders().add(order);
    }

    private void processSellerPayment(Seller seller, List<Product> products) {
        Map<Product, Long> productCounts = products.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        double sellerShare = 0;

        for (Map.Entry<Product, Long> entry : productCounts.entrySet()) {
            Product product = entry.getKey();
            long quantity = entry.getValue();

            product.setStock(product.getStock() - (int) quantity);
            sellerShare += product.getPrice() * 0.9 * quantity;
        }

        seller.getWallet().deposit(sellerShare, "Product sold to " + customer.getEmail());
    }

    private void completeCustomerOrder(Address address, double totalCost) {
        customer.getWallet().withdraw(totalCost, "Buying");
        Order newOrder = new Order(new ArrayList<>(products), customer, LocalDateTime.now(), address, totalCost);
        customer.getOrders().add(newOrder);
        OrderRepository.addOrder(newOrder);
    }

    private class PaymentInfo {
        private final double shippingCost;
        private final double totalCost;

        PaymentInfo(double shippingCost, double totalCost) {
            this.shippingCost = shippingCost;
            this.totalCost = totalCost;
        }
    }

    private boolean checkStockAvailability() {
        Map<Product, Long> productCounts = products.stream()
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        for (Map.Entry<Product, Long> entry : productCounts.entrySet()) {
            Product product = entry.getKey();
            long countInCart = entry.getValue();

            if (product.getStock() < countInCart) {
                System.out.println("Product " + product.getName() + " has only " + product.getStock() +
                        " in stock, but you added " + countInCart + " to the cart.");
                return false;
            }
        }

        return true;
    }

    private double handlePriceWithAddress(Address customerAddress) {
        double shippingCost = 30000;
        boolean allInSameProvince = true;

        for (Product product : products) {
            Seller seller = product.getSeller();
            String sellerAddress = seller.getProvince();
            if (!customerAddress.getProvince().equalsIgnoreCase(sellerAddress)) {
                allInSameProvince = false;
                break;
            }
        }

        if (customer.getVendoliPlus().isActive()) {
            return allInSameProvince ? 0 : shippingCost / 3.0;
        } else {
            return allInSameProvince ? shippingCost / 3.0 : shippingCost;
        }
    }

    public double getFinalPriceWithDiscount() {
        double basePrice = customer.getVendoliPlus().isActive() ? getTotalPrice() * 0.95 : getTotalPrice();
        Discount discount = customer.getSelectedDiscount();

        if (discount == null || !isApplyDiscount()) {
            return basePrice;
        }

        if (discount instanceof DiscountByValue valueDiscount) {
            return Math.max(0, basePrice - valueDiscount.getValue());
        } else if (discount instanceof DiscountByPercentage percentDiscount) {
            return Math.max(0, basePrice - (basePrice * percentDiscount.getPercentage() / 100));
        }

        return basePrice;
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
