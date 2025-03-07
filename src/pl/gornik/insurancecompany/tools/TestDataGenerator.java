package pl.gornik.insurancecompany.tools;

import pl.gornik.insurancecompany.model.enums.AutoInsuranceType;
import pl.gornik.insurancecompany.model.enums.PaymentMethod;
import pl.gornik.insurancecompany.model.enums.PropertyInsuranceType;
import pl.gornik.insurancecompany.model.policies.AutoInsurancePolicy;
import pl.gornik.insurancecompany.model.policies.LifeInsurancePolicy;
import pl.gornik.insurancecompany.model.policies.Policy;
import pl.gornik.insurancecompany.model.policies.PropertyInsurancePolicy;
import pl.gornik.insurancecompany.model.users.AdminUser;
import pl.gornik.insurancecompany.model.users.ClientUser;
import pl.gornik.insurancecompany.model.users.EmployeeUser;
import pl.gornik.insurancecompany.service.*;

import java.time.LocalDate;
import java.util.List;

public class TestDataGenerator {
    public static void initializeData(InsuranceCompany insuranceCompany, AuthenticationService authService) {
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
