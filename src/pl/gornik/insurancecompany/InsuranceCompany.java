package pl.gornik.insurancecompany;

import pl.gornik.insurancecompany.policies.Policy;

import java.util.ArrayList;
import java.util.List;

public class InsuranceCompany {
    private List<Policy> policies = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private List<ClaimReport> claimReports = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();

    public List<ClaimReport> getClaimsByPesel(String pesel) {
        List<ClaimReport> foundClaims = new ArrayList<>();
        for (ClaimReport report : claimReports) {
            if (report.getClient().getPesel().equals(pesel)) {
                foundClaims.add(report);
            }
        }
        return foundClaims;
    }

    public List<ClaimReport> getClaimsByPolicy(String policyNumber) {
        List<ClaimReport> foundClaims = new ArrayList<>();
        for (ClaimReport report : claimReports) {
            if (report.getPolicyNumber().equals(policyNumber)) {
                foundClaims.add(report);
            }
        }
        return foundClaims;
    }

    public List<Client> findClientsByFullName(String firstName, String lastName) {
        List<Client> foundClients = new ArrayList<>();
        for (Client client : clients) {
            if (client.getFirstName().equalsIgnoreCase(firstName) && client.getLastName().equalsIgnoreCase(lastName)) {
                foundClients.add(client);
            }
        }
        return foundClients;
    }

    public Client findClientByPesel(String pesel) {
        for (Client client : clients) {
            if (client.getPesel().equals(pesel)) {
                return client;
            }
        }
        return null;
    }

    public Client findClientByPhoneNumber(String phoneNumber) {
        for (Client client : clients) {
            if (client.getPhoneNumber().equals(phoneNumber)) {
                return client;
            }
        }
        return null;
    }

    public Client findClientByEmail(String email) {
        for (Client client : clients) {
            if (client.getEmail().equalsIgnoreCase(email)) {
                return client;
            }
        }
        return null;
    }

    public void addPolicy(Policy policy) {
        policies.add(policy);
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void addClaimReport(ClaimReport claimReport) {
        claimReports.add(claimReport);
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public List<Policy> getPolicies() {
        return policies;
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<ClaimReport> getClaimReports() {
        return claimReports;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public boolean removeClient(Client client) {
        return clients.remove(client);
    }
}