package com.UniversityManagementSystem.Version1.repository;

import com.UniversityManagementSystem.Version1.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    // Find role by name (most common query)
    Optional<Role> findByRoleName(String roleName);

    // Check if role name exists
    boolean existsByRoleName(String roleName);

    // Find role with users
    @Query("SELECT r FROM Role r LEFT JOIN FETCH r.users WHERE r.roleId = :id")
    Optional<Role> findByIdWithUsers(@Param("id") Integer roleId);

    // Count users by role
    @Query("SELECT COUNT(u) FROM User u WHERE u.role.roleId = :roleId")
    Long countUsersByRoleId(@Param("roleId") Integer roleId);
}
