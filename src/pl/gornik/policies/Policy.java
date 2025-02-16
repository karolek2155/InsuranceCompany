package pl.gornik.policies;

import pl.gornik.Client;

import java.time.LocalDate;

public abstract class Policy {
    protected String policyNumber;
    protected Client client;
    protected double premium;
    protected LocalDate issueDate;

    public Policy(String policyNumber, Client client, double premium, LocalDate issueDate) {
        this.policyNumber = policyNumber;
        this.client = client;
        this.premium = premium;
        this.issueDate = issueDate;
    }

    public abstract double calculatePremium();
}
