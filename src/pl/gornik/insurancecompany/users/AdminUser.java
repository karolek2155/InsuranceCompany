package pl.gornik.insurancecompany.users;

public class AdminUser extends User {
    public AdminUser(String email, String password) {
        super(email, password, "Admin");
    }
}