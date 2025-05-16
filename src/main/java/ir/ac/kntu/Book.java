package ir.ac.kntu;

public class Book extends Product{
    private String author;
    private int pages;
    private String genre;
    private String ageGroup;
    private String ISBN;

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
