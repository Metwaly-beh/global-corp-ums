package com.UniversityManagementSystem.Version1.Services;

import com.UniversityManagementSystem.Version1.entity.*;


import java.util.List;

public interface InstructorService {
    Instructor registerInstructor(Instructor instructor);
    Instructor getInstructorById(int instructorId);
    List<Instructor> getAllInstructors();
    Instructor updateInstructor(int instructorId,Instructor instructor);
    void deleteInstructor(int instructorId);
    Enrollment getStudentEnrollment(Student student);
    Schedule getCourseSchedule(Course course);
    Course setCourse(Course course);
    Exam setExam(Exam exam);


}
