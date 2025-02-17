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
    public void updatePremium() {
        premium = switch (type) {
            case COMPREHENSIVE -> premium * 1.2;
            case COLLISION -> premium * 1.1;
            default -> premium;
        };
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
