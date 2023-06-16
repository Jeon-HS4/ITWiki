package com.tukorea.itwiki.user.dto;

import java.sql.Timestamp;

public class Signup {

    private String userId;
    private String password;
    private String username;
    private String email;
    private Timestamp userCreate;
    private int role;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(Timestamp userCreate) {
        this.userCreate = userCreate;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
