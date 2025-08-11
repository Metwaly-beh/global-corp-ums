package entity;


import enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
//@ToString(exclude = "role")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @Column(name = "password_hash", length = 255)
    private String passwordHash;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

   /* @ManyToOne(fetch = FetchType.EAGER) // Load role immediately since it's always needed
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
*/}