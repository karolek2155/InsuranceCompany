package pl.gornik.insurancecompany.policies;

import pl.gornik.insurancecompany.Client;
import pl.gornik.insurancecompany.enums.InsuranceType;

import java.time.LocalDate;

public abstract class Policy {
    protected String policyNumber;
    protected Client client;
    protected double premium;
    protected LocalDate issueDate;
    protected InsuranceType insuranceType;

    public Policy(String policyNumber, Client client, double premium, LocalDate issueDate) {
        this.policyNumber = policyNumber;
        this.client = client;
        this.premium = premium;
        this.issueDate = issueDate;
    }

    public InsuranceType getInsuranceType() {
        return insuranceType;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public double getPremium() {
        return premium;
    }

    public abstract void updatePremium();
}
