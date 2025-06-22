package com.sawari.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sawari.Entity.UserLogin;

public interface UserRepositoryLogin extends JpaRepository<UserLogin, Long> {
    Optional<UserLogin> findByUsername(String username);
}
