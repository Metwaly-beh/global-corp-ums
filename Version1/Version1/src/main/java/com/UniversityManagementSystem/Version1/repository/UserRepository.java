package com.UniversityManagementSystem.Version1.repository;

import com.UniversityManagementSystem.Version1.entity.Role;
import com.UniversityManagementSystem.Version1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Find user by username (for authentication)
    Optional<User> findByUsername(String username);

    // Find user by email
    Optional<User> findByEmail(String email);

    // Find users by role
    List<User> findByRole(Role role);

    // Find users by role ID
    List<User> findByRoleRoleId(Integer roleId);

    // Find users by role name
    @Query("SELECT u FROM Users u WHERE u.role.roleName = :roleName")
    List<User> findByRoleName(@Param("roleName") String roleName);

    // Find users created after a specific date
    List<User> findByCreatedAtAfter(LocalDateTime date);

    // Find users created between dates
    List<User> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Check if username exists
    boolean existsByUsername(String username);

    // Check if email exists
    boolean existsByEmail(String email);

    // Find user with role (eager loading)
    @Query("SELECT u FROM Users u JOIN FETCH u.role WHERE u.userId = :id")
    Optional<User> findByIdWithRole(@Param("id") Integer userId);

    // Find user by username with role (for authentication)
    @Query("SELECT u FROM Users u JOIN FETCH u.role WHERE u.username = :username")
    Optional<User> findByUsernameWithRole(@Param("username") String username);
}
