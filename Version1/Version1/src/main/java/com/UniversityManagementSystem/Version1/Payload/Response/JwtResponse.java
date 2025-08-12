package com.UniversityManagementSystem.Version1.Payload.Response;

import com.UniversityManagementSystem.Version1.enums.UserRole;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String username;
    private UserRole role;
    private int userId;

    public JwtResponse(String accessToken, String username, UserRole role, int userId) {
        this.token = accessToken;
        this.username = username;
        this.role = role;
        this.userId = userId;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}