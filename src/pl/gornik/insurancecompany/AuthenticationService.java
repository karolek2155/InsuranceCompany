package pl.gornik.insurancecompany;

import pl.gornik.insurancecompany.users.User;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {
    private static Map<String, User> users = new HashMap<>();

    public void registerUser(User user) {
        users.put(user.getEmail(), user);
    }

    public static User login(String email, String password) {
        User user = users.get(email);
        if (user != null && user.authenticate(password)) {
            return user;
        }
        return null;
    }
}