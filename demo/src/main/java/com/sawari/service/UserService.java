package com.sawari.service;

import com.sawari.DTO.UserSignupDTO;

import java.util.Map;

import com.sawari.DTO.UserLoginDTO;

public interface UserService {
    Map<String, Object> registerUser(UserSignupDTO dto);
    Map<String, Object> loginUser(UserLoginDTO dto);
}
