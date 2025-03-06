package pl.gornik.insurancecompany.model.users;

public class ClientUser extends User {
    public ClientUser(String email, String password) {
        super(email, password, "Client");
    }
}