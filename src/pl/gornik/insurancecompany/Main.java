package pl.gornik.insurancecompany;

import pl.gornik.insurancecompany.enums.AutoInsuranceType;
import pl.gornik.insurancecompany.enums.InsuranceType;
import pl.gornik.insurancecompany.enums.PaymentMethod;
import pl.gornik.insurancecompany.enums.PropertyInsuranceType;
import pl.gornik.insurancecompany.policies.AutoInsurancePolicy;
import pl.gornik.insurancecompany.policies.LifeInsurancePolicy;
import pl.gornik.insurancecompany.policies.Policy;
import pl.gornik.insurancecompany.policies.PropertyInsurancePolicy;
import pl.gornik.insurancecompany.users.AdminUser;
import pl.gornik.insurancecompany.users.ClientUser;
import pl.gornik.insurancecompany.users.EmployeeUser;
import pl.gornik.insurancecompany.users.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static final InsuranceCompany insuranceCompany = new InsuranceCompany();
    private static final AuthenticationService authService = new AuthenticationService();

    public static void main(String[] args) {
        System.out.println("System zarządzania polisami ubezpieczeniowymi, klientami, zgłoszeniami roszczeń oraz historią polis.");

        Scanner scanner = new Scanner(System.in);
        initializeData();

        System.out.print("Podaj email: ");
        String login = scanner.nextLine();
        System.out.print("Podaj hasło: ");
        String password = scanner.nextLine();

        User user = AuthenticationService.login(login, password);
        if (user != null) {
            System.out.println("Zalogowano: " + user.getEmail() + " jako " + user.getRole());

            boolean isActive = true;

            while (isActive) {
                System.out.println("Co chcesz zrobić? (wpisz numer)");
                System.out.println("1 - Wyszukać klienta");
                System.out.println("2 - Dodać klienta");
                System.out.println("3 - Usunąć klienta");
                System.out.println("4 - Wyszukać roszczenie");
                System.out.println("5 - Złożyć wniosek o ubezpieczenie");
                System.out.println("6 - Wyszukać polisę");
                System.out.println("7 - Wystawić polisę");
                System.out.println("8 - Dokonać płatności");
                System.out.println("9 - Wyświetlić składkę polisy");
                System.out.println("0 - Wyjście");

                int choice = Validation.getValidChoice(scanner, 0, 9);
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

                    case 4 -> {
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

                    case 5 -> {
                        System.out.println("Składanie wniosku o ubezpieczenie...");

                        String pesel = Validation.getValidPesel(scanner);
                        Client client = insuranceCompany.findClientByPesel(pesel);

                        if (client == null) {
                            System.out.println("Nie znaleziono klienta o podanym numerze PESEL.");
                        } else {
                            System.out.print("Podaj opis szkody: ");
                            String description = scanner.nextLine().trim();
                            System.out.print("Podaj numer polisy: ");
                            String policyNumber = scanner.nextLine().trim();
                            LocalDate reportDate = LocalDate.now();

                            ClaimReport claimReport = new ClaimReport(client, description, reportDate, policyNumber);
                            insuranceCompany.addClaimReport(claimReport);

                            System.out.println("Wniosek o ubezpieczenie został pomyślnie złożony.");
                        }
                    }

                    case 6 -> {
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

                    case 7 -> {
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
                                System.out.println("Polisa została pomyślnie wystawiona:");
                                System.out.println(newPolicy);
                            } else {
                                System.out.println("Wystawienie polisy nie powiodło się.");
                            }
                        }
                    }


                    case 8 -> {
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

                    case 9 -> {
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

                    case 0 -> {
                        isActive = false;
                        System.out.println("Zakończono działanie systemu.");
                    }
                }

            }
        } else {
            System.out.println("Niepoprawne dane logowania");
        }

        scanner.close();

    }

    public static void initializeData() {
        insuranceCompany.addClient(new Client("Jan", "Kowalski", "Warszawa, Mickiewicza 1", "12345678901", "123-456-789", "jan.kowalski@gmail.com"));
        insuranceCompany.addClient(new Client("Anna", "Nowak", "Kraków, Słoneczna 15", "98765432101", "234-567-890", "anna.nowak@wp.pl"));
        insuranceCompany.addClient(new Client("Paweł", "Wiśniewski", "Gdańsk, Morska 3", "23456789012", "345-678-901", "pawel.wisniewski@gmail.com"));
        insuranceCompany.addClient(new Client("Maria", "Wójcik", "Poznań, Pogodna 2", "34567890123", "456-789-012", "maria.wojcik@gmail.com"));
        insuranceCompany.addClient(new Client("Tomasz", "Zieliński", "Wrocław, Spokojna 7", "45678901234", "567-890-123", "tomasz.zielinski@gmail.com"));
        insuranceCompany.addClient(new Client("Tomasz", "Zieliński", "Tarnobrzeg, Konopnickiej 21", "98678301238", "444-890-121", "t.zielinski@gmail.com"));

        insuranceCompany.addPolicy(new AutoInsurancePolicy("A12345", insuranceCompany.getClients().get(0), 1000, LocalDate.of(2025, 2, 17), AutoInsuranceType.COMPREHENSIVE));
        insuranceCompany.addPolicy(new PropertyInsurancePolicy("P67890", insuranceCompany.getClients().get(1), 800, LocalDate.of(2025, 1, 23), PropertyInsuranceType.HOME));
        insuranceCompany.addPolicy(new LifeInsurancePolicy("L54321", insuranceCompany.getClients().get(2), 1200, LocalDate.of(2024, 6, 10), 100000));
        insuranceCompany.addPolicy(new AutoInsurancePolicy("A98765", insuranceCompany.getClients().get(3), 900, LocalDate.of(2024, 10, 5), AutoInsuranceType.COLLISION));
        insuranceCompany.addPolicy(new PropertyInsurancePolicy("P11223", insuranceCompany.getClients().get(4), 700, LocalDate.of(2024, 5, 25), PropertyInsuranceType.FARM));

        List<Policy> policies = insuranceCompany.getPolicies();
        insuranceCompany.addClaimReport(new ClaimReport(insuranceCompany.getClients().get(0), "Uszkodzenie samochodu w wypadku", LocalDate.of(2024, 1, 16), policies.get(0).getPolicyNumber()));
        insuranceCompany.addClaimReport(new ClaimReport(insuranceCompany.getClients().get(1), "Powódź w mieszkaniu", LocalDate.of(2024, 2, 21), policies.get(1).getPolicyNumber()));
        insuranceCompany.addClaimReport(new ClaimReport(insuranceCompany.getClients().get(2), "Śmierć w wyniku wypadku", LocalDate.of(2024, 3, 12), policies.get(2).getPolicyNumber()));
        insuranceCompany.addClaimReport(new ClaimReport(insuranceCompany.getClients().get(3), "Uszkodzenie samochodu w kolizji", LocalDate.of(2024, 4, 6), policies.get(3).getPolicyNumber()));
        insuranceCompany.addClaimReport(new ClaimReport(insuranceCompany.getClients().get(4), "Pożar w budynku gospodarczym", LocalDate.of(2024, 5, 26), policies.get(4).getPolicyNumber()));

        insuranceCompany.addPayment(new Payment(1200, PaymentMethod.CASH));
        insuranceCompany.addPayment(new Payment(880, PaymentMethod.BLIK));
        insuranceCompany.addPayment(new Payment(1400, PaymentMethod.CARD));
        insuranceCompany.addPayment(new Payment(950, PaymentMethod.CASH));
        insuranceCompany.addPayment(new Payment(700, PaymentMethod.BLIK));

        authService.registerUser(new ClientUser("jan.kowalski@gmail.com", "pass123"));
        authService.registerUser(new ClientUser("anna.nowak@wp.pl", "maslo123"));
        authService.registerUser(new ClientUser("pawel.wisniewski@gmail.com", "ksd234"));
        authService.registerUser(new ClientUser("maria.wojcik@gmail.com", "kak67jsj"));
        authService.registerUser(new ClientUser("tomasz.zielinski@gmail.com", "zdf775"));
        authService.registerUser(new EmployeeUser("janek.kowalski@wp.pl", "emp456"));
        authService.registerUser(new AdminUser("admin@gmail.com", "admin789"));
    }
}
