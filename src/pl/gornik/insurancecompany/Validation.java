package pl.gornik.insurancecompany;

import java.util.Scanner;

public class Validation {

    public static int getValidChoice(Scanner scanner, int min, int max) {
        if (min > max) max = min;
        int choice = -1;
        while (choice < min || choice > max) {
            System.out.printf("Wybierz numer opcji (%d-%d): ", min, max);
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice < min || choice > max) {
                    System.out.printf("Nieprawidłowa opcja. Wybierz numer opcji (%d-%d): ", min, max);
                }
            } else {
                System.out.println("Proszę podać liczbę.");
                scanner.next();
            }
        }
        return choice;
    }

    public static double getValidDouble(Scanner scanner, String prompt) {
        double value;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                value = scanner.nextDouble();
                scanner.nextLine();
                if (value > 0) {
                    return value;
                } else {
                    System.out.println("Wartość musi być większa od zera. Spróbuj ponownie.");
                }
            } else {
                System.out.println("Proszę podać poprawną liczbę.");
                scanner.next();
            }
        }
    }

    public static <T extends Enum<T>> T getValidEnum(Scanner scanner, Class<T> enumClass, String prompt) {
        while (true) {
            System.out.println(prompt);
            for (T constant : enumClass.getEnumConstants()) {
                System.out.println("- " + constant.name());
            }
            System.out.print("Wybierz jedną z powyższych opcji: ");
            String input = scanner.nextLine().toUpperCase();
            for (T constant : enumClass.getEnumConstants()) {
                if (constant.name().equals(input)) {
                    return constant;
                }
            }
            System.out.println("Niepoprawny wybór. Spróbuj ponownie.");
        }
    }
    public static String getValidPesel(Scanner scanner) {
        String pesel;
        String peselRegex = "^\\d{11}$";
        while (true) {
            System.out.print("Podaj numer PESEL klienta: ");
            pesel = scanner.nextLine();
            if (!pesel.matches(peselRegex)) {
                System.out.println("Numer PESEL musi mieć dokładnie 11 cyfr. Spróbuj ponownie.");
            } else {
                break;
            }
        }
        return pesel;
    }

    public static String getValidPhone(Scanner scanner) {
        String phone = "";
        while (true) {
            System.out.print("Podaj numer telefonu klienta w formacie ddd-ddd-ddd: ");
            phone = scanner.nextLine();
            if (!phone.matches("\\d{3}-\\d{3}-\\d{3}")) {
                System.out.println("Numer telefonu musi być w formacie ddd-ddd-ddd. Spróbuj ponownie.");
            } else {
                break;
            }
        }
        return phone;
    }

    public static String getValidEmail(Scanner scanner) {
        String email;
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        while (true) {
            System.out.print("Podaj adres e-mail klienta: ");
            email = scanner.nextLine();
            if (!email.matches(emailRegex)) {
                System.out.println("Adres e-mail jest niepoprawny. Spróbuj ponownie.");
            } else {
                break;
            }
        }
        return email;
    }

    public static String getValidNotEmptyString(Scanner scanner, String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Pole nie może być puste. Spróbuj ponownie.");
            } else {
                break;
            }
        }
        return input;
    }
}
