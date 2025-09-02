package com.UniversityManagementSystem.Version1.security;


import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class CurrentUser {

    private final String token;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public CurrentUser(HttpServletRequest request, JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            this.token = header.substring(7);
        } else {
            this.token = null;
        }
    }

    public String getUsername() {
        return jwtTokenUtil.getUsernameFromToken(token);
    }

    public String getRole() {
        return jwtTokenUtil.getRoleFromToken(token);
    }

    public Long getUserId() {
        return jwtTokenUtil.getUserIdFromToken(token);
    }

    public Integer getInstructorId() {
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(token);
        return claims.get("instructorId", Integer.class);
    }
}
