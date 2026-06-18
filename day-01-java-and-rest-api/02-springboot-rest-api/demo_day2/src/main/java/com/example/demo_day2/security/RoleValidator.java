package com.example.demo_day2.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo_day2.model.Role;
import com.example.demo_day2.model.User;

import java.util.Arrays;

public class RoleValidator {

    public static void validate(User user, Role... allowedRoles) {
        boolean allowed = Arrays.stream(allowedRoles)
                .anyMatch(role -> role == user.getRole());

        if (!allowed) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "You do not have permission to access this resource");
        }
    }
}