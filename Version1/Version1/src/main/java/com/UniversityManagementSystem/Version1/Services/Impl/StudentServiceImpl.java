package com.UniversityManagementSystem.Version1.Services.Impl;
import com.UniversityManagementSystem.Version1.Services.StudentService;
import com.UniversityManagementSystem.Version1.entity.*;
import com.UniversityManagementSystem.Version1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    //@Autowired
    //private RoleRepository roleRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Student registerStudent(Student student) {
        // Create user account
       /* User user = new User();
        user.setUsername(student.getEmail());
        user.setEmail(student.getEmail());
        user.setPasswordHash(passwordEncoder.encode("defaultPassword123")); // You might want to generate this
        user.setCreatedAt(LocalDateTime.now());
*/
        // Set student role
      /*  Role studentRole = roleRepository.findByRoleName("STUDENT")
                .orElseThrow(() -> new RuntimeException("Student role not found"));
        user.setRole(studentRole);*/

        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(int studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(int studentId, Student student) {
        Student existingStudent = getStudentById(studentId);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setDateOfBirth(student.getDateOfBirth());
        existingStudent.setDepartment(student.getDepartment());

        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(int studentId) {
        Student student = getStudentById(studentId);
        studentRepository.delete(student);
    }

    @Override
    public Course enrollCourse(int courseId) {
        // This method should take studentId as parameter too
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));
        return course;
    }

    public void enrollStudentInCourse(int studentId, int courseId) {
        Student student = getStudentById(studentId);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        // Check if already enrolled
        boolean alreadyEnrolled = enrollmentRepository.existsByStudentAndCourse(student, course);
        if (alreadyEnrolled) {
            throw new RuntimeException("Student already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(LocalDateTime.now());

        enrollmentRepository.save(enrollment);
    }

    @Override
    public Schedule getSchedule(int studentId) {
        // This should return a list of schedules for the student
        Student student = getStudentById(studentId);
        List<Enrollment> enrollments = enrollmentRepository.findByStudent(student);
        // You'd need to implement logic to get schedules from enrollments
        return null; // Placeholder
    }

    public List<Schedule> getStudentSchedules(int studentId) {
        Student student = getStudentById(studentId);
        List<Enrollment> enrollments = enrollmentRepository.findByStudent(student);
        return enrollments.stream()
                .flatMap(enrollment -> scheduleRepository.findByCourse(enrollment.getCourse()).stream())
                .toList();
    }

    @Override
    public Performance getPerformance(int studentId) {
        Student student = getStudentById(studentId);
        List<Performance> performances = performanceRepository.findByStudent(student);
        return performances.isEmpty() ? null : performances.get(0); // Return first or modify to return list
    }

    public List<Performance> getAllPerformances(int studentId) {
        Student student = getStudentById(studentId);
        return performanceRepository.findByStudent(student);
    }

    @Override
    public Participation getParticipation(int studentId) {
        Student student = getStudentById(studentId);
        List<Participation> participations = participationRepository.findByStudent(student);
        return participations.isEmpty() ? null : participations.get(0); // Return first or modify to return list
    }

    public List<Participation> getAllParticipations(int studentId) {
        Student student = getStudentById(studentId);
        return participationRepository.findByStudent(student);
    }


}
