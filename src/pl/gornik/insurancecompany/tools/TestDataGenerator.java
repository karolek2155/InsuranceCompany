package pl.gornik.insurancecompany.tools;

import pl.gornik.insurancecompany.model.enums.AutoInsuranceType;
import pl.gornik.insurancecompany.model.enums.ClaimStatus;
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
        insuranceCompany.addClient(new Client("Katarzyna", "Lewandowska", "Łódź, Kwiatowa 9", "56789012345", "678-901-234", "katarzyna.lewandowska@gmail.com"));
        insuranceCompany.addClient(new Client("Marek", "Dąbrowski", "Szczecin, Jasna 5", "67890123456", "789-012-345", "marek.dabrowski@gmail.com"));
        insuranceCompany.addClient(new Client("Monika", "Mazur", "Lublin, Lipowa 3", "78901234567", "890-123-456", "monika.mazur@gmail.com"));
        insuranceCompany.addClient(new Client("Robert", "Kaczmarek", "Bydgoszcz, Długa 10", "89012345678", "901-234-567", "robert.kaczmarek@gmail.com"));
        insuranceCompany.addClient(new Client("Ewa", "Pawlak", "Rzeszów, Zielona 12", "90123456789", "012-345-678", "ewa.pawlak@gmail.com"));
        insuranceCompany.addClient(new Client("Jacek", "Sikora", "Opole, Miodowa 7", "11223344556", "123-987-654", "jacek.sikora@gmail.com"));
        insuranceCompany.addClient(new Client("Agnieszka", "Król", "Kielce, Sosnowa 15", "22334455667", "234-876-543", "agnieszka.krol@gmail.com"));
        insuranceCompany.addClient(new Client("Karol", "Górski", "Toruń, Orla 8", "33445566778", "345-765-432", "karol.gorski@gmail.com"));
        insuranceCompany.addClient(new Client("Dorota", "Lis", "Radom, Wiosenna 4", "44556677889", "456-654-321", "dorota.lis@gmail.com"));
        insuranceCompany.addClient(new Client("Grzegorz", "Czajka", "Gdynia, Wodna 9", "55667788990", "567-543-210", "grzegorz.czajka@gmail.com"));
        insuranceCompany.addClient(new Client("Beata", "Sadowska", "Koszalin, Polna 11", "66778899001", "678-432-109", "beata.sadowska@gmail.com"));
        insuranceCompany.addClient(new Client("Łukasz", "Wrona", "Elbląg, Chmielna 2", "77889900112", "789-321-098", "lukasz.wrona@gmail.com"));
        insuranceCompany.addClient(new Client("Natalia", "Stępień", "Częstochowa, Leśna 6", "88990011223", "890-210-987", "natalia.stepien@gmail.com"));
        insuranceCompany.addClient(new Client("Wojciech", "Kołodziej", "Zielona Góra, Różana 14", "99001122334", "901-109-876", "wojciech.kolodziej@gmail.com"));

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
        authService.registerUser(new EmployeeUser("marcin.nowak@wp.pl", "emp656"));
        authService.registerUser(new EmployeeUser("zuzanna.chwalek@wp.pl", "emp478"));
        authService.registerUser(new AdminUser("admin@gmail.com", "admin789"));

        AutoInsuranceType[] autoTypes = AutoInsuranceType.values();
        PropertyInsuranceType[] propertyTypes = PropertyInsuranceType.values();
        PaymentMethod[] paymentMethods = PaymentMethod.values();

        for (int i = 5; i < insuranceCompany.getClients().size(); i++) {
            if (i % 2 == 0) {
                insuranceCompany.addPolicy(new AutoInsurancePolicy("A" + (10000 + i), insuranceCompany.getClients().get(i), 800 + (i * 75), LocalDate.of(2025, (i % 12) + 1, (i % 28) + 1), autoTypes[i % autoTypes.length]));
            } else {
                insuranceCompany.addPolicy(new PropertyInsurancePolicy("P" + (20000 + i), insuranceCompany.getClients().get(i), 900 + (i * 60), LocalDate.of(2025, (i % 12) + 1, (i % 28) + 1), propertyTypes[i % propertyTypes.length]));
            }

            insuranceCompany.addClaimReport(new ClaimReport(insuranceCompany.getClients().get(i), "Zgłoszenie szkody nr " + i, LocalDate.of(2024, (i % 12) + 1, (i % 28) + 1), (i % 2 == 0 ? "A" : "P") + (10000 + i)));
            if (i % 2 == 0) insuranceCompany.getClaimReports().get(i).setStatus(ClaimStatus.ACCEPTED);
            else insuranceCompany.getClaimReports().get(i).setStatus(ClaimStatus.REJECTED);

            insuranceCompany.addPayment(new Payment(900 + (i * 55), paymentMethods[i % paymentMethods.length]));
            authService.registerUser(new ClientUser(insuranceCompany.getClients().get(i).getEmail(), "haslo" + i));
        }
    }
}
