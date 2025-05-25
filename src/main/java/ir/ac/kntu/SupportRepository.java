package ir.ac.kntu;

import java.util.ArrayList;
import java.util.List;

public class SupportRepository {
    private static final List<CustomerSupportRequest> allSupportRequests = new ArrayList<>();

    public static void addRequest(CustomerSupportRequest request) {
        allSupportRequests.add(request);
    }

    public static List<CustomerSupportRequest> getAllRequests() {
        return allSupportRequests;
    }

    public static List<CustomerSupportRequest> getRequestsByStatus(String status) {
        return allSupportRequests.stream()
                .filter(r -> r.getStatus().equalsIgnoreCase(status))
                .toList();
    }

    public static List<CustomerSupportRequest> getRequestsByCategory(String category) {
        return allSupportRequests.stream()
                .filter(r -> r.getCategory().equalsIgnoreCase(category))
                .toList();
    }
}
