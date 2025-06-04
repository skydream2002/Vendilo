package ir.ac.kntu.util;

import java.util.Scanner;

public class SafeInput {

    public static int getInt(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static double getDouble(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();

            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
            }
        }
    }

    public static boolean getBooleanInput(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true")) {
                return true;
            } else if (input.equals("false")) {
                return false;
            } else {
                System.out.println("please enter just true or false");
            }
        }
    }
}
