package com.UniversityManagementSystem.Version1.repository;

import com.UniversityManagementSystem.Version1.entity.Exam;
import com.UniversityManagementSystem.Version1.entity.Performance;
import com.UniversityManagementSystem.Version1.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Integer> {

    // Find performances by student
    List<Performance> findByStudent(Student student);

    // Find performances by student ID
    List<Performance> findByStudentStudentId(Integer studentId);

    // Find performances by exam
    List<Performance> findByExam(Exam exam);

    // Find performances by exam ID
    List<Performance> findByExamExamId(Integer examId);

    // Find specific performance by student and exam
    Optional<Performance> findByStudentAndExam(Student student, Exam exam);

    // Find performances by grade range
    List<Performance> findByGradeBetween(Float minGrade, Float maxGrade);

    // Find performances by minimum grade
    List<Performance> findByGradeGreaterThanEqual(Float minGrade);

    // Find performances by maximum grade
    List<Performance> findByGradeLessThanEqual(Float maxGrade);

    // Find student performances for a specific course
    @Query("SELECT p FROM Performance p WHERE p.student.studentId = :studentId " +
            "AND p.exam.course.courseId = :courseId")
    List<Performance> findByStudentIdAndCourseId(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);

    // Calculate average grade for student
    @Query("SELECT AVG(p.grade) FROM Performance p WHERE p.student.studentId = :studentId")
    Double calculateAverageGradeByStudentId(@Param("studentId") Integer studentId);

    // Calculate average grade for exam
    @Query("SELECT AVG(p.grade) FROM Performance p WHERE p.exam.examId = :examId")
    Double calculateAverageGradeByExamId(@Param("examId") Integer examId);

    // Calculate average grade for course
    @Query("SELECT AVG(p.grade) FROM Performance p WHERE p.exam.course.courseId = :courseId")
    Double calculateAverageGradeByCourseId(@Param("courseId") Integer courseId);

    // Find top performers for exam
    @Query("SELECT p FROM Performance p WHERE p.exam.examId = :examId ORDER BY p.grade DESC")
    List<Performance> findTopPerformersByExamId(@Param("examId") Integer examId);

    // Find student's highest grade
    @Query("SELECT MAX(p.grade) FROM Performance p WHERE p.student.studentId = :studentId")
    Float findHighestGradeByStudentId(@Param("studentId") Integer studentId);

    // Find student's lowest grade
    @Query("SELECT MIN(p.grade) FROM Performance p WHERE p.student.studentId = :studentId")
    Float findLowestGradeByStudentId(@Param("studentId") Integer studentId);

    // Count performances by student
    @Query("SELECT COUNT(p) FROM Performance p WHERE p.student.studentId = :studentId")
    Long countByStudentId(@Param("studentId") Integer studentId);
}
