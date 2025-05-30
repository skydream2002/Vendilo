package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

public class SupportRepository {
    private static final List<CustomerSupportRequest> allRequests = new ArrayList<>();

    public static void addRequest(CustomerSupportRequest request) {
        allRequests.add(request);
    }

    public static List<CustomerSupportRequest> getAllRequests() {
        return allRequests;
    }

    public static List<CustomerSupportRequest> getRequestsByStatus(String status) {
        return allRequests.stream()
                .filter(r -> r.getStatus().equalsIgnoreCase(status))
                .toList();
    }

    public static List<CustomerSupportRequest> getRequestsByCategory(String category) {
        return allRequests.stream()
                .filter(r -> r.getCategory().equalsIgnoreCase(category))
                .toList();
    }
}
