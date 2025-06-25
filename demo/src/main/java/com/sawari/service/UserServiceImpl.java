package com.sawari.service;

import com.sawari.DTO.UserLoginDTO;
import com.sawari.DTO.UserSignupDTO;
import com.sawari.Entity.User;
import com.sawari.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Map<String, Object> registerUser(UserSignupDTO dto) {
        Map<String, Object> response = new HashMap<>();

        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            response.put("status", "error");
            response.put("message", "Passwords do not match");
            return response;
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            response.put("status", "error");
            response.put("message", "Email already registered");
            return response;
        }

        if (userRepository.existsByUsername(dto.getUsername())) {
            response.put("status", "error");
            response.put("message", "Username already taken");
            return response;
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        try {
            userRepository.save(user);
            response.put("status", "success");
            response.put("message", "User registered successfully");
            response.put("userId", user.getId());
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to save user: " + e.getMessage());
        }

        return response;
    }

    public Map<String, Object> loginUser(UserLoginDTO dto) {
        Map<String, Object> response = new HashMap<>();

        Optional<User> userOpt = userRepository.findByEmail(dto.getEmail());

        if (userOpt.isEmpty()) {
            response.put("status", "error");
            response.put("message", "User not found with given email");
            return response;
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            response.put("status", "error");
            response.put("message", "Invalid credentials");
            return response;
        }

        response.put("status", "success");
        response.put("message", "Login successful");
        response.put("userId", user.getId());
        response.put("name", user.getName());
        response.put("email", user.getEmail());
        return response;
    }

}
