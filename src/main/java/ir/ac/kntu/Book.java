package ir.ac.kntu;

import ir.ac.kntu.util.SafeInput;
import java.util.Scanner;


public class Book extends Product {
    private String author;
    private int pages;
    private String genre;
    private String ageGroup;
    private String ISBN;

    public Book() {
        super.setType(ProductType.BOOK);
    }

    @Override
    public void showDetails() {
        System.out.println("----Product Details (Book)----");
        System.out.println("Name : " + getName());
        System.out.println("Category : " + getType());
        System.out.println("Author : " + getAuthor());
        System.out.println("Price : " + getPrice());
        System.out.println("Seller's Name : " + getSeller().getFirstName() + " " + getSeller().getLastName());
        System.out.println("Rating Average : " + getAverageRating());
        System.out.println("Pages : " + getPages());
        System.out.println("Genre : " + getGenre());
        System.out.println("Age Group : " + getAgeGroup());
        System.out.println("ISBN : " + getISBN());
        System.out.println("Stock : " + getStock());
    }

    @Override
    public void addProduct(Scanner scanner, Seller seller) {
        setBasicInfo(scanner, seller);
        setBookDetails(scanner);
    }

    private void setBasicInfo(Scanner scanner, Seller seller) {
        super.setSeller(seller);
        System.out.println("Enter name:");
        super.setName(scanner.nextLine());

        System.out.println("Price:");
        while (true) {
            double price = SafeInput.getDouble(scanner);
            if (price > 0) {
                super.setPrice(price);
                break;
            } else {
                System.out.println("Price must be positive.");
            }
        }

        System.out.println("Stock:");
        while (true) {
            int stock = SafeInput.getInt(scanner);
            if (stock > 0) {
                super.setStock(stock);
                break;
            } else {
                System.out.println("Stock must be positive.");
            }
        }
    }

    private void setBookDetails(Scanner scanner) {
        System.out.println("Author:");
        setAuthor(scanner.nextLine());

        System.out.println("Number of Pages:");
        setPages(SafeInput.getInt(scanner));

        System.out.println("Genre:");
        setGenre(scanner.nextLine());

        System.out.println("Age Group:");
        setAgeGroup(scanner.nextLine());

        System.out.println("ISBN:");
        setISBN(scanner.nextLine());
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }
}
