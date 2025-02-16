package pl.gornik;

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
}
