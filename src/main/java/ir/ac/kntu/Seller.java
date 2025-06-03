package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Seller extends User {
    private String agencyCode;
    private String storeName;
    private int nationalCode;
    private String province;
    private boolean isVerified;
    private List<Product> products = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private final Wallet wallet = new Wallet(UserType.SELLER);

    public Seller(String storeName, int nationalCode, String province, String email, String firstName, String lastName,
            String password, String phoneNumber) {
        super(email, firstName, lastName, password, phoneNumber);
        this.storeName = storeName;
        this.nationalCode = nationalCode;
        this.province = province;
        this.isVerified = false;
    }

    @Override
    public void usersMenu(Scanner scanner) {

        while (true) {
            System.out.println("----Seller Menu----");
            System.out.println("--1.Veiw Products--");
            System.out.println("---2.Add Product---");
            System.out.println("------3.Wallet-----");
            System.out.println("------4.Orders-----");
            System.out.println("-------5.back------");
            System.out.println("choose:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewProducts(scanner);
                case 2 -> addingProduct(scanner);
                case 3 -> wallet.walletMenu(scanner);
                case 4 -> OrderService.orderMenu(this, orders, scanner);
                case 5 -> {
                    scanner.close();
                    return;
                }
                default -> System.out.println("invalid choice");
            }
        }

    }

    public void viewProducts(Scanner scanner) {

        PaginationHelper<Product> pagination = new PaginationHelper<>() {
            @Override
            public String formatItem(Product product) {
                return product.getName()
                        + " | " + product.getType()
                        + " | " + product.getPrice()
                        + " | stock :" + product.getStock();
            }
        };

        pagination.paginate(products, scanner, (product, sc) -> {
            changeStockAmount(sc, product);
        });
    }

    private void changeStockAmount(Scanner scanner, Product product) {
        while (true) {
            product.showDetails();
            System.out.println("--- 1. Increase Stock ---");
            System.out.println("--- 2. Decrease Stock ---");
            System.out.println("--- 0. back ---");
            System.out.println("Choose :");
            int selection = scanner.nextInt();
            switch (selection) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    System.out.println("Enter count :");
                    int count = scanner.nextInt();
                    product.increaseStock(count);
                }
                case 2 -> {
                    System.out.println("Enter count :");
                    int count = scanner.nextInt();
                    product.decreaseStock(count);
                }
                default -> System.out.println("Invalid selection.");
            }
        }
    }

    public void addingProduct(Scanner scanner) {
        while (true) {
            System.out.println("Choose category:");
            System.out.println("1. Book");
            System.out.println("2. Laptop");
            System.out.println("3. Mobile");
            System.out.println("0. back");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    Book book = new Book();
                    book.addProduct(scanner, this);
                    products.add(book);
                    System.out.println("Book added.");
                }
                case 2 -> {
                    Laptop laptop = new Laptop();
                    laptop.addProduct(scanner, this);
                    products.add(laptop);
                    System.out.println("Laptop added.");
                }
                case 3 -> {
                    Mobile mobile = new Mobile();
                    mobile.addProduct(scanner, this);
                    products.add(mobile);
                    System.out.println("Mobile added.");
                }
                default -> System.out.println("Invalid category.");
            }
        }
    }

    public Seller(String email, String firstName, String lastName, String password, String phoneNumber) {
        super(email, firstName, lastName, password, phoneNumber);
    }

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(int nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public boolean isIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
