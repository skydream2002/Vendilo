package ir.ac.kntu;

public class Main {

    public static void main(String[] args) {

        LoginMenu menu = new LoginMenu();
        Supporter admin = new Supporter("ali.1384@email.com", "ali", "abbasi", "mohammad134A", "09151458098");
        Customer customer = new Customer("mohammad.akbari1384@email.com" , "mohammad", "akbari", "M1234567@", "09926767821");
        Seller seller = new Seller("shop@email.com", "vendilo", "akbari", "vendilo123@", "09351757690");
        UserRepository.getSellers().add(seller);
        UserRepository.getCustomers().add(customer);
        UserRepository.addSupport(admin);
        menu.startMenu();
    }

}
