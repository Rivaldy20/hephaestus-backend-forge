package com.example.demo_day2.security;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.example.demo_day2.model.User;
import com.example.demo_day2.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;

public class AuthUtil {

    public static User authenticate(HttpServletRequest request, AuthService authService) {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication is required");
        }

        String token = header.replace("Bearer ", "");
        User user = authService.getByToken(token);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid token");
        }

        return user;
    }
}