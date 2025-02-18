package pl.gornik.insurancecompany;

import pl.gornik.insurancecompany.policies.Policy;

import java.util.ArrayList;
import java.util.List;

public class InsuranceCompany {
    private List<Policy> policies = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private List<ClaimReport> claimReports = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();

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
}
