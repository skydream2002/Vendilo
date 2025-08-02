package ir.ac.kntu;

import ir.ac.kntu.util.SafeInput;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer extends User {
    private List<Address> addresses = new ArrayList<>();
    private ShoppingCart shoppingCart = new ShoppingCart(this);
    private List<Order> orders = new ArrayList<>();
    private final Wallet wallet = new Wallet(UserType.CUSTOMER);
    private List<CustomerSupportRequest> supportRequests = new ArrayList<>();
    private List<Discount> discounts = new ArrayList<>();
    private Discount selectedDiscount;
    private VendiloPlus vendoliPlus = new VendiloPlus();
    private List<MyNotification> notifications = new ArrayList<>();

    @Override
    public void usersMenu(Scanner scanner) {
        while (true) {
            printCustomerMenu();
            int choice = SafeInput.getInt(scanner);
            switch (choice) {
                case 1 -> {
                    shoppingCart.shoppingCartMenu(scanner);
                }
                case 2 -> SearchProduct.showSearchMenu(this, scanner);
                case 3 -> {
                    Address addressMenu = new Address();
                    addressMenu.addressMenu(this, scanner);
                }
                case 4 -> wallet.walletMenu(scanner);
                case 5 -> OrderService.orderMenu(this, orders, scanner);
                case 6 -> {
                    Settings setting = new Settings();
                    setting.settingMenu(this, scanner);
                }
                case 7 -> supportMenu(scanner);
                case 8 -> discountMenu(scanner);
                case 9 -> vendoliPlusMenu(scanner);
                case 10 -> notificationMenu(scanner);
                case 0 -> {
                    return;
                }
                default -> System.out.println("invalid choice.");
            }
        }
    }

    private void printCustomerMenu() {
        System.out.println("----Customer Menu----");
        System.out.println("---1.Shopping Cart---");
        System.out.println("-2.Searching Products");
        System.out.println("-----3.Addresses-----");
        System.out.println("------4.Wallet-------");
        System.out.println("------5.Orders-------");
        System.out.println("------6.Setting------");
        System.out.println("------7.Support------");
        System.out.println("--8.Discount codes --");
        System.out.println("---- 9.Vendilo + ----");
        System.out.println("---10.notifications--");
        System.out.println("--------0.back-------");
        System.out.println("choose:");
    }

    private void supportMenu(Scanner scanner) {
        while (true) {
            System.out.println("---- Support Menu ----");
            System.out.println("1. Submit a new request");
            System.out.println("2. View my open requests");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int choice = SafeInput.getInt(scanner);
            switch (choice) {
                case 1 -> submitSupportRequest(scanner);
                case 2 -> viewOpenSupportRequests(scanner);
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void submitSupportRequest(Scanner scanner) {
        while (true) {
            printRequestCategories();
            int categoryChoice = SafeInput.getInt(scanner);

            if (categoryChoice == 0) {
                return;
            } else if (categoryChoice >= 1 && categoryChoice <= 5) {
                String category = switch (categoryChoice) {
                    case 1 -> "QUALITY_REPORT";
                    case 2 -> "ORDER_DISCREPANCY";
                    case 3 -> "DELIVERY_PROBLEM";
                    case 4 -> "SETTINGS";
                    case 5 -> "ORDER_REJECTION";
                    default -> "";
                };
                System.out.println("Enter your request description:");
                String message = scanner.nextLine();

                CustomerSupportRequest request = new CustomerSupportRequest(category, message, this);
                supportRequests.add(request);
                SupportRepository.addRequest(request);
                System.out.println("Request submitted successfully.");
            } else {
                System.out.println("Invalid category.");
            }
        }
    }

    private void printRequestCategories() {
        System.out.println("Select request category:");
        System.out.println("1. Product quality issue");
        System.out.println("2. Order mismatch");
        System.out.println("3. Delivery problem");
        System.out.println("4. Settings / technical issues");
        System.out.println("5. Order not received");
        System.out.println("0. back");
    }

    private void viewOpenSupportRequests(Scanner scanner) {
        List<CustomerSupportRequest> openRequests = new ArrayList<>(supportRequests);

        if (openRequests.isEmpty()) {
            System.out.println("You have no open support requests.");
            return;
        }
        PaginationHelper<CustomerSupportRequest> pagination = new PaginationHelper<>() {
            @Override
            public String formatItem(CustomerSupportRequest request) {
                return request.getCategory() + " - Status: " + request.getStatus();
            }
        };

        pagination.paginate(openRequests, scanner, (request, sc) -> {
            showRequestDetails(request, sc);
        });
    }

    private void showRequestDetails(CustomerSupportRequest request, Scanner scanner) {
        while (true) {
            System.out.println("---- Request Details ----");
            System.out.println("Category: " + request.getCategory());
            System.out.println("Status: " + request.getStatus());
            System.out.println("Your message: " + request.getMessage());

            if (request.getSupportResponse() != null) {
                System.out.println("Support reply: " + request.getSupportResponse());
            } else {
                System.out.println("Support has not replied yet.");
            }
            System.out.println("---- 0. back ----");
            System.out.println("Choose :");
            int selection = SafeInput.getInt(scanner);
            if (selection == 0) {
                return;
            }
        }
    }

    public void addToShoppingCart(Product product) {
        shoppingCart.getProducts().add(product);
    }

    public void addAddress(Address address) {
        addresses.add(address);
        System.out.println("Address successfuly added.");
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        System.out.println("Address successfuly deleted.");
    }

    public Customer(String email, String firstName, String lastName, String password, String phoneNumber) {
        super(email, firstName, lastName, password, phoneNumber);
        this.shoppingCart = new ShoppingCart(this);
        selectedDiscount = null;
    }

    public void discountMenu(Scanner scanner) {
        List<Discount> activeDiscounts = discounts;

        if (activeDiscounts.isEmpty()) {
            System.out.println("No active discount codes.");
            return;
        }

        if (selectedDiscount != null && !handleExistingDiscount(scanner)) {
            return;
        }

        showDiscountPagination(scanner, activeDiscounts);
    }

    private boolean handleExistingDiscount(Scanner scanner) {
        System.out.println("Currently selected discount: " + selectedDiscount.getSummary());
        System.out.println("Do you want to:");
        System.out.println("1. Keep current discount");
        System.out.println("2. Remove discount");
        System.out.println("3. Select a new one");
        System.out.print("Enter choice (1/2/3): ");
        String input = scanner.nextLine().trim();

        switch (input) {
            case "1":
                System.out.println("Keeping current discount.");
                return false;
            case "2":
                setSelectedDiscount(null);
                System.out.println("Discount removed.");
                return false;
            case "3":
                // proceed to selection menu
                return true;
            default:
                System.out.println("Invalid input.");
                return false;
        }
    }

    private void showDiscountPagination(Scanner scanner, List<Discount> activeDiscounts) {
        PaginationHelper<Discount> pagination = new PaginationHelper<>() {
            @Override
            public String formatItem(Discount discount) {
                return discount.getSummary();
            }
        };

        pagination.paginate(activeDiscounts, scanner, (discount, sc) -> {
            discount.showDiscountDetails(this, scanner);
        });
    }

    private void vendoliPlusMenu(Scanner scanner) {
        while (true) {
            printMenuTopics();
            int choice = SafeInput.getInt(scanner);

            if (!handleChoice(choice, scanner)) {
                return;
            }
        }
    }

    private void printMenuTopics() {
        System.out.println("========= Vendilo + ========");
        System.out.println("---- 1.Buy subscription ----");
        System.out.println("--- 2.Subscription status---");
        System.out.println("--------- 0.back -----------");
        System.out.println("Enter your choice :");
    }

    private boolean handleChoice(int number, Scanner scanner) {
        switch (number) {
            case 1 -> {
                buySubscription(scanner);
                return true;
            }
            case 2 -> {
                subscriptionStatus(scanner);
                return true;
            }
            case 0 -> {
                return false;
            }
            default -> {
                return true;
            }
        }
    }

    private void buySubscription(Scanner scanner) {
        System.out.println("==== Vendilo subscriptions ====");
        System.out.println("----- 1. One month(2000) ------");
        System.out.println("----- 2.Three month(5000)-----");
        System.out.println("------ 3. One year(19000)------");
        System.out.println("---------- 0. back ------------");

        int selection = SafeInput.getInt(scanner);

        if (selection == 0) {
            return;
        }
        double price = switch (selection) {
            case 1 -> 2000;
            case 2 -> 5000;
            case 3 -> 19000;
            default -> 0;
        };

        if (!confirmPayment(scanner, price)) {
            return;
        }
        handleBuySubscription(selection);
    }

    private void handleBuySubscription(int number) {
        switch (number) {
            case 1 -> {
                wallet.withdraw(2000, "buy 1 month vendilo +");
                Period oneMonth = Period.ofMonths(1);
                vendoliPlus.extendSubscription(oneMonth);
            }
            case 2 -> {
                wallet.withdraw(5000, "buy 3 month vendilo +");
                Period threeMonth = Period.ofMonths(3);
                vendoliPlus.extendSubscription(threeMonth);
            }
            case 3 -> {
                wallet.withdraw(19000, "buy 1 year vendilo +");
                Period oneYear = Period.ofYears(1);
                vendoliPlus.extendSubscription(oneYear);
            }
            case 0 -> {
                return;
            }
            default -> System.out.println("Invalid chioce.");
        }
    }

    private boolean confirmPayment(Scanner scanner, double totalCost) {
        System.out.println("Do you want to confirm payment? (y/n)");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if (!"y".equals(confirm)) {
            System.out.println("Checkout canceled.");
            return false;
        }

        if (wallet.getAccountBalance() < totalCost) {
            System.out.println("Insufficient wallet balance.");
            return false;
        }
        return true;
    }

    private void subscriptionStatus(Scanner scanner) {
        while (true) {
            System.out.println("==== Status ====");
            System.out.println(vendoliPlus);
            System.out.println("--- 0. back ----");

            int choice = SafeInput.getInt(scanner);
            if (choice == 0) {
                return;
            }
        }
    }

    private void printNotiMenuHeader() {
        System.out.println("*****== Notifications Menu ==*****");
        System.out.println("------ 1. view notifications -----");
        System.out.println("------------- 0. back ------------");
    }

    private void notificationMenu(Scanner scanner) {
        while (true) {
            printNotiMenuHeader();
            if (!choosingNotiMenu(scanner)) {
                return;
            }
        }
    }

    private boolean choosingNotiMenu(Scanner scanner) {
        int selection = SafeInput.getInt(scanner);
        switch (selection) {
            case 1 -> {
                showNotification(scanner);
                return true;
            }
            case 0 -> {
                return false;
            }
            default -> {
                return true;
            }
        }
    }

    private void showNotification(Scanner scanner) {
        if (this.notifications.isEmpty()) {
            System.out.println("No notification found.");
            return;
        }
        PaginationHelper<MyNotification> pagination = new PaginationHelper<>() {
            @Override
            public String formatItem(MyNotification notification) {
                return notification.getTopic();
            }
        };
        pagination.paginate(this.notifications, scanner, (notification, sc) -> {
            showNotiDetails(sc, notification);
        });
    }

    private void showNotiDetails(Scanner scanner, MyNotification notification) {
        System.out.println("=== Notification Details ===");
        System.out.println(notification);
        if (notification.getProduct() != null) {
            System.out.println("--- 1. View Product Details ---");
        } else if (notification.getRequest() != null) {
            System.out.println("--- 1. Go to Support Menu ---");
        }
        System.out.println("----------- 0. Back -----------");
        int choice = SafeInput.getInt(scanner);
        switch (choice) {
            case 0 -> {
                return;
            }
            case 1 -> {
                if (notification.getProduct() != null) {
                    Product product = notification.getProduct();
                    product.showDetails(this); // this == customer
                } else if (notification.getRequest() != null) {
                    showRequestDetails(notification.getRequest(), scanner);
                } else {
                    System.out.println("This notification has no associated content.");
                }
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    public double getMonthlyPurchase(LocalDateTime now) {
        LocalDateTime oneMonthAgo = now.minusMonths(1);
        return orders.stream()
                .filter(order -> order.getOrderDate().isAfter(oneMonthAgo))
                .mapToDouble(Order::getFinalCost)
                .sum();
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<CustomerSupportRequest> getSupportRequests() {
        return supportRequests;
    }

    public void setSupportRequests(List<CustomerSupportRequest> supportRequests) {
        this.supportRequests = supportRequests;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public Discount getSelectedDiscount() {
        return selectedDiscount;
    }

    public void setSelectedDiscount(Discount selectedDiscount) {
        this.selectedDiscount = selectedDiscount;
    }

    public void clearSelectedDiscount() {
        this.selectedDiscount = null;
    }

    public VendiloPlus getVendoliPlus() {
        return vendoliPlus;
    }

    public void setVendoliPlus(VendiloPlus vendoliPlus) {
        this.vendoliPlus = vendoliPlus;
    }

    public List<MyNotification> getNotifications() {
        return notifications;
    }

    public void addNotifications(MyNotification notification) {
        this.notifications.add(notification);
    }

    public void addDiscount(Discount discount) {
        this.discounts.add(discount);
    }
}
