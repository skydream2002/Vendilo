package ir.ac.kntu;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class HtmlExporter {

    public static void exportProductsToHtml(List<Product> products, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("<!DOCTYPE html>");
            writer.println("<html lang=\"en\">");
            writer.println("<head>");
            writer.println("    <meta charset=\"UTF-8\">");
            writer.println("    <title>Search Results</title>");
            writer.println("    <style>");
            writer.println("        body { font-family: sans-serif; padding: 20px; }");
            writer.println("        h1 { color: #333; }");
            writer.println("        table { width: 100%; border-collapse: collapse; margin-top: 15px; }");
            writer.println("        th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }");
            writer.println("        th { background-color: #f7f7f7; }");
            writer.println("        tr:hover { background-color: #f0f0f0; }");
            writer.println("    </style>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("    <h1>Search Results</h1>");
            writer.println("    <table>");
            writer.println("        <thead>");
            writer.println("            <tr>");
            writer.println("                <th>#</th>");
            writer.println("                <th>Product</th>");
            writer.println("                <th>Category</th>");
            writer.println("                <th>Price</th>");
            writer.println("                <th>Stock</th>");
            writer.println("            </tr>");
            writer.println("        </thead>");
            writer.println("        <tbody>");

            int index = 1;
            for (Product product : products) {
                writer.println("            <tr>");
                writer.printf("                <td>%d</td>%n", index++);
                writer.printf("                <td>%s</td>%n", product.getName());
                writer.printf("                <td>%s</td>%n", product.getType().name());
                writer.printf("                <td>$%.2f</td>%n", product.getPrice());
                writer.printf("                <td>%d</td>%n", product.getStock());
                writer.println("            </tr>");
            }

            writer.println("        </tbody>");
            writer.println("    </table>");
            writer.println("</body>");
            writer.println("</html>");

        } catch (IOException e) {
            System.out.println("Failed to export HTML: " + e.getMessage());
        }
    }
}
