package com.UniversityManagementSystem.Version1.Controllers;

import com.UniversityManagementSystem.Version1.Services.StudentService;
import com.UniversityManagementSystem.Version1.Services.Impl.StudentServiceImpl;
import com.UniversityManagementSystem.Version1.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        try {
            Student registeredStudent = studentService.registerStudent(student);
            return new ResponseEntity<>(registeredStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{studentId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and #studentId == authentication.principal.studentId)")
    public ResponseEntity<Student> getStudentById(@PathVariable int studentId) {
        try {
            Student student = studentService.getStudentById(studentId);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('INSTRUCTOR')")
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{studentId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and #studentId == authentication.principal.studentId)")
    public ResponseEntity<Student> updateStudent(@PathVariable int studentId, @RequestBody Student student) {
        try {
            Student updatedStudent = studentService.updateStudent(studentId, student);
            return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{studentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable int studentId) {
        try {
            studentService.deleteStudent(studentId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{studentId}/enroll/{courseId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and #studentId == authentication.principal.studentId)")
    public ResponseEntity<HttpStatus> enrollInCourse(@PathVariable int studentId, @PathVariable int courseId) {
        try {
            studentServiceImpl.enrollStudentInCourse(studentId, courseId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{studentId}/schedule")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and #studentId == authentication.principal.studentId)")
    public ResponseEntity<List<Schedule>> getStudentSchedule(@PathVariable int studentId) {
        try {
            List<Schedule> schedules = studentServiceImpl.getStudentSchedules(studentId);
            return new ResponseEntity<>(schedules, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{studentId}/performances")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and #studentId == authentication.principal.studentId)")
    public ResponseEntity<List<Performance>> getStudentPerformances(@PathVariable int studentId) {
        try {
            List<Performance> performances = studentServiceImpl.getAllPerformances(studentId);
            return new ResponseEntity<>(performances, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{studentId}/participations")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('STUDENT') and #studentId == authentication.principal.studentId)")
    public ResponseEntity<List<Participation>> getStudentParticipations(@PathVariable int studentId) {
        try {
            List<Participation> participations = studentServiceImpl.getAllParticipations(studentId);
            return new ResponseEntity<>(participations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
