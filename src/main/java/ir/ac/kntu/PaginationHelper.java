package  ir.ac.kntu;

import java.util.*;
import java.util.function.BiConsumer;

public class PaginationHelper<T> {
    private static final int PAGE_SIZE = 5;

    public void paginate(List<T> items, Scanner scanner, BiConsumer<T, Scanner> onItemSelected) {
        if (items == null || items.isEmpty()) {
            System.out.println("Nothing to display.");
            return;
        }
        int totalPage = (int) Math.ceil((double) items.size() / PAGE_SIZE);
        int currentPage = 0;
        Stack<Integer> pageHistory = new Stack<>();
        while (true) {
            showPage(items, currentPage);
            System.out.println("\n--- Page " + (currentPage + 1) + " of " + totalPage + " ---");
            System.out.println("Select item number to view details.");
            System.out.println("n: next | p: previous | b: back | page <number> | e: exit");
            String input = scanner.nextLine().trim();

            if (input.equals("e")) {
                break;
            } else if (input.equals("n") && currentPage < totalPage - 1) {
                pageHistory.push(currentPage);
                currentPage++;
            } else if (input.equals("p") && currentPage > 0) {
                pageHistory.push(currentPage);
                currentPage--;
            } else if (input.equals("b") && !pageHistory.isEmpty()) {
                currentPage = pageHistory.pop();
            } else if (input.matches("\\d+")) {
                int selection = Integer.parseInt(input);
                int index = currentPage * PAGE_SIZE + selection - 1;
                if (index >= 0 && index < items.size()) {
                    onItemSelected.accept(items.get(index), scanner);
                } else {
                    System.out.println("Invalid selection.");
                }
            } else if (input.toLowerCase().matches("page\\s+\\d+")) {
                try {
                    int page = Integer.parseInt(input.replaceAll("\\D+", "")) - 1;
                    if (page >= 0 && page < totalPage) {
                        pageHistory.push(currentPage);
                        currentPage = page;
                    } else {
                        System.out.println("Invalid page number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                }
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    private void showPage(List<T> items, int page) {
        int start = page * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, items.size());
        for (int i = start; i < end; i++) {
            System.out.println((i - start + 1) + ". " + formatItem(items.get(i)));
        }
    }

    public String formatItem(T item) {
        return item.toString();
    }
}

