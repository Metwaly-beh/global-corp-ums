package com.UniversityManagementSystem.Version1.repository;

import com.UniversityManagementSystem.Version1.entity.Classroom;
import com.UniversityManagementSystem.Version1.entity.Course;
import com.UniversityManagementSystem.Version1.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {

    // Find exams by course
    List<Exam> findByCourse(Course course);

    // Find exams by course ID
    List<Exam> findByCourseCourseId(Integer courseId);

    // Find exams by classroom
    List<Exam> findByClassroom(Classroom classroom);

    // Find exams by exam date
    List<Exam> findByExamDate(LocalDate examDate);

    // Find exams by date range
    List<Exam> findByExamDateBetween(LocalDate startDate, LocalDate endDate);

    // Find exams by type
    List<Exam> findByExamType(String examType);

    // Find upcoming exams
    List<Exam> findByExamDateGreaterThanEqual(LocalDate currentDate);

    // Find past exams
    List<Exam> findByExamDateLessThan(LocalDate currentDate);

    // Find conflicting exams (same classroom, same date, overlapping time)
    @Query("SELECT e FROM Exam e WHERE e.classroom.classroomId = :classroomId " +
            "AND e.examDate = :examDate AND e.startTime < :endTime AND e.endTime > :startTime")
    List<Exam> findConflictingExams(@Param("classroomId") Integer classroomId,
                                     @Param("examDate") LocalDate examDate,
                                     @Param("startTime") LocalTime startTime,
                                     @Param("endTime") LocalTime endTime);

    // Find exams for a specific instructor
    @Query("SELECT e FROM Exam e WHERE e.course.instructor.instructorId = :instructorId")
    List<Exam> findByInstructorId(@Param("instructorId") Integer instructorId);

    // Find exams with performances
    @Query("SELECT e FROM Exam e LEFT JOIN FETCH e.performances WHERE e.examId = :id")
    List<Exam> findByIdWithPerformances(@Param("id") Integer examId);
}
