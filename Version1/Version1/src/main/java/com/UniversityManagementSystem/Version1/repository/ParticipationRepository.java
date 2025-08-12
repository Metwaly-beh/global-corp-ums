package com.UniversityManagementSystem.Version1.repository;

import com.UniversityManagementSystem.Version1.entity.Participation;
import com.UniversityManagementSystem.Version1.entity.Schedule;
import com.UniversityManagementSystem.Version1.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipationRepository extends JpaRepository<Participation, Integer> {

    // Find participation by student
    List<Participation> findByStudent(Student student);

    // Find participation by student ID
    List<Participation> findByStudentStudentId(Integer studentId);

    // Find participation by schedule
    List<Participation> findBySchedule(Schedule schedule);

    // Find participation by schedule ID
    List<Participation> findByScheduleScheduleId(Integer scheduleId);

    // Find specific participation by student and schedule
    Optional<Participation> findByStudentAndSchedule(Student student, Schedule schedule);

    // Find participation by date
    List<Participation> findByDate(LocalDate date);

    // Find participation by date range
    List<Participation> findByDateBetween(LocalDate startDate, LocalDate endDate);

    // Find participation by attendance status
    List<Participation> findByAttended(Boolean attended);

    // Find attended participation by student
    List<Participation> findByStudentAndAttended(Student student, Boolean attended);

    // Find attended participation by student ID
    @Query("SELECT p FROM Participation p WHERE p.student.studentId = :studentId AND p.attended = :attended")
    List<Participation> findByStudentIdAndAttended(@Param("studentId") Integer studentId, @Param("attended") Boolean attended);

    // Calculate attendance rate for student
    @Query("SELECT AVG(CASE WHEN p.attended = true THEN 1.0 ELSE 0.0 END) " +
            "FROM Participation p WHERE p.student.studentId = :studentId")
    Double calculateAttendanceRateByStudentId(@Param("studentId") Integer studentId);

    // Calculate attendance rate for course
    @Query("SELECT AVG(CASE WHEN p.attended = true THEN 1.0 ELSE 0.0 END) " +
            "FROM Participation p WHERE p.schedule.course.courseId = :courseId")
    Double calculateAttendanceRateByCourseId(@Param("courseId") Integer courseId);

    // Count attendances by student and course
    @Query("SELECT COUNT(p) FROM Participation p WHERE p.student.studentId = :studentId " +
            "AND p.schedule.course.courseId = :courseId AND p.attended = true")
    Long countAttendancesByStudentIdAndCourseId(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);
}
