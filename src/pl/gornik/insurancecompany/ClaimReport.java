package pl.gornik.insurancecompany;

import java.time.LocalDate;

public class ClaimReport {
    private Client client;
    private String description;
    private LocalDate reportDate;
    private String policyNumber;

    public ClaimReport(Client client, String description, LocalDate reportDate, String policyNumber) {
        this.client = client;
        this.description = description;
        this.reportDate = reportDate;
        this.policyNumber = policyNumber;
    }

    public Client getClient() {
        return client;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    @Override
    public String toString() {
        return "ClaimReport{" +
                "client=" + client +
                ", description='" + description + '\'' +
                ", reportDate=" + reportDate +
                ", policyNumber='" + policyNumber + '\'' +
                '}';
    }
}
