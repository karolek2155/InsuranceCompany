package pl.gornik.insurancecompany.model.enums;

public enum InsuranceType {
    AUTO("komunikacyjna"),
    PROPERTY("majątkowa"),
    LIFE("na życie");

    private final String displayName;

    InsuranceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
