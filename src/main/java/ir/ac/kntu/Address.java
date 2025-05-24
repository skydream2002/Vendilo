package ir.ac.kntu;

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
        if (customer.getAddresses().isEmpty()) {
            System.out.println("No addresses available.");
            return;
        }

        while (true) {
            showAddressDetails(customer);
            System.out.println("-------- 0. back --------");
            System.out.println("Select the address you want to delete.");
            int selection = scanner.nextInt();
            scanner.nextLine();

            if (selection == 0) {
                return;
            } else if (selection > 0 && selection <= customer.getAddresses().size()) {
                Address address = customer.getAddresses().get(selection - 1);
                customer.removeAddress(address);
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    public void editAddress(Scanner scanner, Customer customer) {
        if (customer.getAddresses().isEmpty()) {
            System.out.println("No addresses available.");
            return;
        }

        while (true) {
            showAddressDetails(customer);
            System.out.println("-------- 0. back --------");
            System.out.println("Select the address you want to change.");
            int selection = scanner.nextInt();
            scanner.nextLine();

            if (selection == 0) {
                return;
            } else if (selection > 0 && selection <= customer.getAddresses().size()) {
                Address address = customer.getAddresses().get(selection - 1);
                System.out.println("Leave field blank to keep current value.");
                System.out.println("Current title: " + address.getTitle());
                System.out.print("New title: ");
                String newTitle = scanner.nextLine();
                if (!newTitle.isBlank()) {
                    address.setTitle(newTitle);
                }
                System.out.println("Current province: " + address.getProvince());
                System.out.print("New province: ");
                String newProvince = scanner.nextLine();
                if (!newProvince.isBlank()) {
                    address.setProvince(newProvince);
                }
                System.out.println("Current city: " + address.getCity());
                System.out.print("New city: ");
                String newCity = scanner.nextLine();
                if (!newCity.isBlank()) {
                    address.setCity(newCity);
                }
                System.out.println("Current description: " + address.getDescription());
                System.out.print("New description: ");
                String newDescription = scanner.nextLine();
                if (!newDescription.isBlank()) {
                    address.setDescription(newDescription);
                }
                System.out.println("Address updated successfully.");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    public void viewAddresses(Scanner scanner, Customer customer) {
        if (customer.getAddresses().isEmpty()) {
            System.out.println("No addresses available.");
            return;
        }

        while (true) {
            showAddressDetails(customer);
            System.out.println("-------- 0. back --------");
            System.out.println("Enter your selection :");
            int selection = scanner.nextInt();
            scanner.nextLine();

            if (selection == 0) {
                return;
            } else {
                System.out.println("Invalid selection!");
            }
        }
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
