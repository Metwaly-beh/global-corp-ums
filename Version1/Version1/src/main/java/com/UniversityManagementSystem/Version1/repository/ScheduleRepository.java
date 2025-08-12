package com.UniversityManagementSystem.Version1.repository;

import com.UniversityManagementSystem.Version1.entity.Classroom;
import com.UniversityManagementSystem.Version1.entity.Course;
import com.UniversityManagementSystem.Version1.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

    // Find schedules by course
    List<Schedule> findByCourse(Course course);

    // Find schedules by course ID
    List<Schedule> findByCourseCourseId(Integer courseId);

    // Find schedules by classroom
    List<Schedule> findByClassroom(Classroom classroom);

    // Find schedules by classroom ID
    List<Schedule> findByClassroomClassroomId(Integer classroomId);

    // Find schedules by day
    List<Schedule> findByScheduleDay(String scheduleDay);

    // Find schedules by day and time range
    List<Schedule> findByScheduleDayAndStartTimeBetween(String scheduleDay, LocalTime startTime, LocalTime endTime);

    // Find conflicting schedules (same classroom, overlapping time)
    @Query("SELECT s FROM Schedules s WHERE s.classroom.classroomId = :classroomId " +
            "AND s.scheduleDay = :day AND s.startTime < :endTime AND s.endTime > :startTime")
    List<Schedule> findConflictingSchedules(@Param("classroomId") Integer classroomId,
                                             @Param("day") String day,
                                             @Param("startTime") LocalTime startTime,
                                             @Param("endTime") LocalTime endTime);

    // Find schedules for a specific instructor
    @Query("SELECT s FROM Schedules s WHERE s.course.instructor.instructorId = :instructorId")
    List<Schedule> findByInstructorId(@Param("instructorId") Integer instructorId);

    // Find schedules with participation
    @Query("SELECT s FROM Schedules s LEFT JOIN FETCH s.participation WHERE s.scheduleId = :id")
    List<Schedule> findByIdWithParticipation(@Param("id") Integer scheduleId);
}
