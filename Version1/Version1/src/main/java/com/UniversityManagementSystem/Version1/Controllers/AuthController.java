package com.UniversityManagementSystem.Version1.Controllers;

import com.UniversityManagementSystem.Version1.Payload.Request.UserRegistrationRequest;
import com.UniversityManagementSystem.Version1.Payload.Response.JwtResponse;
import com.UniversityManagementSystem.Version1.Payload.Request.LoginRequest;
import com.UniversityManagementSystem.Version1.Services.Impl.StudentServiceImpl;
import com.UniversityManagementSystem.Version1.Services.Impl.UserServiceImpl;
import com.UniversityManagementSystem.Version1.entity.Student;
import com.UniversityManagementSystem.Version1.entity.User;
import com.UniversityManagementSystem.Version1.enums.RoleName;
import com.UniversityManagementSystem.Version1.security.JwtTokenUtil;
import com.UniversityManagementSystem.Version1.Services.*;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            Optional<User> optionalUser = userService.findByUsername(loginRequest.getUsername());
            if (optionalUser.isEmpty()) {
                throw new RuntimeException("Invalid username or password");
            }

            User user = optionalUser.get();
            String jwt = jwtTokenUtil.generateToken(user);

            return ResponseEntity.ok(new JwtResponse(
                    jwt,
                    user.getUsername(),
                    user.getRoleName().name(),
                    user.getUserId()
            ));

        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Invalid username or password!");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest) {
        User user = new User();
        Student student=new Student();

        user.setUsername(userRegistrationRequest.getUsername());
        user.setEmail(userRegistrationRequest.getEmail());
        user.setPasswordHash(userRegistrationRequest.getPassword());  // Make sure to hash this in production!
        user.setFirstName(userRegistrationRequest.getFirstName());
        user.setLastName(userRegistrationRequest.getLastName());
        user.setDateOfBirth(userRegistrationRequest.getDateOfBirth());  // If your User entity expects a Date type, convert it



        // You might also want to fetch Department by ID and set it here:
        // Department dept = departmentRepository.findById(req.getDepartmentId()).orElseThrow(...);
        // user.setDepartment(dept);
        try {
            user.setRoleName(RoleName.valueOf(userRegistrationRequest.getRole().toUpperCase()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid role: " + userRegistrationRequest.getRole());
        }





        User createdUser = userService.createUser(user);

        if (createdUser.getRoleName().name().equals("STUDENT")) {
            student.setUser(createdUser);
            student.setEmail(createdUser.getEmail());
            student.setFirstName(createdUser.getFirstName());
            student.setLastName(createdUser.getLastName());
            student.setDateOfBirth(createdUser.getDateOfBirth());
        }


        if (user.getRoleName().name().equals("STUDENT")) {
            Student createdStudent = studentService.registerStudent(student);
        }




        String jwt = jwtTokenUtil.generateToken(createdUser);

        return ResponseEntity.ok(Map.of(
                "token", jwt,
                "username", createdUser.getUsername(),
                "role", createdUser.getRoleName().name()
        ));

    }
}