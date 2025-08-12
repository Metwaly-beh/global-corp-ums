package com.UniversityManagementSystem.Version1.repository;

import com.UniversityManagementSystem.Version1.entity.Department;
import com.UniversityManagementSystem.Version1.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {

    // Find instructor by email
    Optional<Instructor> findByEmail(String email);

    // Find instructors by department
    List<Instructor> findByDepartment(Department department);

    // Find instructors by department ID
    List<Instructor> findByDepartmentDepartmentId(Integer departmentId);

    // Find instructor by user ID
    Optional<Instructor> findByUserUserId(Integer userId);

    // Find instructors by first and last name
    List<Instructor> findByFirstNameAndLastName(String firstName, String lastName);

    // Custom query to find instructor with their courses
    @Query("SELECT i FROM Instructors i LEFT JOIN FETCH i.courses WHERE i.instructorId = :id")
    Optional<Instructor> findByIdWithCourses(@Param("id") Integer instructorId);

    // Check if email exists
    boolean existsByEmail(String email);
}
