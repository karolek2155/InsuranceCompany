package pl.gornik.insurancecompany.policies;

import pl.gornik.insurancecompany.Client;
import pl.gornik.insurancecompany.enums.InsuranceType;
import pl.gornik.insurancecompany.enums.PropertyInsuranceType;

import java.time.LocalDate;

public class PropertyInsurancePolicy extends Policy{
    private final PropertyInsuranceType type;

    public PropertyInsurancePolicy(String policyNumber, Client client, double premium, LocalDate issueDate, PropertyInsuranceType type) {
        super(policyNumber, client, premium, issueDate);
        this.type = type;
        this.insuranceType = InsuranceType.PROPERTY;
    }

    @Override
    public double calculatePremium() {
        if (type == PropertyInsuranceType.HOME) {
            return premium * 1.1;
        } else {
            return premium;
        }
    }

    @Override
    public String toString() {
        return "PropertyInsurancePolicy{" +
                "policyNumber='" + policyNumber + '\'' +
                ", client=" + client +
                ", premium=" + premium +
                ", issueDate=" + issueDate +
                ", insuranceType=" + insuranceType +
                ", type=" + type +
                '}';
    }
}
