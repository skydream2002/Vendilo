package ir.ac.kntu;

import java.util.List;
import java.util.Scanner;

public class Supporter extends User {
    private String userName;

    @Override
    public void usersMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("--------Support Menu--------");
            System.out.println("--1.Authentication Request--");
            System.out.println("----2.Follow-up Requests----");
            System.out.println("----------3.Orders----------");
            System.out.println("-----------4.back-----------");
            System.out.println("choose :");
            int choice = scanner.nextInt();
            // complete this
            switch (choice) {
                case 1 -> handleAuthentication(scanner);
                case 2 -> handleCustomersRequests(scanner);
                case 3 -> System.out.println("-3.Orders-");
                case 4 -> {
                    scanner.close();
                    return;
                }
                default -> System.out.println("invalid choice");
            }
        }
    }

    private void handleAuthentication(Scanner scanner) {
        while (true) {
            System.out.println("--- Sign up requests ---");
            int number = 1;
            for (SellerSignUpRequest request : UserRepository.getPendingSellerRequests()) {
                if (request.getReasonRejection() == null) {
                    System.out.println(number + ". " + "store name : " + request.getStoreName()
                            + "seller's email : " + request.getEmail());
                    number++;
                }
            }
            System.out.println("--- 0. back ---");
            System.out.println("Choose :");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                return;
            } else if (choice > 0 && choice < number) {
                showRequest(scanner, UserRepository.getPendingSellerRequests().get(choice - 1));
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void showRequest(Scanner scanner, SellerSignUpRequest request) {
        while (true) {
            System.out.println("--- Request details ---");
            System.out.println(request);
            System.out.println("---- 1. Accept request ----");
            System.out.println("---- 2. Reject request ----");
            System.out.println("---- 0. back ----");
            System.out.println("Choose : ");
            int selection = scanner.nextInt();
            scanner.nextLine();

            switch (selection) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    Seller seller = new Seller(request.getStoreName(), request.getNationalCode(), request.getProvince(),
                            request.getEmail(), request.getFirstName(), request.getLastName(), request.getPassword(),
                            request.getPhoneNumber());

                    seller.setIsVerified(true);
                    seller.setAgencyCode(request.generateAgentCode());

                    UserRepository.getSellers().add(seller);

                    UserRepository.getPendingSellerRequests().remove(request);
                    System.out.println("Request accepted successfully.");
                }
                case 2 -> {
                    System.out.println("Enter reason for rejection:");
                    String reason = scanner.nextLine();
                    request.setReasonRejection(reason);

                    System.out.println("Request rejected. Reason: " + reason);
                }
                default -> System.out.println("Invalid selection.");
            }
        }
    }

    private void handleCustomersRequests(Scanner scanner) {
        while (true) {
            System.out.println("--- Support Request Management ---");
            System.out.println("1. View all requests");
            System.out.println("2. Filter by status");
            System.out.println("3. Filter by category");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> displayRequests(SupportRepository.getAllRequests(), scanner);
                case 2 -> {
                    System.out.println("Enter status (submitted/in progress/closed):");
                    String status = scanner.nextLine().toLowerCase();
                    List<CustomerSupportRequest> filtered = SupportRepository.getRequestsByStatus(status);
                    displayRequests(filtered, scanner);
                }
                case 3 -> {
                    System.out.println("Enter category:");
                    String category = scanner.nextLine();
                    List<CustomerSupportRequest> filtered = SupportRepository.getRequestsByCategory(category);
                    displayRequests(filtered, scanner);
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void displayRequests(List<CustomerSupportRequest> requests, Scanner scanner) {
        if (requests.isEmpty()) {
            System.out.println("No requests found.");
            return;
        }

        for (int i = 0; i < requests.size(); i++) {
            CustomerSupportRequest request = requests.get(i);
            System.out.println((i + 1) + ". " + request.getCategory() + " - " + request.getStatus());
        }

        System.out.println("Enter number to view details or 0 to back:");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= requests.size()) {
            respondToRequest(requests.get(choice - 1), scanner);
        }
    }

    private void respondToRequest(CustomerSupportRequest request, Scanner scanner) {
        System.out.println("--- Request Details ---");
        System.out.println("Category: " + request.getCategory());
        System.out.println("Status: " + request.getStatus());
        System.out.println("Customer message: " + request.getMessage());

        if (request.getSupportResponse() != null) {
            System.out.println("Previous response: " + request.getSupportResponse());
        }

        System.out.println("1. Respond and update status");
        System.out.println("0. Back");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.println("Enter your response:");
            String response = scanner.nextLine();
            request.setSupportResponse(response);

            System.out.println("Set new status (in progress / closed):");
            String status = scanner.nextLine().toLowerCase();
            request.setStatus(status);

            System.out.println("Response saved successfully.");
        }
    }

    public Supporter(String email, String firstName, String lastName, String password, String phoneNumber) {
        super(email, firstName, lastName, password, phoneNumber);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}