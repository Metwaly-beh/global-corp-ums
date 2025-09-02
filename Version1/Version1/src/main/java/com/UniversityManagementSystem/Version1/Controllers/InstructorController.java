package com.UniversityManagementSystem.Version1.Controllers;

import com.UniversityManagementSystem.Version1.Services.Impl.InstructorServiceImpl;
import com.UniversityManagementSystem.Version1.Services.Impl.StudentServiceImpl;
import com.UniversityManagementSystem.Version1.Services.InstructorService;
import com.UniversityManagementSystem.Version1.Services.StudentService;
import com.UniversityManagementSystem.Version1.entity.*;
import com.UniversityManagementSystem.Version1.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
@CrossOrigin(origins = "*")
public class InstructorController {

    @Autowired
    private InstructorServiceImpl instructorService;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private CurrentUser currentUser;

    // -------------------- Instructor CRUD --------------------

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Instructor> registerInstructor(@RequestBody Instructor instructor) {
        Instructor registered = instructorService.registerInstructor(instructor);
        return ResponseEntity.status(201).body(registered);
    }

    @GetMapping("/{instructorId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('INSTRUCTOR')")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable int instructorId) {
        if (!isAuthorizedInstructor(instructorId)) return ResponseEntity.status(403).build();

        Instructor instructor = instructorService.getInstructorById(instructorId);
        return ResponseEntity.ok(instructor);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        return ResponseEntity.ok(instructorService.getAllInstructors());
    }

    @PutMapping("/{instructorId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('INSTRUCTOR')")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable int instructorId, @RequestBody Instructor instructor) {
        if (!isAuthorizedInstructor(instructorId)) return ResponseEntity.status(403).build();

        Instructor updated = instructorService.updateInstructor(instructorId, instructor);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{instructorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteInstructor(@PathVariable int instructorId) {
        instructorService.deleteInstructor(instructorId);
        return ResponseEntity.noContent().build();
    }

    // -------------------- Courses --------------------

    @GetMapping("/{instructorId}/courses")
    @PreAuthorize("hasRole('ADMIN') or hasRole('INSTRUCTOR')")
    public ResponseEntity<List<Course>> getInstructorCourses(@PathVariable int instructorId) {
        if (!isAuthorizedInstructor(instructorId)) return ResponseEntity.status(403).build();

        return ResponseEntity.ok(instructorService.getInstructorCourses(instructorId));
    }

    @PostMapping("/courses")
    @PreAuthorize("hasRole('ADMIN') or hasRole('INSTRUCTOR')")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course created = instructorService.setCourse(course);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/courses/{courseId}/enrollments")
    @PreAuthorize("hasRole('ADMIN') or hasRole('INSTRUCTOR')")
    public ResponseEntity<List<Enrollment>> getCourseEnrollments(@PathVariable int courseId) {
        return ResponseEntity.ok(instructorService.getCourseEnrollments(courseId));
    }

    // -------------------- Exams --------------------

    @PostMapping("/exams")
    @PreAuthorize("hasRole('ADMIN') or hasRole('INSTRUCTOR')")
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        Exam created = instructorService.setExam(exam);
        return ResponseEntity.status(201).body(created);
    }

    @GetMapping("/courses/{courseId}/exams")
    @PreAuthorize("hasRole('ADMIN') or hasRole('INSTRUCTOR')")
    public ResponseEntity<List<Exam>> getCourseExams(@PathVariable int courseId) {
        return ResponseEntity.ok(instructorService.getCourseExams(courseId));
    }

    // -------------------- Enrollment --------------------

    @GetMapping("/students/{studentId}/enrollment")
    @PreAuthorize("hasRole('ADMIN') or hasRole('INSTRUCTOR')")
    public ResponseEntity<Enrollment> getStudentEnrollment(@PathVariable int studentId) {
        Student student = studentService.getStudentById(studentId);
        Enrollment enrollment = instructorService.getStudentEnrollment(student);
        return ResponseEntity.ok(enrollment);
    }

    // -------------------- Internal Access Check --------------------

    private boolean isAuthorizedInstructor(int instructorId) {
        return currentUser.getRole().equalsIgnoreCase("ADMIN") ||
                (currentUser.getInstructorId() != null && currentUser.getInstructorId() == instructorId);
    }
}
