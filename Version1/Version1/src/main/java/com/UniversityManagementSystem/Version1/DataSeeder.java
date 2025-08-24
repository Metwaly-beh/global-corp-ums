package com.UniversityManagementSystem.Version1;


import com.UniversityManagementSystem.Version1.Services.InstructorService;
import com.UniversityManagementSystem.Version1.Services.StudentService;
import com.UniversityManagementSystem.Version1.Services.UserService;
import com.UniversityManagementSystem.Version1.entity.*;
import com.UniversityManagementSystem.Version1.enums.RoleName;
import com.UniversityManagementSystem.Version1.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedDatabase(
            DepartmentRepository departmentRepository,
            UserRepository userRepository,
            StudentRepository studentRepository,
            InstructorRepository instructorRepository,
            CourseRepository courseRepository,
            ClassroomRepository classroomRepository,
            ScheduleRepository scheduleRepository,
            ExamRepository examRepository,
            EnrollmentRepository enrollmentRepository,
            ParticipationRepository participationRepository,
            PerformanceRepository performanceRepository,
            UserService userService,
            StudentService studentService,
            InstructorService instructorService,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {



            // ==== 2. DEPARTMENTS ====
            Department csDept = new Department();
            csDept.setDepartmentName("Computer Science");


            Department mathDept = new Department();
            mathDept.setDepartmentName("Mathematics");

            departmentRepository.saveAll(List.of(csDept, mathDept));

            // ==== 3. USERS ====
            User studentUser = new User();
            studentUser.setUsername("student1");
            studentUser.setEmail("student1@example.com");
            studentUser.setPasswordHash(passwordEncoder.encode("pass123"));
            studentUser.setRoleName(RoleName.STUDENT);
            studentUser.setCreatedAt(LocalDateTime.now());

            userRepository.save(studentUser);

            User instructorUser = new User();
            instructorUser.setUsername("instructor1");
            instructorUser.setEmail("instructor1@example.com");
            instructorUser.setPasswordHash(passwordEncoder.encode("pass123"));
            instructorUser.setRoleName(RoleName.INSTRUCTOR);
            instructorUser.setCreatedAt(LocalDateTime.now());

            userRepository.save(instructorUser);

            // ==== 4. STUDENT ====
            Student student = new Student();
            student.setFirstName("John");
            student.setLastName("Doe");
            student.setEmail("student1@example.com");
            student.setDateOfBirth(LocalDate.of(2000, 1, 1));
            student.setUser(studentUser);
            student.setDepartment(csDept);

            studentService.registerStudent(student);

            // ==== 5. INSTRUCTOR ====
            Instructor instructor = new Instructor();
            instructor.setFirstName("Jane");
            instructor.setLastName("Smith");
            instructor.setEmail("instructor1@example.com");
            instructor.setUser(instructorUser);
            instructor.setDepartment(mathDept);

            instructorService.registerInstructor(instructor);

            // ==== 6. CLASSROOMS ====
            Classroom roomA = new Classroom();
            roomA.setBuilding("Engineering");
            roomA.setRoomNumber("A101");
            roomA.setCapacity(30);

            Classroom roomB = new Classroom();
            roomB.setBuilding("Science");
            roomB.setRoomNumber("B202");
            roomB.setCapacity(40);

            classroomRepository.saveAll(List.of(roomA, roomB));

            // ==== 7. COURSES ====
            Course csCourse = new Course();
            csCourse.setCourseName("Intro to Programming");
            csCourse.setCredits(4);
            csCourse.setDepartment(csDept);
            csCourse.setInstructor(instructor);
            courseRepository.save(csCourse);

            Course mathCourse = new Course();
            mathCourse.setCourseName("Discrete Math");
            mathCourse.setCredits(3);
            mathCourse.setDepartment(mathDept);
            mathCourse.setInstructor(instructor);
            courseRepository.save(mathCourse);

            // ==== 8. SCHEDULES ====
            Schedule csSchedule = new Schedule();
            csSchedule.setCourse(csCourse);
            csSchedule.setClassroom(roomA);
            csSchedule.setScheduleDay("Monday");
            csSchedule.setStartTime(LocalTime.of(9, 0));
            csSchedule.setEndTime(LocalTime.of(11, 0));
            scheduleRepository.save(csSchedule);

            // ==== 9. EXAMS ====
            Exam exam = new Exam();
            exam.setCourse(csCourse);
            exam.setExamDate(LocalDate.now().plusDays(10));
            exam.setStartTime(LocalTime.of(10, 0));
            exam.setEndTime(LocalTime.of(12, 0));
            exam.setClassroom(roomA);
            exam.setExamType("MIDTERM");
            examRepository.save(exam);

            // ==== 10. ENROLLMENT ====
            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student);
            enrollment.setCourse(csCourse);
            enrollment.setEnrolledAt(LocalDateTime.now());
            enrollmentRepository.save(enrollment);

            // ==== 11. PARTICIPATION ====
            Participation participation = new Participation();
            participation.setStudent(student);
            participation.setSchedule(csSchedule);
            participation.setAttended(true);
            participation.setDate(LocalDate.now());
            participationRepository.save(participation);

            // ==== 12. PERFORMANCE ====
            Performance performance = new Performance();
            performance.setStudent(student);
            performance.setExam(exam);
            performance.setGrade(88.5);
            performanceRepository.save(performance);

            System.out.println("Data seeding complete.");
        };
    }
}