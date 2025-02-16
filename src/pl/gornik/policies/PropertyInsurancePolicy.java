package pl.gornik.policies;

import pl.gornik.Client;

import java.time.LocalDate;

public class PropertyInsurancePolicy extends Policy{
    private final PropertyInsuranceType type;

    public PropertyInsurancePolicy(String policyNumber, Client client, double premium, LocalDate issueDate, PropertyInsuranceType type) {
        super(policyNumber, client, premium, issueDate);
        this.type = type;
    }

    @Override
    public double calculatePremium() {
        if (type == PropertyInsuranceType.HOME) {
            return premium * 1.1;
        } else {
            return premium;
        }
    }
}
