package pl.gornik.insurancecompany;

public class Client {
    private String firstName;
    private String lastName;
    private String address;
    private String pesel;
    private String phoneNumber;
    private String email;

    public Client(String firstName, String lastName, String address, String pesel, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.pesel = pesel;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void presentClient() {
        System.out.println(firstName + " " + lastName + " - Adres: " + address + " - PESEL: " + pesel + " - Telefon: " + phoneNumber + " - E-mail: " + email);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPesel() {
        return pesel;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
