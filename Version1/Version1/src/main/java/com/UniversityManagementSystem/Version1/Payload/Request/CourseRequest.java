package com.UniversityManagementSystem.Version1.Payload.Request;

public class CourseRequest {
    private String courseName;
    private Integer credits;
    private Long departmentId;
    private Long instructorId;

    public CourseRequest() {}

    public CourseRequest(String courseName, Integer credits, Long departmentId, Long instructorId) {
        this.courseName = courseName;
        this.credits = credits;
        this.departmentId = departmentId;
        this.instructorId = instructorId;
    }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }
    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }
    public Long getInstructorId() { return instructorId; }
    public void setInstructorId(Long instructorId) { this.instructorId = instructorId; }
}