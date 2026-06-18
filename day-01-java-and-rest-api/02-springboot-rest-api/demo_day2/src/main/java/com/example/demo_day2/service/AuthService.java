package com.example.demo_day2.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo_day2.model.Role;
import com.example.demo_day2.model.User;

@Service
public class AuthService {

    private final Map<String, User> users = new HashMap<>();

    public AuthService() {
        users.put("admin", new User("admin", "admin123", Role.ADMIN, "token-admin"));
        users.put("staff", new User("staff", "staff123", Role.STAFF, "token-staff"));
        users.put("approver", new User("approver", "approver123", Role.APPROVER, "token-approver"));
    }

    public User login(String username, String password) {
        User user = users.get(username);

        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }

    public User getByToken(String token) {
        // return users.values()
        // .stream()
        // .filter(u -> u.getToken().equals(token))
        // .findFirst()
        // .orElse(null);

        for (User u : users.values()) {
            if (u.getToken().equals(token)) {
                return u;
            }
        }
        return null;
    }

}
