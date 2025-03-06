package pl.gornik.insurancecompany.model.policies;

import pl.gornik.insurancecompany.service.Client;
import pl.gornik.insurancecompany.model.enums.InsuranceType;
import pl.gornik.insurancecompany.model.enums.PropertyInsuranceType;

import java.time.LocalDate;

public class PropertyInsurancePolicy extends Policy{
    private final PropertyInsuranceType type;

    public PropertyInsurancePolicy(String policyNumber, Client client, double premium, LocalDate issueDate, PropertyInsuranceType type) {
        super(policyNumber, client, premium, issueDate);
        this.type = type;
        this.insuranceType = InsuranceType.PROPERTY;
    }

    @Override
    public void updatePremium() {
        premium = switch (type) {
            case HOME -> premium * 1.1;
            case APARTMENT -> premium * 1.05;
            default -> premium;
        };
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
