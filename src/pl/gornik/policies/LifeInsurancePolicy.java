package pl.gornik.policies;

import pl.gornik.Client;

import java.time.LocalDate;

public class LifeInsurancePolicy extends Policy{
    private double insuredAmount;

    public LifeInsurancePolicy(String policyNumber, Client client, double premium, LocalDate issueDate, double insuredAmount) {
        super(policyNumber, client, premium, issueDate);
        this.insuredAmount = insuredAmount;
    }

    @Override
    public double calculatePremium() {
        return premium + insuredAmount * 0.02;
    }
}
