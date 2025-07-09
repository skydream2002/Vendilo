package ir.ac.kntu;

import ir.ac.kntu.util.SafeInput;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;

public class Supporter extends User {
    private String userName;
    private EnumSet<SupportSection> sections = EnumSet.noneOf(SupportSection.class);

    @Override
    public void usersMenu(Scanner scanner) {
        while (true) {
            System.out.println("--------Support Menu--------");
            System.out.println("--1.Authentication Request--");
            System.out.println("----2.Follow-up Requests----");
            System.out.println("----------3.Orders----------");
            System.out.println("-----------4.back-----------");
            System.out.println("choose :");
            int choice = SafeInput.getInt(scanner);

            switch (choice) {
                case 1 -> handleAuthentication(scanner);
                case 2 -> handleCustomersRequests(scanner);
                case 3 -> manageOrders(scanner);
                case 4 -> {
                    return;
                }
                default -> System.out.println("invalid choice");
            }
        }
    }

    private void handleAuthentication(Scanner scanner) {
        while (true) {
            List<SellerSignUpRequest> pendingRequests = UserRepository.getPendingSellerRequests().stream()
                    .filter(request -> request.getReasonRejection() == null)
                    .toList();

            if (pendingRequests.isEmpty()) {
                System.out.println("No pending seller requests.");
                return;
            }

            System.out.println("--- Sign up requests ---");

            PaginationHelper<SellerSignUpRequest> pagination = new PaginationHelper<>() {
                @Override
                public String formatItem(SellerSignUpRequest request) {
                    return "store name : " + request.getStoreName()
                            + " seller's email : " + request.getEmail();
                }
            };

            try {
                pagination.paginate(pendingRequests, scanner, (request, sc) -> {
                    showRequest(scanner, request);
                });
            } catch (ExitPaginationException e) {
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
            int selection = SafeInput.getInt(scanner);

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
                    throw new ExitPaginationException();
                }
                case 2 -> {
                    System.out.println("Enter reason for rejection:");
                    String reason = scanner.nextLine();
                    request.setReasonRejection(reason);

                    System.out.println("Request rejected. Reason: " + reason);
                    throw new ExitPaginationException();
                }
                default -> System.out.println("Invalid selection.");
            }
        }
    }

    private void handleCustomersRequests(Scanner scanner) {
        while (true) {
            System.out.println("--- Support Request Management ---");
            System.out.println("1. View all assigned requests");
            System.out.println("2. Filter by status");
            System.out.println("3. Filter by category");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int choice = SafeInput.getInt(scanner);

            List<CustomerSupportRequest> requests = SupportRepository.getAllRequests().stream()
                    .filter(req -> sections.stream()
                            .anyMatch(section -> section.name().equalsIgnoreCase(req.getCategory())))
                    .toList();

            switch (choice) {
                case 1 -> displayRequests(requests, scanner);
                case 2 -> {
                    System.out.print("Enter status (submitted/in progress/closed): ");
                    String status = scanner.nextLine().trim().toLowerCase();
                    displayRequests(requests.stream()
                            .filter(req -> req.getStatus().equalsIgnoreCase(status))
                            .toList(), scanner);
                }
                case 3 -> filterCategory(scanner, requests);
                case 0 -> {
                    return;
                }
            }
        }
    }

    private void filterCategory(Scanner scanner, List<CustomerSupportRequest> requests) {
        List<SupportSection> sectionList = sections.stream().toList();
        while (true) {
            for (int i = 0; i < sections.size(); i++) {
                System.out.println((i + 1) + ". " + sectionList.get(i).name());
            }
            System.out.println("0. back");
            int selection = SafeInput.getInt(scanner);

            if (selection == 0) {
                return;
            } else if (selection >= 1 && selection <= sectionList.size()) {
                String selectedCategory = sectionList.get(selection - 1).name();
                displayRequests(requests.stream()
                        .filter(req -> req.getCategory().equalsIgnoreCase(selectedCategory))
                        .toList(), scanner);
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void displayRequests(List<CustomerSupportRequest> requests, Scanner scanner) {
        if (requests.isEmpty()) {
            System.out.println("No requests found.");
            return;
        }
        PaginationHelper<CustomerSupportRequest> pagination = new PaginationHelper<>() {
            @Override
            public String formatItem(CustomerSupportRequest request) {
                return request.getCategory() + " - " + request.getStatus();
            }
        };

        pagination.paginate(requests, scanner, (request, sc) -> {
            respondToRequest(request, scanner);
        });
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
        int choice = SafeInput.getInt(scanner);

        if (choice == 1) {
            System.out.println("Enter your response:");
            String response = scanner.nextLine();
            request.setSupportResponse(response);

            System.out.println("Set new status (in progress / closed):");
            String status = scanner.nextLine().toLowerCase();
            request.setStatus(status);

            MyNotification notification = new MyNotification("Support", "your request has been reviewed");
            notification.setRequest(request);
            request.getCustomer().addNotifications(notification);

            System.out.println("Response saved successfully.");
        }
    }

    private void manageOrders(Scanner scanner) {
        while (true) {
            System.out.println("--- Order Management ---");
            System.out.println("1. View all orders");
            System.out.println("2. Filter by customer email");
            System.out.println("3. Filter by date range");
            System.out.println("0. Back");
            System.out.print("Choose: ");
            int choice = SafeInput.getInt(scanner);

            switch (choice) {
                case 1 -> OrderService.orderMenu(this, OrderRepository.getAllOrders(), scanner);
                case 2 -> {
                    System.out.print("Enter customer email: ");
                    String email = scanner.nextLine();
                    OrderService.orderMenu(this, OrderRepository.getOrdersByCustomerEmail(email), scanner);
                }
                case 3 -> {
                    System.out.print("Enter start date (yyyy-mm-dd): ");
                    String start = scanner.nextLine();
                    System.out.print("Enter end date (yyyy-mm-dd): ");
                    String end = scanner.nextLine();
                    OrderService.orderMenu(this, OrderRepository.getOrdersByDateRange(start, end), scanner);
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
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

    public EnumSet<SupportSection> getSections() {
        return sections;
    }

    public void printSections() {
        System.out.println("--- Assigned secions ---");
        for (SupportSection section : sections) {
            System.out.println(section.name());
        }
        System.out.println("------------------------");
    }

    public void setSections(EnumSet<SupportSection> sections) {
        this.sections = sections;
    }

    public void addSection(SupportSection section) {
        sections.add(section);
    }

    public void removeSection(SupportSection section) {
        sections.remove(section);
    }

}