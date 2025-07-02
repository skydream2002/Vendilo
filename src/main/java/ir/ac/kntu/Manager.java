package ir.ac.kntu;

import ir.ac.kntu.util.SafeInput;
import java.util.Scanner;

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
                
                return true;
            }
            case 2 -> {

                return true;
            }
            case 3 -> {

                return true;
            }
            case 4 -> {

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

    private void viewAllUsers(Scanner scanner) {
        PaginationHelper<User> pagination = new PaginationHelper<>() {
            @Override
            public String formatItem(User user) {
                return user.getFirstName() + " " + user.getLastName() + " | " + user.getPhoneNumber();
            }
        };
        pagination.paginate(UserRepository.getAllUsers(), scanner, (sc, user) -> {
            user.showUserDetails();
        });
    }

    private void showUserDetails() {

    }


    public Manager(String email, String firstName, String lastName, String password, String phoneNumber) {
        super(email, firstName, lastName, password, phoneNumber);
    }
}
