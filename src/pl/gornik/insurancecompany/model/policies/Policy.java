package pl.gornik.insurancecompany.model.policies;

import pl.gornik.insurancecompany.service.Client;
import pl.gornik.insurancecompany.model.enums.InsuranceType;

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

    public static String generatePolicyNumber(InsuranceType insuranceType) {
        String prefix = switch (insuranceType) {
            case LIFE -> "L";
            case AUTO -> "A";
            case PROPERTY -> "P";
        };
        int randomNumber = (int) (Math.random() * 90000 + 10000);
        return prefix + randomNumber;
    }


    public Client getClient() {
        return client;
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
