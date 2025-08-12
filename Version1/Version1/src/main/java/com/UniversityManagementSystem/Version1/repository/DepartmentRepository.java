package com.UniversityManagementSystem.Version1.repository;

import com.UniversityManagementSystem.Version1.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    // Find department by name
    Optional<Department> findByDepartmentName(String departmentName);

    // Check if department name exists
    boolean existsByDepartmentName(String departmentName);

    // Custom query to find department with students count
    @Query("SELECT d FROM Departments d LEFT JOIN d.students s WHERE d.departmentId = :id")
    Optional<Department> findByIdWithStudents(@Param("id") Integer departmentId);
}
