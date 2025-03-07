package pl.gornik.insurancecompany;

import pl.gornik.insurancecompany.service.*;
import pl.gornik.insurancecompany.tools.TestDataGenerator;
import pl.gornik.insurancecompany.tools.Validation;
import pl.gornik.insurancecompany.model.users.*;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        System.out.println("System zarządzania polisami ubezpieczeniowymi, klientami, zgłoszeniami roszczeń oraz historią polis.");

        InsuranceCompany insuranceCompany = new InsuranceCompany();
        AuthenticationService authService = new AuthenticationService();
        Scanner scanner = new Scanner(System.in);
        TestDataGenerator.initializeData(insuranceCompany, authService);
        Client client = null;
        boolean running = true;

        while (running) {
        System.out.print("Podaj email: ");
        String login = scanner.nextLine();
        System.out.print("Podaj hasło: ");
        String password = scanner.nextLine();

        User user = AuthenticationService.login(login, password);
        if (user instanceof ClientUser) {
            client = insuranceCompany.findClientByEmail(login);
        }

        if (user != null) {
            System.out.println("Zalogowano: " + user.getEmail() + " jako " + user.getRole());

            boolean isActive = true;

            while (isActive) {
                System.out.println("\nWybierz opcję:");
                if (user instanceof AdminUser) {
                    System.out.println("1 - Zarządzanie użytkownikami");
                    System.out.println("2 - Zarządzanie klientami");
                    System.out.println("3 - Przeglądanie zgłoszeń roszczeń");
                }
                if (user instanceof EmployeeUser) {
                    System.out.println("4 - Wyszukiwanie polis");
                    System.out.println("5 - Obsługa roszczeń");
                    System.out.println("6 - Wystawianie polis");
                    System.out.println("7 - Dokonywanie płatności");
                    System.out.println("8 - Obliczanie składek");
                }
                if (user instanceof ClientUser) {
                    System.out.println("9 - Podgląd moich polis");
                    System.out.println("10 - Składanie wniosków o roszczenie");
                }
                System.out.println("0 - Wyloguj");

                int choice = Validation.getValidChoice(scanner, 0, 10);
                scanner.nextLine();

                switch (choice) {
                    case 1 -> { if (user instanceof AdminUser) InsuranceManagement.manageUsers(scanner, authService); }
                    case 2 -> { if (user instanceof AdminUser) InsuranceManagement.manageClients(scanner, insuranceCompany); }
                    case 3 -> { if (user instanceof AdminUser) InsuranceManagement.viewClaims(scanner, insuranceCompany); }
                    case 4 -> { if (user instanceof EmployeeUser) InsuranceManagement.managePolicies(scanner, insuranceCompany); }
                    case 5 -> { if (user instanceof EmployeeUser) InsuranceManagement.processClaim(scanner, insuranceCompany); }
                    case 6 -> { if (user instanceof EmployeeUser) InsuranceManagement.issuePolicy(scanner, insuranceCompany); }
                    case 7 -> { if (user instanceof EmployeeUser) InsuranceManagement.processPayment(scanner, insuranceCompany); }
                    case 8 -> { if (user instanceof EmployeeUser) InsuranceManagement.calculatePremiums(scanner, insuranceCompany); }
                    case 9 -> { if (user instanceof ClientUser) InsuranceManagement.viewClientPolicies(scanner, client, insuranceCompany); }
                    case 10 -> { if (user instanceof ClientUser) InsuranceManagement.submitClaim(scanner, client, insuranceCompany); }
                    case 0 -> {
                        isActive = false;
                        System.out.println("Wylogowano");
                    }
                    default -> System.out.println("Niepoprawny wybór.");
                }
            }
        } else {
            System.out.println("Niepoprawne dane logowania");
        }

            System.out.println("Czy chcesz zalogować się ponownie? (T/N)");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equalsIgnoreCase("T")) {
                running = false;
            }
        }

        System.out.println("Zakończono działanie systemu.");
        scanner.close();
    }
}
