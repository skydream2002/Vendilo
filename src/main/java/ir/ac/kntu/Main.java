package ir.ac.kntu;

public class Main {

    public static void main(String[] args) {

        LoginMenu menu = new LoginMenu();
        Supporter admin = new Supporter("ali.1384@email.com", "ali", "abbasi", "mohammad134A", "09151458098");
        UserRepository.addSupport(admin);
        menu.startMenu();


    }

}
