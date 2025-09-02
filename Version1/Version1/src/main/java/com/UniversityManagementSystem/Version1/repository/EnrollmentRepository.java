package com.UniversityManagementSystem.Version1.repository;

import com.UniversityManagementSystem.Version1.entity.Course;
import com.UniversityManagementSystem.Version1.entity.Enrollment;
import com.UniversityManagementSystem.Version1.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {

    // Find enrollments by student
    List<Enrollment> findByStudent(Student student);

    // Find enrollments by student ID
    List<Enrollment> findByStudentStudentId(Integer studentId);

    // Find enrollments by course
    List<Enrollment> findByCourse(Course course);

    // Find enrollments by course ID
    List<Enrollment> findByCourseCourseId(Integer courseId);

    // Find specific enrollment by student and course
    Optional<Enrollment> findByStudentAndCourse(Student student, Course course);

    // Find enrollments by date range
    List<Enrollment> findByEnrolledAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Find recent enrollments
    List<Enrollment> findByEnrolledAtGreaterThanEqual(LocalDateTime date);

    // Check if student is enrolled in course
    boolean existsByStudentAndCourse(Student student, Course course);

    // Check if student is enrolled in course by IDs
    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END " +
            "FROM Enrollment e WHERE e.student.studentId = :studentId AND e.course.courseId = :courseId")
    boolean existsByStudentIdAndCourseId(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);

    // Count enrollments by course
    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.course.courseId = :courseId")
    Long countByCourseId(@Param("courseId") Integer courseId);

    // Count enrollments by student
    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.student.studentId = :studentId")
    Long countByStudentId(@Param("studentId") Integer studentId);
}
