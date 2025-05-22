package ir.ac.kntu;

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
        System.out.println("{----Product Details (Book)----");
        System.out.println("Name : " + getName());
        System.out.println("Category : " + getType());
        System.out.println("Author : " + getAuthor());
        System.out.println("Price : " + getPrice());
        System.out.println("Seller's Name : " + getSeller().getFirstName() + " " + getSeller().getLastName());
        System.out.println("Rating Average : " + getAverageRating());
        System.out.println("Pages : " + getPages());
        System.out.println("Genre : " + getGenre());
        System.out.println("Age Group : " + getAgeGroup());
        System.out.println("ISBN : " + getISBN() + "}");
        System.out.println("Stock : " + getStock());
    }

    @Override
    public void addProduct(Scanner scanner, Seller seller) {
        super.setSeller(seller);
        System.out.println("Enter name :");
        String name = scanner.nextLine();
        super.setName(name);
        System.out.println("Price :");
        double price = scanner.nextDouble();
        scanner.nextLine();
        super.setPrice(price);
        System.out.println("Stock :");
        int stock = scanner.nextInt();
        scanner.nextLine();
        super.setStock(stock);
        System.out.println("Author : ");
        String authorBook = scanner.nextLine();
        setAuthor(authorBook);
        System.out.println("number of Pages : ");
        int pageNumbers = scanner.nextInt();
        scanner.nextLine();
        setPages(pageNumbers);
        System.out.println("Genre :");
        String genreBook = scanner.nextLine();
        setGenre(genreBook);
        System.out.println("Age Group :");
        String ageGroupBook = scanner.nextLine();
        setAgeGroup(ageGroupBook);
        System.out.println(" ISBN :");
        String isbn = scanner.nextLine();
        setISBN(isbn);
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
