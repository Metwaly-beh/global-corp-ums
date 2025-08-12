package com.UniversityManagementSystem.Version1.Services;

import com.UniversityManagementSystem.Version1.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);
    User createUser(User user);

    User getUserById(Integer id);

    List<User> getAllUsers();

    User updateUser(Integer id, User userDetails);

    void deleteUser(Integer id);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}