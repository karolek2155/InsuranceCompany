package pl.gornik.insurancecompany.policies;

import pl.gornik.insurancecompany.Client;
import pl.gornik.insurancecompany.enums.InsuranceType;

import java.time.LocalDate;

public class LifeInsurancePolicy extends Policy{
    private double insuredAmount;

    public LifeInsurancePolicy(String policyNumber, Client client, double premium, LocalDate issueDate, double insuredAmount) {
        super(policyNumber, client, premium, issueDate);
        this.insuredAmount = insuredAmount;
        this.insuranceType = InsuranceType.LIFE;
    }

    @Override
    public void updatePremium() {
        premium = premium + insuredAmount * 0.02;
    }

    @Override
    public String toString() {
        return "LifeInsurancePolicy{" +
                "policyNumber='" + policyNumber + '\'' +
                ", client=" + client +
                ", premium=" + premium +
                ", issueDate=" + issueDate +
                ", insuranceType=" + insuranceType +
                ", insuredAmount=" + insuredAmount +
                '}';
    }
}
