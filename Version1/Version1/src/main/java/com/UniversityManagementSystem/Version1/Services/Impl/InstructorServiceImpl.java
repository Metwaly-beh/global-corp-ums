package com.UniversityManagementSystem.Version1.Services.Impl;

import com.UniversityManagementSystem.Version1.Services.InstructorService;
import com.UniversityManagementSystem.Version1.entity.*;
import com.UniversityManagementSystem.Version1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Instructor registerInstructor(Instructor instructor) {
        // Create user account
        User user = new User();
        user.setUsername(instructor.getEmail());
        user.setEmail(instructor.getEmail());
        user.setPasswordHash(passwordEncoder.encode("defaultPassword123")); // You might want to generate this
        user.setCreatedAt(LocalDateTime.now());

        // Set instructor role
        Role instructorRole = roleRepository.findByRoleName("INSTRUCTOR")
                .orElseThrow(() -> new RuntimeException("Instructor role not found"));
        user.setRole(instructorRole);

        User savedUser = userRepository.save(user);
        instructor.setUser(savedUser);

        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor getInstructorById(int instructorId) {
        return instructorRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found with id: " + instructorId));
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor updateInstructor(int instructorId, Instructor instructor) {
        Instructor existingInstructor = getInstructorById(instructorId);
        existingInstructor.setFirstName(instructor.getFirstName());
        existingInstructor.setLastName(instructor.getLastName());
        existingInstructor.setEmail(instructor.getEmail());
        existingInstructor.setDepartment(instructor.getDepartment());

        return instructorRepository.save(existingInstructor);
    }

    @Override
    public void deleteInstructor(int instructorId) {
        Instructor instructor = getInstructorById(instructorId);
        instructorRepository.delete(instructor);
    }

    @Override
    public Enrollment getStudentEnrollment(Student student) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudent(student);
        return enrollments.isEmpty() ? null : enrollments.get(0);
    }

    public List<Enrollment> getStudentEnrollments(Student student) {
        return enrollmentRepository.findByStudent(student);
    }

    public List<Enrollment> getCourseEnrollments(int courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return enrollmentRepository.findByCourse(course);
    }

    @Override
    public Schedule getCourseSchedule(Course course) {
        List<Schedule> schedules = scheduleRepository.findByCourse(course);
        return schedules.isEmpty() ? null : schedules.get(0);
    }

    public List<Schedule> getCourseSchedules(Course course) {
        return scheduleRepository.findByCourse(course);
    }

    @Override
    public Course setCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Exam setExam(Exam exam) {
        return examRepository.save(exam);
    }

    public List<Course> getInstructorCourses(int instructorId) {
        Instructor instructor = getInstructorById(instructorId);
        return courseRepository.findByInstructor(instructor);
    }

    public List<Exam> getCourseExams(int courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return examRepository.findByCourse(course);
    }
}