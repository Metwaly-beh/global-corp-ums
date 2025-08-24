package com.UniversityManagementSystem.Version1.Payload.Request;

//import com.UniversityManagementSystem.Version1.entity.Role;

import com.UniversityManagementSystem.Version1.enums.RoleName;

import java.time.LocalDate;

public class UserRegistrationRequest {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth; // For students
    private Long departmentId;
    private RoleName role; // "STUDENT" or "INSTRUCTOR"

    // Constructors, getters, setters
    public UserRegistrationRequest() {}

    public UserRegistrationRequest(String username, String password, String email,
                                   String firstName, String lastName, LocalDate dateOfBirth,
                                   Long departmentId, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.departmentId = departmentId;
        this.role = RoleName.valueOf(role);
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }
    public String getRole() { return role.name(); }
    public void setRole(String role) { this.role = RoleName.valueOf(role); }
}
