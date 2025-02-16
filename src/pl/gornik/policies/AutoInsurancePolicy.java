package pl.gornik.policies;

import pl.gornik.Client;

import java.time.LocalDate;

public class AutoInsurancePolicy extends Policy{
    private final AutoInsuranceType type;

    public AutoInsurancePolicy(String policyNumber, Client client, double premium, LocalDate issueDate, AutoInsuranceType type) {
        super(policyNumber, client, premium, issueDate);
        this.type = type;
    }

    @Override
    public double calculatePremium() {
        if (type == AutoInsuranceType.COMPREHENSIVE) {
            return premium * 1.2;
        } else {
            return premium;
        }
    }
}
