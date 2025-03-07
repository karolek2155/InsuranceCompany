package pl.gornik.insurancecompany.service;

import pl.gornik.insurancecompany.model.enums.ClaimStatus;

import java.time.LocalDate;
import java.util.UUID;

public class ClaimReport {
    private ClaimStatus status;
    private String claimNumber;
    private Client client;
    private String description;
    private LocalDate reportDate;
    private String policyNumber;

    public ClaimReport(Client client, String description, LocalDate reportDate, String policyNumber) {
        this.client = client;
        this.description = description;
        this.reportDate = reportDate;
        this.policyNumber = policyNumber;
        this.claimNumber = generateClaimNumber();
    }

    public static String generateClaimNumber() {
        return UUID.randomUUID().toString();
    }

    public ClaimStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setStatus(ClaimStatus status) {
        this.status = status;
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
