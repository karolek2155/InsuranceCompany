package pl.gornik.insurancecompany;

import pl.gornik.insurancecompany.policies.Policy;

import java.util.ArrayList;
import java.util.List;

public class InsuranceCompany {
    private List<Policy> policies = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();

    public void addPolicy(Policy policy) {
        policies.add(policy);
    }

    public void addClient(Client client) {
        clients.add(client);
    }
}
