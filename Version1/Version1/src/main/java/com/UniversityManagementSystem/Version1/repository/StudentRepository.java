package com.UniversityManagementSystem.Version1.repository;


import com.UniversityManagementSystem.Version1.entity.Department;
import com.UniversityManagementSystem.Version1.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

Optional<Student> findByEmail(String email);


    List<Student> findByDepartment(Department department);

    List<Student> findByDepartmentDepartmentId(Integer departmentId);

    Optional<Student> findByUserUserId(Integer userId);

    List<Student> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("SELECT s FROM Student s LEFT JOIN FETCH s.enrollments WHERE s.studentId = :id")
    Optional<Student> findByIdWithEnrollment(@Param("id") Integer studentId);}
