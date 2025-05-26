package ir.ac.kntu;

import java.util.List;
import java.util.Scanner;

public class Address {
    private String title;
    private String province;
    private String city;
    private String description;

    public void addressMenu(Customer customer) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("----- Address menu -----");
            System.out.println("---- 1. Add address ----");
            System.out.println("--- 2.View addresses ---");
            System.out.println("--- 3.Remove address ---");
            System.out.println("---- 4.Edit address ----");
            System.out.println("------- 5. back --------");
            System.out.println("Enter your selection :");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addingAddress(scanner, customer);
                case 2 -> viewAddresses(scanner, customer);
                case 3 -> removingAddress(scanner, customer);
                case 4 -> editAddress(scanner, customer);
                case 5 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public static void addingAddress(Scanner scanner, Customer customer) {
        System.out.println("Enter title :");
        String title = scanner.nextLine();
        System.out.println("Province :");
        String province = scanner.nextLine();
        System.out.println("city :");
        String city = scanner.nextLine();
        System.out.println("description :");
        String description = scanner.nextLine();
        Address address = new Address(city, description, province, title);
        customer.addAddress(address);
    }

    public void removingAddress(Scanner scanner, Customer customer) {
        List<Address> addresses = customer.getAddresses();
        
        if (addresses.isEmpty()) {
            System.out.println("No addresses available.");
            return;
        }

        PaginationHelper<Address> pagination = new PaginationHelper<>();
        pagination.paginate(addresses, scanner, (address, sc) -> {
            customer.removeAddress(address);
        });
    }

    public void editAddress(Scanner scanner, Customer customer) {
        List<Address> addresses = customer.getAddresses();

        if (addresses.isEmpty()) {
            System.out.println("No addresses available.");
            return;
        }

        PaginationHelper<Address> pagination = new PaginationHelper<>();

        pagination.paginate(addresses, scanner, (address, sc) -> {
            System.out.println("Leave field blank to keep current value.");

            System.out.println("Current title: " + address.getTitle());
            System.out.print("New title: ");
            String newTitle = sc.nextLine();
            if (!newTitle.isBlank()) {
                address.setTitle(newTitle);
            }

            System.out.println("Current province: " + address.getProvince());
            System.out.print("New province: ");
            String newProvince = sc.nextLine();
            if (!newProvince.isBlank()) {
                address.setProvince(newProvince);
            }

            System.out.println("Current city: " + address.getCity());
            System.out.print("New city: ");
            String newCity = sc.nextLine();
            if (!newCity.isBlank()) {
                address.setCity(newCity);
            }

            System.out.println("Current description: " + address.getDescription());
            System.out.print("New description: ");
            String newDescription = sc.nextLine();
            if (!newDescription.isBlank()) {
                address.setDescription(newDescription);
            }

            System.out.println("Address updated successfully.\n");
        });
    }

    public void viewAddresses(Scanner scanner, Customer customer) {
        List<Address> addresses = customer.getAddresses();

        if (addresses.isEmpty()) {
            System.out.println("No addresses available.");
            return;
        }

        PaginationHelper<Address> pagination = new PaginationHelper<>();
        pagination.paginate(addresses, scanner, (address, sc) -> {
        });
    }

    public void showAddressDetails(Customer customer) {
        System.out.println("---- Address Details ----");
        int number = 1;
        for (Address address : customer.getAddresses()) {
            System.out.println(number + ". " + address);
            number++;
        }
    }

    public Address(String city, String description, String province, String title) {
        this.city = city;
        this.description = description;
        this.province = province;
        this.title = title;
    }

    public Address() {
    }

    public Address(String title, String province, String city) {
        this.title = title;
        this.province = province;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Province: " + province + ", City: " + city + ", Description: " + description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
