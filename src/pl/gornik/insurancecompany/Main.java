package pl.gornik.insurancecompany;

import pl.gornik.insurancecompany.enums.AutoInsuranceType;
import pl.gornik.insurancecompany.enums.PaymentMethod;
import pl.gornik.insurancecompany.enums.PropertyInsuranceType;
import pl.gornik.insurancecompany.policies.AutoInsurancePolicy;
import pl.gornik.insurancecompany.policies.LifeInsurancePolicy;
import pl.gornik.insurancecompany.policies.Policy;
import pl.gornik.insurancecompany.policies.PropertyInsurancePolicy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Client> clients = new ArrayList<>();
        List<Policy> policies = new ArrayList<>();
        List<ClaimReport> claimReports = new ArrayList<>();
        List<Payment> payments = new ArrayList<>();

        Client client1 = new Client("Jan", "Kowalski", "Warszawa, Mickiewicza 1");
        Client client2 = new Client("Anna", "Nowak", "Kraków, Słoneczna 15");
        Client client3 = new Client("Paweł", "Wiśniewski", "Gdańsk, Morska 3");
        Client client4 = new Client("Maria", "Wójcik", "Poznań, Pogodna 2");
        Client client5 = new Client("Tomasz", "Zielinski", "Wrocław, Spokojna 7");
        clients.add(client1);
        clients.add(client2);
        clients.add(client3);
        clients.add(client4);
        clients.add(client5);

        Policy policy1 = new AutoInsurancePolicy("A12345", client1, 1000, LocalDate.of(2025, 2, 17), AutoInsuranceType.COMPREHENSIVE);
        Policy policy2 = new PropertyInsurancePolicy("P67890", client2, 800, LocalDate.of(2025, 1, 23), PropertyInsuranceType.HOME);
        Policy policy3 = new LifeInsurancePolicy("L54321", client3, 1200, LocalDate.of(2024, 6, 10), 100000);
        Policy policy4 = new AutoInsurancePolicy("A98765", client4, 900, LocalDate.of(2024, 10, 5), AutoInsuranceType.COLLISION);
        Policy policy5 = new PropertyInsurancePolicy("P11223", client5, 700, LocalDate.of(2024, 5, 25), PropertyInsuranceType.FARM);
        policies.add(policy1);
        policies.add(policy2);
        policies.add(policy3);
        policies.add(policy4);
        policies.add(policy5);

        ClaimReport claimReport1 = new ClaimReport(client1, "Uszkodzenie samochodu w wypadku", LocalDate.of(2024, 1, 16), "A12345");
        ClaimReport claimReport2 = new ClaimReport(client2, "Powódź w mieszkaniu", LocalDate.of(2024, 2, 21), "P67890");
        ClaimReport claimReport3 = new ClaimReport(client3, "Śmierć w wyniku wypadku", LocalDate.of(2024, 3, 12), "L54321");
        ClaimReport claimReport4 = new ClaimReport(client4, "Uszkodzenie samochodu w kolizji", LocalDate.of(2024, 4, 6), "A98765");
        ClaimReport claimReport5 = new ClaimReport(client5, "Pożar w budynku gospodarczym", LocalDate.of(2024, 5, 26), "P11223");
        claimReports.add(claimReport1);
        claimReports.add(claimReport2);
        claimReports.add(claimReport3);
        claimReports.add(claimReport4);
        claimReports.add(claimReport5);

        Payment payment1 = new Payment(1200, PaymentMethod.CASH);
        Payment payment2 = new Payment(880, PaymentMethod.BLIK);
        Payment payment3 = new Payment(1400, PaymentMethod.CARD);
        Payment payment4 = new Payment(950, PaymentMethod.CASH);
        Payment payment5 = new Payment(700, PaymentMethod.BLIK);
        payments.add(payment1);
        payments.add(payment2);
        payments.add(payment3);
        payments.add(payment4);
        payments.add(payment5);

        System.out.println("\nSkładki dla polis:");
        for (Policy policy : policies) {
            policy.updatePremium();
            System.out.printf("Polisa (%s) o numerze %s: %.2f\n", policy.getInsuranceType().getDisplayName(), policy.getPolicyNumber(), policy.getPremium());
        }

        System.out.println("\nRaporty roszczeń:");
        for (ClaimReport report : claimReports) {
            System.out.println(report);
        }

        System.out.println("\nPłatności:");
        for (Payment payment : payments) {
            System.out.println(payment);
        }
    }
}
