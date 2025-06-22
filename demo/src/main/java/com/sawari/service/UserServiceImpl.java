package com.sawari.service;

import com.sawari.DTO.UserSignupDTO;
import com.sawari.DTO.UserLoginDTO;
import com.sawari.Entity.UserSignup;
import com.sawari.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public String registerUser(UserSignupDTO dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            return "Error: Passwords do not match.";
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            return "Error: Email already registered.";
        }

        String hashedPassword = passwordEncoder.encode(dto.getPassword());
        UserSignup user = new UserSignup(dto.getName(), dto.getEmail(), hashedPassword);
        userRepository.save(user);
        return "User registered successfully!";
    }

    @Override
    public String loginUser(UserLoginDTO dto) {
    	UserSignup user = userRepository.findByEmail(dto.getEmail());
        if (user == null) {
            return "Error: User not found.";
        }

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            return "Error: Invalid password.";
        }

        return "Login successful!";
    }
}
