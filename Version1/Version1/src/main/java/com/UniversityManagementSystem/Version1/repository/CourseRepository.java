package com.UniversityManagementSystem.Version1.repository;

import com.UniversityManagementSystem.Version1.entity.Course;
import com.UniversityManagementSystem.Version1.entity.Department;
import com.UniversityManagementSystem.Version1.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    // Find courses by name
    List<Course> findByCourseName(String courseName);

    // Find courses by department
    List<Course> findByDepartment(Department department);

    // Find courses by department ID
    List<Course> findByDepartmentDepartmentId(Integer departmentId);

    // Find courses by instructor
    List<Course> findByInstructor(Instructor instructor);

    // Find courses by instructor ID
    List<Course> findByInstructorInstructorId(Integer instructorId);

    // Find courses by credits
    List<Course> findByCredits(Integer credits);

    // Find courses with enrollments
    @Query("SELECT c FROM Courses c LEFT JOIN FETCH c.enrollments WHERE c.courseId = :id")
    Optional<Course> findByIdWithEnrollments(@Param("id") Integer courseId);

    // Find courses with schedules and exams
    @Query("SELECT c FROM Courses c LEFT JOIN FETCH c.schedules LEFT JOIN FETCH c.exams WHERE c.courseId = :id")
    Optional<Course> findByIdWithSchedulesAndExams(@Param("id") Integer courseId);
}
