package com.UniversityManagementSystem.Version1.Payload.Response;

public class GradeResponse {
    private Long performanceId;
    private String courseName;
    private String examType;
    private String examDate;
    private Float grade;

    public GradeResponse() {}

    public GradeResponse(Long performanceId, String courseName, String examType,
                         String examDate, Float grade) {
        this.performanceId = performanceId;
        this.courseName = courseName;
        this.examType = examType;
        this.examDate = examDate;
        this.grade = grade;
    }

    public Long getPerformanceId() { return performanceId; }
    public void setPerformanceId(Long performanceId) { this.performanceId = performanceId; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public String getExamType() { return examType; }
    public void setExamType(String examType) { this.examType = examType; }
    public String getExamDate() { return examDate; }
    public void setExamDate(String examDate) { this.examDate = examDate; }
    public Float getGrade() { return grade; }
    public void setGrade(Float grade) { this.grade = grade; }
}
