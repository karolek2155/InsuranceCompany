package pl.gornik.insurancecompany.users;

public class ClientUser extends User {
    public ClientUser(String email, String password) {
        super(email, password, "Client");
    }
}