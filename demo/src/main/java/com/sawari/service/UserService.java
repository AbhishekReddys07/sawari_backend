package com.sawari.service;

import com.sawari.DTO.UserSignupDTO;
import com.sawari.DTO.UserLoginDTO;

public interface UserService {
    String registerUser(UserSignupDTO dto);
    String loginUser(UserLoginDTO dto);
}
