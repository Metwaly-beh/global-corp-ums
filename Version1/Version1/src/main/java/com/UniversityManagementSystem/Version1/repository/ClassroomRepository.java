package com.UniversityManagementSystem.Version1.repository;

import com.UniversityManagementSystem.Version1.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Integer> {

    // Find classrooms by building
    List<Classroom> findByBuilding(String building);

    // Find classroom by building and room number
    Optional<Classroom> findByBuildingAndRoomNumber(String building, String roomNumber);

    // Find classrooms by capacity greater than or equal to
    List<Classroom> findByCapacityGreaterThanEqual(Integer minCapacity);

    // Find classrooms by capacity range
    List<Classroom> findByCapacityBetween(Integer minCapacity, Integer maxCapacity);

    // Find available classrooms (not scheduled at specific time)
    @Query("SELECT c FROM Classroom c WHERE c.classroomId NOT IN " +
            "(SELECT s.classroom.classroomId FROM Schedule s WHERE s.scheduleDay = :day " +
            "AND s.startTime < :endTime AND s.endTime > :startTime)")
    List<Classroom> findAvailableClassrooms(@Param("day") String day,
                                             @Param("startTime") java.time.LocalTime startTime,
                                             @Param("endTime") java.time.LocalTime endTime);

    // Check if classroom exists by building and room number
    boolean existsByBuildingAndRoomNumber(String building, String roomNumber);
}
