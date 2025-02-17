package pl.gornik.insurancecompany.policies;

import pl.gornik.insurancecompany.Client;
import pl.gornik.insurancecompany.enums.AutoInsuranceType;
import pl.gornik.insurancecompany.enums.InsuranceType;

import java.time.LocalDate;

public class AutoInsurancePolicy extends Policy{
    private final AutoInsuranceType type;

    public AutoInsurancePolicy(String policyNumber, Client client, double premium, LocalDate issueDate, AutoInsuranceType type) {
        super(policyNumber, client, premium, issueDate);
        this.type = type;
        this.insuranceType = InsuranceType.AUTO;
    }

    @Override
    public double calculatePremium() {
        if (type == AutoInsuranceType.COMPREHENSIVE) {
            return premium * 1.2;
        } else {
            return premium;
        }
    }

    @Override
    public String toString() {
        return "AutoInsurancePolicy{" +
                "policyNumber='" + policyNumber + '\'' +
                ", client=" + client +
                ", premium=" + premium +
                ", issueDate=" + issueDate +
                ", insuranceType=" + insuranceType +
                ", type=" + type +
                '}';
    }
}
