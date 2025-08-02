package ir.ac.kntu;

public class Main {

    public static void main(String[] args) {

        LoginMenu menu = new LoginMenu();
        Manager manager = new Manager("ali.1384@email.com", "ali", "abbasi", "mohammad134A", "09151458098");
        Customer customer = new Customer("mohammad.akbari1384@email.com", "mohammad", "akbari", "M1234567@", "09926767821");
        Seller seller = new Seller("shop@email.com", "vendilo", "akbari", "Vendilo123@", "09351757690");
        UserRepository.getSellers().add(seller);
        UserRepository.getCustomers().add(customer);
        UserRepository.addManager(manager);
        menu.startMenu();
    }

}
