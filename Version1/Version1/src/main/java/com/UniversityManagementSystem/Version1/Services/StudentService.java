    package com.UniversityManagementSystem.Version1.Services;

    import com.UniversityManagementSystem.Version1.entity.*;

    import java.util.List;

    public interface StudentService {

        Student registerStudent(Student student);
        Student getStudentById(int studentId);
        List<Student> getAllStudents();
        Student updateStudent(int studentId,Student student);
        void deleteStudent(int studentId);
        Course enrollCourse(int courseId);
        Schedule getSchedule(int studentId);
        Performance getPerformance(int studentId);
        Participation getParticipation(int studentId);

    }
