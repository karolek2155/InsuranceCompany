package pl.gornik.insurancecompany;

import pl.gornik.insurancecompany.enums.AutoInsuranceType;
import pl.gornik.insurancecompany.enums.PaymentMethod;
import pl.gornik.insurancecompany.enums.PropertyInsuranceType;
import pl.gornik.insurancecompany.policies.AutoInsurancePolicy;
import pl.gornik.insurancecompany.policies.LifeInsurancePolicy;
import pl.gornik.insurancecompany.policies.Policy;
import pl.gornik.insurancecompany.policies.PropertyInsurancePolicy;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static InsuranceCompany insuranceCompany = new InsuranceCompany();

    public static void main(String[] args) {
        System.out.println("System zarządzania polisami ubezpieczeniowymi, klientami, zgłoszeniami roszczeń oraz historią polis.");

        Scanner scanner = new Scanner(System.in);
        initializeData();
        boolean isActive = true;

        while (isActive) {
            System.out.println("Co chcesz zrobić? (wpisz numer)");
            System.out.println("1 - Wyszukać klienta");
            System.out.println("2 - Dodać klienta");
            System.out.println("3 - Usunąć klienta");
            System.out.println("4 - Złożyć wniosek o ubezpieczenie");
            System.out.println("5 - Wyszukać roszczenie");
            System.out.println("6 - Wyszukać polisę");
            System.out.println("7 - Wystawić polisę");
            System.out.println("8 - Wyświetlić płatności");
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
                }
                case 4 -> {
                    System.out.println("Składanie wniosku o ubezpieczenie...");

//                    insuranceCompany.addClaimReport(new ClaimReport(client, description, reportDate, policyNumber));
                }
                case 5 -> {
                    System.out.println("Wyszukiwanie roszczeń...");
                    System.out.println("\nRaporty roszczeń:");
                    for (ClaimReport report : insuranceCompany.getClaimReports()) {
                        System.out.println(report);
                    }
                }
                case 6 -> {
                    System.out.println("Wyszukiwanie polisy...");
                }
                case 7 -> {
                    System.out.println("Wystawianie nowej polisy...");
                }
                case 8 -> {
                    System.out.println("Wyszukiwanie płatności...");
                    System.out.println("\nPłatności:");
                    for (Payment payment : insuranceCompany.getPayments()) {
                        System.out.println(payment);
                    }
                }
                case 9 -> {
                    System.out.println("Obliczanie składek...");
                    System.out.println("\nSkładki dla polis:");
                    for (Policy policy : insuranceCompany.getPolicies()) {
                        policy.updatePremium();
                        System.out.printf("Polisa (%s) o numerze %s: %.2f\n", policy.getInsuranceType().getDisplayName(), policy.getPolicyNumber(), policy.getPremium());
                    }
                }
                case 0 -> {
                    isActive = false;
                    System.out.println("Zakończono działanie systemu.");
                }
                default -> System.out.println("Niepoprawny wybór, spróbuj ponownie.");
            }

        }
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
    }
}
