package pl.gornik.insurancecompany.service;

import pl.gornik.insurancecompany.model.enums.*;
import pl.gornik.insurancecompany.model.policies.AutoInsurancePolicy;
import pl.gornik.insurancecompany.model.policies.LifeInsurancePolicy;
import pl.gornik.insurancecompany.model.policies.Policy;
import pl.gornik.insurancecompany.model.policies.PropertyInsurancePolicy;
import pl.gornik.insurancecompany.model.users.AdminUser;
import pl.gornik.insurancecompany.model.users.ClientUser;
import pl.gornik.insurancecompany.model.users.EmployeeUser;
import pl.gornik.insurancecompany.model.users.User;
import pl.gornik.insurancecompany.tools.Validation;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InsuranceManagement {
    public static void manageUsers(Scanner scanner, AuthenticationService authService) {
            System.out.println("Zarządzanie użytkownikami...");
            System.out.println("1. Wyświetl wszystkich użytkowników");
            System.out.println("2. Dodaj użytkownika");
            System.out.println("3. Usuń użytkownika");

            int choice = Validation.getValidChoice(scanner, 1, 3);
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    Map<String, User> users = authService.getUsers();
                    if (users.isEmpty()) {
                        System.out.println("Brak użytkowników.");
                    } else {
                        System.out.println("Lista użytkowników:");
                        users.forEach((email, user) ->
                                System.out.println("- " + email + " (" + user.getClass().getSimpleName() + ")")
                        );
                    }
                }
                case 2 -> {
                    System.out.print("Podaj email: ");
                    String email = scanner.nextLine();
                    System.out.print("Podaj hasło: ");
                    String password = scanner.nextLine();

                    System.out.println("Wybierz rolę użytkownika:");
                    System.out.println("1. Klient");
                    System.out.println("2. Pracownik");
                    System.out.println("3. Administrator");
                    System.out.print("Twój wybór: ");
                    int roleChoice = Validation.getValidChoice(scanner, 1, 3);
                    scanner.nextLine();

                    User newUser = switch (roleChoice) {
                        case 1 -> new ClientUser(email, password);
                        case 2 -> new EmployeeUser(email, password);
                        case 3 -> new AdminUser(email, password);
                        default -> throw new IllegalStateException("Niepoprawny wybór roli.");
                    };

                    authService.registerUser(newUser);
                    System.out.println("Użytkownik dodany.");
                }
                case 3 -> {
                    System.out.print("Podaj email użytkownika do usunięcia: ");
                    String removeEmail = scanner.nextLine();
                    if (authService.removeUser(removeEmail)) {
                        System.out.println("Użytkownik usunięty.");
                    } else {
                        System.out.println("Użytkownik nie istnieje.");
                    }
                }
                default -> System.out.println("Niepoprawny wybór.");
            }
    }

    public static void manageClients(Scanner scanner, InsuranceCompany insuranceCompany) {
        System.out.println("Zarządzanie klientami...");
        System.out.println("1 - Wyszukać klienta");
        System.out.println("2 - Dodać klienta");
        System.out.println("3 - Usunąć klienta");
        int choice = Validation.getValidChoice(scanner, 1, 3);
        scanner.nextLine();

        switch (choice) {
            case 1 -> {
                System.out.println("Wyszukiwanie klienta...");
                System.out.println("1 - Wyszukać po imieniu i nazwisku");
                System.out.println("2 - Wyszukać po numerze PESEL");
                System.out.println("3 - Wyszukać po numerze telefonu");
                System.out.println("4 - Wyszukać po adresie e-mail");
                System.out.println("5 - Wyświetlić wszystkich klientów");
                int caseChoice = Validation.getValidChoice(scanner, 1, 5);
                scanner.nextLine();
                System.out.println();

                switch (caseChoice) {
                    case 1 -> {
                        String firstName = Validation.getValidNotEmptyString(scanner, "Podaj imię klienta: ");
                        String lastName = Validation.getValidNotEmptyString(scanner, "Podaj nazwisko klienta: ");
                        List<Client> foundClients = insuranceCompany.findClientsByFullName(firstName, lastName);

                        if (foundClients.isEmpty()) {
                            System.out.println("Nie znaleziono klienta o podanym imieniu i nazwisku.");
                        } else if (foundClients.size() == 1) {
                            System.out.println("Znaleziono klienta:");
                            foundClients.getFirst().presentClient();
                        } else {
                            System.out.println("Znaleziono następujących klientów:");
                            for (Client client : foundClients) {
                                client.presentClient();
                            }
                        }
                    }
                    case 2 -> {
                        String pesel = Validation.getValidPesel(scanner);
                        Client foundClient = insuranceCompany.findClientByPesel(pesel);

                        if (foundClient == null) {
                            System.out.println("Nie znaleziono klienta o podanym numerze PESEL.");
                        } else {
                            System.out.println("Znaleziono klienta:");
                            foundClient.presentClient();
                        }
                    }
                    case 3 -> {
                        String phone = Validation.getValidPhone(scanner);
                        Client foundClient = insuranceCompany.findClientByPhoneNumber(phone);

                        if (foundClient == null) {
                            System.out.println("Nie znaleziono klienta o podanym numerze telefonu.");
                        } else {
                            System.out.println("Znaleziono klienta:");
                            foundClient.presentClient();
                        }
                    }
                    case 4 -> {
                        String email = Validation.getValidEmail(scanner);
                        Client foundClient = insuranceCompany.findClientByEmail(email);

                        if (foundClient == null) {
                            System.out.println("Nie znaleziono klienta o podanym adresie e-mail.");
                        } else {
                            System.out.println("Znaleziono klienta:");
                            foundClient.presentClient();
                        }
                    }
                    case 5 -> {
                        System.out.println("Wszyscy klienci:");
                        for (Client client : insuranceCompany.getClients()) {
                            client.presentClient();
                        }
                    }
                    default -> System.out.println("Niepoprawny wybór.");
                }
            }
            case 2 -> {
                System.out.println("Dodawanie nowego klienta...");

                String firstName = Validation.getValidNotEmptyString(scanner, "Podaj imię klienta: ");
                String lastName = Validation.getValidNotEmptyString(scanner, "Podaj nazwisko klienta: ");
                String pesel = Validation.getValidPesel(scanner);
                String phone = Validation.getValidPhone(scanner);
                String email = Validation.getValidEmail(scanner);
                String address = Validation.getValidNotEmptyString(scanner, "Podaj adres zamieszkania klienta: ");

                insuranceCompany.addClient(new Client(firstName, lastName, address, pesel, phone, email));
                System.out.println("Nowy klient został pomyślnie dodany:");
                insuranceCompany.getClients().getLast().presentClient();
            }
            case 3 -> {
                System.out.println("Usuwanie klienta...");

                String pesel = Validation.getValidPesel(scanner);
                Client clientToRemove = insuranceCompany.findClientByPesel(pesel);

                if (clientToRemove == null) {
                    System.out.println("Nie znaleziono klienta o podanym numerze PESEL.");
                } else {
                    System.out.println("Znaleziono klienta:");
                    clientToRemove.presentClient();
                    System.out.print("Czy na pewno chcesz usunąć tego klienta? (tak/nie): ");
                    String confirmation = scanner.nextLine().trim().toLowerCase();

                    if (confirmation.equals("tak")) {
                        boolean removed = insuranceCompany.removeClient(clientToRemove);
                        if (removed) {
                            System.out.println("Klient został pomyślnie usunięty.");
                        } else {
                            System.out.println("Nie udało się usunąć klienta.");
                        }
                    } else {
                        System.out.println("Anulowano usunięcie klienta.");
                    }
                }
            }
        }
    }

    public static void viewClaims(Scanner scanner, InsuranceCompany insuranceCompany) {
        System.out.println("Wyszukiwanie roszczeń...");

        System.out.println("Wybierz kryterium wyszukiwania:");
        System.out.println("1 - Wszystkie roszczenia");
        System.out.println("2 - Roszczenia według numeru PESEL klienta");
        System.out.println("3 - Roszczenia według numeru polisy");

        int caseChoice = Validation.getValidChoice(scanner, 1, 3);

        switch (caseChoice) {
            case 1 -> {
                System.out.println("\nRaporty roszczeń:");
                List<ClaimReport> allReports = insuranceCompany.getClaimReports();
                if (allReports.isEmpty()) {
                    System.out.println("Brak zgłoszonych roszczeń.");
                } else {
                    for (ClaimReport report : allReports) {
                        System.out.println(report);
                    }
                }
            }
            case 2 -> {
                String pesel = Validation.getValidPesel(scanner);
                List<ClaimReport> reportsByPesel = insuranceCompany.getClaimsByPesel(pesel);
                if (reportsByPesel.isEmpty()) {
                    System.out.println("Brak zgłoszonych roszczeń dla podanego PESEL.");
                } else {
                    System.out.println("\nRaporty roszczeń dla PESEL " + pesel + ":");
                    for (ClaimReport report : reportsByPesel) {
                        System.out.println(report);
                    }
                }
            }
            case 3 -> {
                String policyNumber = Validation.getValidNotEmptyString(scanner, "Podaj numer polisy: ");
                List<ClaimReport> reportsByPolicy = insuranceCompany.getClaimsByPolicy(policyNumber);
                if (reportsByPolicy.isEmpty()) {
                    System.out.println("Brak zgłoszonych roszczeń dla podanej polisy.");
                } else {
                    System.out.println("\nRaporty roszczeń dla polisy " + policyNumber + ":");
                    for (ClaimReport report : reportsByPolicy) {
                        System.out.println(report);
                    }
                }
            }
        }
    }

    public static void managePolicies(Scanner scanner, InsuranceCompany insuranceCompany) {
        System.out.println("Wyszukiwanie polisy...");

        System.out.println("Wybierz kryterium wyszukiwania:");
        System.out.println("1 - Wszystkie polisy");
        System.out.println("2 - Polisy według numeru PESEL klienta");
        System.out.println("3 - Polisa według numeru polisy");

        int caseChoice = Validation.getValidChoice(scanner, 1, 3);

        switch (caseChoice) {
            case 1 -> {
                System.out.println("\nLista wszystkich polis:");
                List<Policy> allPolicies = insuranceCompany.getPolicies();
                if (allPolicies.isEmpty()) {
                    System.out.println("Brak dostępnych polis.");
                } else {
                    for (Policy policy : allPolicies) {
                        System.out.println(policy);
                    }
                }
            }
            case 2 -> {
                String pesel = Validation.getValidPesel(scanner);
                List<Policy> policiesByPesel = insuranceCompany.getPoliciesByPesel(pesel);
                if (policiesByPesel.isEmpty()) {
                    System.out.println("Brak polis powiązanych z podanym numerem PESEL.");
                } else {
                    System.out.println("\nPolisy dla PESEL " + pesel + ":");
                    for (Policy policy : policiesByPesel) {
                        System.out.println(policy);
                    }
                }
            }
            case 3 -> {
                String policyNumber = Validation.getValidNotEmptyString(scanner, "Podaj numer polisy: ");
                Policy policy = insuranceCompany.getPolicyByNumber(policyNumber);
                if (policy == null) {
                    System.out.println("Nie znaleziono polisy o podanym numerze.");
                } else {
                    System.out.println("\nZnaleziono polisę:");
                    System.out.println(policy);
                }
            }
        }
    }


    public static void issuePolicy(Scanner scanner, InsuranceCompany insuranceCompany) {
        System.out.println("Wystawianie polisy...");
        System.out.println("Wystawianie nowej polisy...");

        String pesel = Validation.getValidPesel(scanner);
        Client client = insuranceCompany.findClientByPesel(pesel);

        if (client == null) {
            System.out.println("Nie znaleziono klienta o podanym PESEL.");
        } else {
            System.out.println("Wybierz rodzaj polisy:");
            System.out.println("1 - Ubezpieczenie na życie");
            System.out.println("2 - Ubezpieczenie samochodu");
            System.out.println("3 - Ubezpieczenie nieruchomości");
            int policyTypeChoice = Validation.getValidChoice(scanner, 1, 3);
            scanner.nextLine();

            LocalDate issueDate = LocalDate.now();
            double premiumAmount = Validation.getValidDouble(scanner, "Podaj składkę ubezpieczeniową: ");

            Policy newPolicy = null;

            switch (policyTypeChoice) {
                case 1 -> {
                    String policyNumber = Policy.generatePolicyNumber(InsuranceType.LIFE);
                    double insuredAmount = Validation.getValidDouble(scanner, "Podaj sumę ubezpieczenia: ");
                    newPolicy = new LifeInsurancePolicy(policyNumber, client, premiumAmount, issueDate, insuredAmount);
                }
                case 2 -> {
                    String policyNumber = Policy.generatePolicyNumber(InsuranceType.AUTO);
                    AutoInsuranceType autoType = Validation.getValidEnum(scanner, AutoInsuranceType.class, "Podaj typ ubezpieczenia samochodu: ");
                    newPolicy = new AutoInsurancePolicy(policyNumber, client, premiumAmount, issueDate, autoType);
                }
                case 3 -> {
                    String policyNumber = Policy.generatePolicyNumber(InsuranceType.PROPERTY);
                    PropertyInsuranceType propertyType = Validation.getValidEnum(scanner, PropertyInsuranceType.class, "Podaj typ ubezpieczenia nieruchomości: ");
                    newPolicy = new PropertyInsurancePolicy(policyNumber, client, premiumAmount, issueDate, propertyType);
                }
            }

            if (newPolicy != null) {
                insuranceCompany.addPolicy(newPolicy);
                System.out.println("Polisa została pomyślnie wystawiona o numerze " + newPolicy.getPolicyNumber() + ":");
                System.out.println(newPolicy);
            } else {
                System.out.println("Wystawienie polisy nie powiodło się.");
            }
        }    }

    public static void processPayment(Scanner scanner, InsuranceCompany insuranceCompany) {
        System.out.println("Dokonywanie płatności...");
        System.out.println("Podaj numer polisy do opłacenia:");
        String policyNumber = scanner.next();
        Policy policy = insuranceCompany.getPolicyByNumber(policyNumber);
        if (policy != null) {
            System.out.println("Kwota do zapłaty: " + policy.getPremium());
            PaymentMethod paymentMethod = Validation.getValidEnum(scanner, PaymentMethod.class, "Podaj sposób dokonania płatności: ");
            System.out.println("Podaj kwotę płatności:");
            double amount = scanner.nextDouble();
            insuranceCompany.processPayment(policy, amount, paymentMethod);
        } else {
            System.out.println("Nie znaleziono polisy o podanym numerze.");
        }
    }

    public static void calculatePremiums(Scanner scanner, InsuranceCompany insuranceCompany) {
        System.out.println("Obliczanie składek...");

        System.out.println("Wybierz opcję:");
        System.out.println("1 - Obliczyć składki dla wszystkich polis");
        System.out.println("2 - Obliczyć składkę dla konkretnej polisy");

        int caseChoice = Validation.getValidChoice(scanner, 1, 2);
        scanner.nextLine();

        if (caseChoice == 1) {
            System.out.println("\nSkładki dla polis:");
            for (Policy policy : insuranceCompany.getPolicies()) {
                policy.updatePremium();
                System.out.printf("Polisa (%s) o numerze %s: %.2f\n", policy.getInsuranceType().getDisplayName(), policy.getPolicyNumber(), policy.getPremium());
            }
        } else if (caseChoice == 2) {
            String policyNumber = Validation.getValidNotEmptyString(scanner, "Podaj numer polisy: ");
            Policy policy = insuranceCompany.getPolicyByNumber(policyNumber);

            if (policy != null) {
                policy.updatePremium();
                System.out.printf("Składka dla polisy (%s) o numerze %s: %.2f\n", policy.getInsuranceType().getDisplayName(), policy.getPolicyNumber(), policy.getPremium());
            } else {
                System.out.println("Nie znaleziono polisy o podanym numerze.");
            }
        }
    }

    public static void viewClientPolicies(Scanner scanner, Client client, InsuranceCompany insuranceCompany) {
        System.out.println("Wyszukiwanie polisy...");

        System.out.println("Wybierz kryterium wyszukiwania:");
        System.out.println("1 - Wszystkie Twoje polisy");
        System.out.println("2 - Polisa według numeru polisy");

        int caseChoice = Validation.getValidChoice(scanner, 1, 2);

        switch (caseChoice) {
            case 1 -> {
                System.out.println("\nLista Twoich polis:");
                List<Policy> userPolicies = insuranceCompany.getPoliciesByPesel(client.getPesel());
                if (userPolicies.isEmpty()) {
                    System.out.println("Nie posiadasz żadnych polis.");
                } else {
                    for (Policy policy : userPolicies) {
                        policy.updateStatus();
                        System.out.println(policy);
                    }
                }
            }
            case 2 -> {
                String policyNumber = Validation.getValidNotEmptyString(scanner, "Podaj numer polisy: ");
                Policy policy = insuranceCompany.getPolicyByNumber(policyNumber);
                if (policy == null || !policy.getClient().getPesel().equals(client.getPesel())) {
                    System.out.println("Nie znaleziono Twojej polisy o podanym numerze.");
                } else {
                    policy.updateStatus();
                    System.out.println("\nZnaleziono Twoją polisę:");
                    System.out.println(policy);
                }
            }
        }
    }


    public static void submitClaim(Scanner scanner, Client client, InsuranceCompany insuranceCompany) {
        System.out.println("Przetwarzanie roszczenia...");

        System.out.print("Podaj opis szkody: ");
        String description = scanner.nextLine().trim();
        System.out.print("Podaj numer polisy: ");
        String policyNumber = scanner.nextLine().trim();
        Policy policy = insuranceCompany.getPolicyByNumber(policyNumber);
        if (policy == null) {
            System.out.println("Nie znaleziono polisy o podanym numerze.");
        } else {
            LocalDate reportDate = LocalDate.now();
            ClaimReport claimReport = new ClaimReport(client, description, reportDate, policyNumber);
            claimReport.setStatus(ClaimStatus.UNDER_REVIEW);
            insuranceCompany.addClaimReport(claimReport);
            System.out.println("Wniosek o ubezpieczenie o numerze" + claimReport.getClaimNumber() + "został pomyślnie złożony i oczekuje na rozpatrzenie.");
        }
    }

    public static void processClaim(Scanner scanner, InsuranceCompany insuranceCompany) {
        System.out.print("Podaj numer roszczenia do rozpatrzenia: ");
        String claimNumber = scanner.nextLine().trim();
        ClaimReport claimReport = (ClaimReport) insuranceCompany.getClaimsByPolicy(claimNumber);
        if (claimReport == null) {
            System.out.println("Nie znaleziono roszczenia o podanym numerze.");
        } else {
            System.out.println("Szczegóły roszczenia:");
            System.out.println("Opis: " + claimReport.getDescription());
            System.out.println("Data zgłoszenia: " + claimReport.getReportDate());
            System.out.println("Status: " + claimReport.getStatus());

            if (claimReport.getStatus() == ClaimStatus.UNDER_REVIEW) {
                System.out.print("Czy chcesz zaakceptować (A) czy odrzucić (R) to roszczenie? ");
                String decision = scanner.nextLine().trim().toUpperCase();
                if (decision.equals("A")) {
                    claimReport.setStatus(ClaimStatus.ACCEPTED);
                    System.out.println("Roszczenie zostało zaakceptowane.");
                } else if (decision.equals("R")) {
                    claimReport.setStatus(ClaimStatus.REJECTED);
                    System.out.println("Roszczenie zostało odrzucone.");
                } else {
                    System.out.println("Nieprawidłowa decyzja. Roszczenie pozostaje w statusie 'W trakcie rozpatrywania'.");
                }
            } else {
                System.out.println("To roszczenie zostało już rozpatrzone.");
            }
        }
    }

}
