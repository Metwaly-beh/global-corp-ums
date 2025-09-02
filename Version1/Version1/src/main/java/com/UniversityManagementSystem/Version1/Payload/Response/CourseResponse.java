package com.UniversityManagementSystem.Version1.Payload.Response;

public class CourseResponse {
    private Long courseId;
    private String courseName;
    private Integer credits;
    private String departmentName;
    private String instructorName;

    public CourseResponse() {}

    public CourseResponse(Long courseId, String courseName, Integer credits,
                          String departmentName, String instructorName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.departmentName = departmentName;
        this.instructorName = instructorName;
    }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }
    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
    public String getInstructorName() { return instructorName; }
    public void setInstructorName(String instructorName) { this.instructorName = instructorName; }
}
