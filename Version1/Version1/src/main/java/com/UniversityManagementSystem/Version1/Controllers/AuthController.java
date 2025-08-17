package com.UniversityManagementSystem.Version1.Controllers;

import com.UniversityManagementSystem.Version1.Payload.Response.JwtResponse;
import com.UniversityManagementSystem.Version1.Payload.Request.LoginRequest;
import com.UniversityManagementSystem.Version1.Services.Impl.UserServiceImpl;
import com.UniversityManagementSystem.Version1.entity.User;
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

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

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
                    user.getRole().getRoleName(),
                    user.getUserId()
            ));

        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Invalid username or password!");
        }
    }
}