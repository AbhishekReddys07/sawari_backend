package com.sawari.Repository;


import com.sawari.Entity.UserSignup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserSignup, Integer> {
    boolean existsByEmail(String email);
    UserSignup findByEmail(String email);
}
