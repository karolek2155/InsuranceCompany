package pl.gornik.insurancecompany.users;

public class EmployeeUser extends User {
    public EmployeeUser(String email, String password) {
        super(email, password, "Employee");
    }
}