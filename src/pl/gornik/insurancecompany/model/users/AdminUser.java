package pl.gornik.insurancecompany.model.users;

public class AdminUser extends EmployeeUser  {
    public AdminUser(String email, String password) {
        super(email, password, "Admin");
    }
}