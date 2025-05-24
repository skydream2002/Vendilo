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
}
