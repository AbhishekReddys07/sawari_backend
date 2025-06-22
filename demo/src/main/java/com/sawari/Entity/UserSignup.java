package com.sawari.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "SignUp")
public class UserSignup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    private String name;
    private String email;
    @Column(unique = true)
    private String password;

    public UserSignup() {}

    public UserSignup(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public int getNum() { return num; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
