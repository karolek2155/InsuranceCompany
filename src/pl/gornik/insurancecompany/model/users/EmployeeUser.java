package pl.gornik.insurancecompany.model.users;

public class EmployeeUser extends User {
    public EmployeeUser(String email, String password) {
        super(email, password, "Employee");
    }

    public EmployeeUser(String email, String password, String role) {
        super(email, password, role);
    }
}