package com.UniversityManagementSystem.Version1.Services.Impl;

import com.UniversityManagementSystem.Version1.entity.Instructor;
import com.UniversityManagementSystem.Version1.entity.Student;
import com.UniversityManagementSystem.Version1.entity.User;
import com.UniversityManagementSystem.Version1.repository.InstructorRepository;
import com.UniversityManagementSystem.Version1.repository.StudentRepository;
import com.UniversityManagementSystem.Version1.repository.UserRepository;
import com.UniversityManagementSystem.Version1.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private InstructorRepository instructorRepository;

    private InstructorServiceImpl instructorService;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email is already in use: " + user.getEmail());
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username is already taken: " + user.getUsername());
        }

        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Integer id, User userDetails) {
        User user = getUserById(id);

        user.setUsername(userDetails.getUsername());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setDateOfBirth(userDetails.getDateOfBirth());
        user.setEmail(userDetails.getEmail());
        user.setPasswordHash(userDetails.getPasswordHash());
        user.setRoleName(userDetails.getRoleName());

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Instructor getInstructorByUser(User user) {
        return instructorRepository.findByUserUserId(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Instructor not Found"));

    }

    @Override
    public Student getStudentByUser(User user) {
        return null;
    }

    @Override
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public int getStudentIdByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));

        Student student = studentRepository.findByUserUserId(user.getUserId())
                .orElseThrow(() -> new RuntimeException("Student not found for user ID: " + user.getUserId()));

        return student.getStudentId();
    }

}
