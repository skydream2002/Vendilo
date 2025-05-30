package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer extends User {
    private List<Address> addresses = new ArrayList<>();
    private ShoppingCart shoppingCart;
    private List<Order> orders = new ArrayList<>();
    private final Wallet wallet = new Wallet(UserType.CUSTOMER);
    private List<CustomerSupportRequest> supportRequests = new ArrayList<>();

    @Override
    public void usersMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("----Customer Menu----");
            System.out.println("---1.Shopping Cart---");
            System.out.println("-2.Searching Products");
            System.out.println("-----3.Addresses-----");
            System.out.println("------4.Wallet-------");
            System.out.println("------5.Orders-------");
            System.out.println("------6.Setting------");
            System.out.println("------7.Support------");
            System.out.println("--------8.back-------");
            System.out.println("choose:");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    if (shoppingCart == null) {
                        shoppingCart = new ShoppingCart(this);
                    }
                    shoppingCart.shoppingCartMenu();
                }
                case 2 -> SearchProduct.showSearchMenu(this);
                case 3 -> {
                    Address addressMenu = new Address();
                    addressMenu.addressMenu(this);
                }
                case 4 -> wallet.walletMenu();
                case 5 -> OrderService.orderMenu(this, orders, scanner);
                case 6 -> {
                    Settings setting = new Settings();
                    setting.settingMenu(this);
                }
                case 7 -> supportMenu(scanner);
                case 8 -> {
                    return;
                }
                default -> System.out.println("invalid choice.");
            }
        }
    }

    private void supportMenu(Scanner scanner) {
        while (true) {
            System.out.println("---- Support Menu ----");
            System.out.println("1. Submit a new request");
            System.out.println("2. View my open requests");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

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
            System.out.println("Select request category:");
            System.out.println("1. Product quality issue");
            System.out.println("2. Order mismatch");
            System.out.println("3. Settings / technical issues");
            System.out.println("4. Order not received");
            System.out.println("0. back");

            int categoryChoice = scanner.nextInt();
            scanner.nextLine();

            if (categoryChoice == 0) {
                return;
            } else if (categoryChoice >= 1 && categoryChoice <= 4) {
                String category = switch (categoryChoice) {
                    case 1 -> "Product quality issue";
                    case 2 -> "Order mismatch";
                    case 3 -> "Settings issue";
                    case 4 -> "Order not received";
                    default -> "";
                };

                System.out.println("Enter your request description:");
                String message = scanner.nextLine();

                CustomerSupportRequest request = new CustomerSupportRequest(category, message);
                supportRequests.add(request);
                SupportRepository.addRequest(request);
                System.out.println("Request submitted successfully.");
            } else {
                System.out.println("Invalid category.");
            }
        }
    }

    private void viewOpenSupportRequests(Scanner scanner) {
        List<CustomerSupportRequest> openRequests = new ArrayList<>();
        for (CustomerSupportRequest request : supportRequests) {
            if (!request.getStatus().equalsIgnoreCase("closed")) {
                openRequests.add(request);
            }
        }

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
            int selection = scanner.nextInt();
            scanner.nextLine();
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
        return new ArrayList<>(orders);
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
}
