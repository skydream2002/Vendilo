package ir.ac.kntu;

import ir.ac.kntu.util.SafeInput;
import java.util.*;
import java.util.stream.Collectors;

public class Manager extends User {

    @Override
    public void usersMenu(Scanner scanner) {
        while (true) {
            System.out.println("********** Manager Menu ***********");
            System.out.println("-------- 1.User management --------");
            System.out.println("-- 2.Reviewing sales performance --");
            System.out.println("-- 3. reviewing buyer performane --");
            System.out.println("---- 4.Universal discount code ----");
            System.out.println("-------- 5.public message ---------");
            System.out.println("-------------- 0.back -------------");

            int choice = SafeInput.getInt(scanner);
            if (!handleMenuOptions(choice, scanner)) {
                return;
            }
        }
    }

    private boolean handleMenuOptions(int number, Scanner scanner) {
        switch (number) {
            case 1 -> {
                userManagement(scanner);
                return true;
            }
            case 2 -> {
                salesPerformance(scanner);
                return true;
            }
            case 3 -> {

                return true;
            }
            case 4 -> {

                return true;
            }
            case 5 -> {

                return true;
            }
            case 0 -> {
                return false;
            }
            default -> {
                System.out.println("Invalid choice.");
                return true;
            }
        }
    }

    private void userManagement(Scanner scanner) {
        while (true) {
            System.out.println("**** User Management Menu ****");
            System.out.println("------ 1.View all users ------");
            System.out.println("----- 2. View customers ------");
            System.out.println("------ 3. View sellers -------");
            System.out.println("----- 4. View supporters -----");
            System.out.println("- 5.Add Manager or Supporter -");
            System.out.println("---------- 0. back -----------");

            int choice = SafeInput.getInt(scanner);
            if (!handleUserOption(scanner, choice)) {
                return;
            }
        }
    }

    private boolean handleUserOption(Scanner scanner, int number) {
        switch (number) {
            case 1 -> {
                viewUsersMenu(scanner, "user");
                return true;
            }
            case 2 -> {
                viewUsersMenu(scanner, "customer");
                return true;
            }
            case 3 -> {
                viewUsersMenu(scanner, "seller");
                return true;
            }
            case 4 -> {
                viewUsersMenu(scanner, "supporter");
                return true;
            }
            case 5 -> {
                createManagerOrSupporter(scanner);
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

    private void createManagerOrSupporter(Scanner scanner) {
        printHeader();
        int choice = SafeInput.getInt(scanner);
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();

        String email = UserRepository.getValidEmail(scanner);

        String password = UserRepository.getValidPassword(scanner);

        String phoneNumber = UserRepository.getValidPhoneNumber(scanner);

        switch (choice) {
            case 1 -> {
                Manager manager = new Manager(email, firstName, lastName, password, phoneNumber);
                UserRepository.addManager(manager);
                System.out.println("Manager created successfully.");
            }
            case 2 -> {
                Supporter supporter = new Supporter(email, firstName, lastName, password, phoneNumber);
                assignSupportSections(scanner, supporter);
                UserRepository.addSupport(supporter);
                System.out.println("Supporter created successfully.");
            }
            case 0 -> {
                return;
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    private void assignSupportSections(Scanner scanner, Supporter supporter) {
        System.out.println("Assign support sections (enter 0 to finish):");
        while (true) {
            for (int i = 0; i < SupportSection.values().length; i++) {
                System.out.println((i + 1) + ". " + SupportSection.values()[i]);
            }
            int sectionIndex = SafeInput.getInt(scanner) - 1;
            if (sectionIndex == -1) {
                break;
            }
            if (sectionIndex >= 0 && sectionIndex < SupportSection.values().length) {
                supporter.addSection(SupportSection.values()[sectionIndex]);
            } else {
                System.out.println("Invalid index.");
            }
        }
    }

    private void printHeader() {
        System.out.println("Choose role to create:");
        System.out.println("1. Manager");
        System.out.println("2. Supporter");
        System.out.println("0.back");
    }

    private void viewUsersMenu(Scanner scanner, String role) {
        while (true) {
            handleRole(role);
            System.out.println("---- 2. filter by name ----");
            System.out.println("--------- 0. back ---------");

            int selection = SafeInput.getInt(scanner);
            switch (selection) {
                case 1 -> viewAllUsers(scanner, role, "");
                case 2 -> {
                    String name = scanner.nextLine().trim();
                    viewAllUsers(scanner, role, name);
                }
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void handleRole(String role) {
        switch (role) {
            case "customer" -> System.out.println("---- 1. view all customers ----");
            case "seller" -> System.out.println("---- 1. view all sellers ----");
            case "user" -> System.out.println("---- 1. view all users ----");
            case "supporter" -> System.out.println("---- 1. view all supporters ----");
        }
    }

    private void viewAllUsers(Scanner scanner, String role, String name) {
        List<User> users;
        if ("".equals(name.trim())) {
            users = returnUsers(role);
        } else {
            users = returnUsers(role).stream()
                    .filter(user -> (user.getFirstName() + " " + user.getLastName())
                            .toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (users.isEmpty()) {
            System.out.println("No users found for this criteria.");
            return;
        }

        PaginationHelper<User> pagination = new PaginationHelper<>() {
            @Override
            public String formatItem(User user) {
                return user.getFirstName() + " " + user.getLastName() + " | "
                        + (user.isActive() ? "Active" : "Blocked");
            }
        };
        pagination.paginate(users, scanner, (user, sc) -> {
            showUserDetails(scanner, user);
        });
    }

    private List<User> returnUsers(String role) {
        return switch (role) {
            case "customer" -> new ArrayList<>(UserRepository.getCustomers());
            case "seller" -> new ArrayList<>(UserRepository.getSellers());
            case "user" -> new ArrayList<>(UserRepository.getAllUsers());
            case "supporter" -> new ArrayList<>(UserRepository.getSupports());
            default -> Collections.emptyList();
        };
    }

    private void showUserDetails(Scanner scanner, User user) {
        while (true) {
            System.out.println("****= User details =****");
            System.out.println(user);
            System.out.println("-- 1.Edit information --");

            if (user.isActive()) {
                System.out.println("---- 2. Block user -----");
            } else {
                System.out.println("--- 2. Unblock user ----");
            }

            if (user instanceof Supporter) {
                System.out.println("-- 3. Edit Support Sections --");
            }
            System.out.println("------- 0. back --------");

            int choice = SafeInput.getInt(scanner);

            switch (choice) {
                case 1 -> editInformation(scanner, user);
                case 2 -> {
                    if (user.isActive()) {
                        blockUser(user);
                        System.out.println("User blocked.");
                    } else {
                        unblockUser(user);
                        System.out.println("User unblocked.");
                    }
                }
                case 3 -> {
                    if (user instanceof Supporter supporter) {
                        editSupporterSections(scanner, supporter);
                    }
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void editInformation(Scanner scanner, User user) {
        while (true) {
            System.out.println("-- 1. Edit first name --");
            System.out.println("--- 2.Edit last name ---");
            System.out.println("-------- 0. back -------");
            System.out.println("Select your choice :");
            int selection = SafeInput.getInt(scanner);

            switch (selection) {
                case 1 -> editFirstName(scanner, user);
                case 2 -> editLastName(scanner, user);
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid selection.");
            }
        }
    }

    private void editFirstName(Scanner scanner, User user) {
        System.out.println("Enter new first name :");
        String firstName = scanner.nextLine();
        user.setFirstName(firstName);
        System.out.println("First name updated.");
    }

    private void editLastName(Scanner scanner, User user) {
        System.out.println("Enter new last name :");
        String lastName = scanner.nextLine();
        user.setLastName(lastName);
        System.out.println("Last name updated.");
    }

    private void blockUser(User user) {
        user.setActive(false);
    }

    private void unblockUser(User user) {
        user.setActive(true);
    }

    private void editSupporterSections(Scanner scanner, Supporter supporter) {
        while (true) {
            supporter.printSections();
            System.out.println("1. Add Section");
            System.out.println("2. Remove Section");
            System.out.println("0. Back");

            int choice = SafeInput.getInt(scanner);
            switch (choice) {
                case 1 -> addSection(scanner, supporter);
                case 2 -> removeSection(scanner, supporter);
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void addSection(Scanner scanner, Supporter supporter) {
        for (SupportSection section : SupportSection.values()) {
            System.out.println(section.ordinal() + 1 + ". " + section);
        }
        int index = SafeInput.getInt(scanner) - 1;
        if (index >= 0 && index < SupportSection.values().length) {
            if (!supporter.getSections().contains(SupportSection.values()[index])) {
                supporter.addSection(SupportSection.values()[index]);
            } else {
                System.out.println("Section already added.");
            }
        } else {
            System.out.println("invalid choice.");
        }
    }

    private void removeSection(Scanner scanner, Supporter supporter) {
        List<SupportSection> current = new ArrayList<>(supporter.getSections());
        for (int i = 0; i < current.size(); i++) {
            System.out.println(i + 1 + ". " + current.get(i));
        }
        int index = SafeInput.getInt(scanner) - 1;
        if (index >= 0 && index < current.size()) {
            supporter.removeSection(current.get(index));
        }
    }

    private void salesPerformance(Scanner scanner) {
        while (true) {
            System.out.println("----- Sales performance -----");
            System.out.println("---- 1. View all sellers ----");
            System.out.println("----- 2. Search seller ------");
            System.out.println("---------- 0. back ----------");

            int choice = SafeInput.getInt(scanner);
            if (!handleSalePer(choice, scanner)) {
                return;
            }
        }
    }

    private boolean handleSalePer(int number, Scanner scanner) {
        switch (number) {
            case 1 -> {
                viewSellersPer(scanner);
                return true;
            }
            case 2 -> {
                searchSeller(scanner);
                return true;
            }
            case 0 -> {
                return false;
            }
            default -> {
                System.out.println("Invalid chioce.");
                return true;
            }
        }
    }

    private void viewSellersPer(Scanner scanner) {
        PaginationHelper<Seller> pagination = new PaginationHelper<>() {
            @Override
            public String formatItem(Seller seller) {
                return seller.getFirstName() + " " + seller.getLastName() + " | " + "Total sales : "
                + seller.getTotalSales();
            }
        };

        pagination.paginate(UserRepository.getSellers(), scanner, (seller, sc) -> {
            bonusToSeller(scanner, seller);
        });
    }

    private void bonusToSeller(Scanner scanner, Seller seller) {
        while (true) {
            System.out.println("--- Bonus to the seller ---");
            System.out.println("How much do you want to reward? (please enter)");
            System.out.println("---- (-1). back ------");
            double selection = SafeInput.getDouble(scanner);

            if (selection == -1) {
                System.out.println("Canceled reward.");
                return;
            }
            seller.getWallet().deposit(selection, "Reward from manager");
        }
    }

    private void searchSeller(Scanner scanner) {
        System.out.println("Please enter seller's code :");
        String code = scanner.nextLine();
        
        for (Seller seller : UserRepository.getSellers()) {
            if (code.equals(seller.getAgencyCode())) {
                bonusToSeller(scanner, seller);
                return;
            }
        }
        System.out.println("No seller found with this code.");
    }

    public Manager(String email, String firstName, String lastName, String password, String phoneNumber) {
        super(email, firstName, lastName, password, phoneNumber);
    }

}
