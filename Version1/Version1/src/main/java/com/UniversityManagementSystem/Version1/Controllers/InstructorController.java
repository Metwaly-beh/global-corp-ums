package com.UniversityManagementSystem.Version1.Controllers;

import com.UniversityManagementSystem.Version1.Services.InstructorService;
import com.UniversityManagementSystem.Version1.Services.Impl.InstructorServiceImpl;
import com.UniversityManagementSystem.Version1.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
@CrossOrigin(origins = "*")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private InstructorServiceImpl instructorServiceImpl;

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Instructor> registerInstructor(@RequestBody Instructor instructor) {
        try {
            Instructor registeredInstructor = instructorService.registerInstructor(instructor);
            return new ResponseEntity<>(registeredInstructor, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{instructorId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('INSTRUCTOR') and #instructorId == authentication.principal.instructorId)")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable int instructorId) {
        try {
            Instructor instructor = instructorService.getInstructorById(instructorId);
            return new ResponseEntity<>(instructor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        try {
            List<Instructor> instructors = instructorService.getAllInstructors();
            return new ResponseEntity<>(instructors, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{instructorId}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('INSTRUCTOR') and #instructorId == authentication.principal.instructorId)")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable int instructorId, @RequestBody Instructor instructor) {
        try {
            Instructor updatedInstructor = instructorService.updateInstructor(instructorId, instructor);
            return new ResponseEntity<>(updatedInstructor, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{instructorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteInstructor(@PathVariable int instructorId) {
        try {
            instructorService.deleteInstructor(instructorId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{instructorId}/courses")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('INSTRUCTOR') and #instructorId == authentication.principal.instructorId)")
    public ResponseEntity<List<Course>> getInstructorCourses(@PathVariable int instructorId) {
        try {
            List<Course> courses = instructorServiceImpl.getInstructorCourses(instructorId);
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/courses/{courseId}/enrollments")
    @PreAuthorize("hasRole('ADMIN') or hasRole('INSTRUCTOR')")
    public ResponseEntity<List<Enrollment>> getCourseEnrollments(@PathVariable int courseId) {
        try {
            List<Enrollment> enrollments = instructorServiceImpl.getCourseEnrollments(courseId);
            return new ResponseEntity<>(enrollments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/courses")
    @PreAuthorize("hasRole('ADMIN') or hasRole('INSTRUCTOR')")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        try {
            Course createdCourse = instructorService.setCourse(course);
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/exams")
    @PreAuthorize("hasRole('ADMIN') or hasRole('INSTRUCTOR')")
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        try {
            Exam createdExam = instructorService.setExam(exam);
            return new ResponseEntity<>(createdExam, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/courses/{courseId}/exams")
    @PreAuthorize("hasRole('ADMIN') or hasRole('INSTRUCTOR')")
    public ResponseEntity<List<Exam>> getCourseExams(@PathVariable int courseId) {
        try {
            List<Exam> exams = instructorServiceImpl.getCourseExams(courseId);
            return new ResponseEntity<>(exams, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
