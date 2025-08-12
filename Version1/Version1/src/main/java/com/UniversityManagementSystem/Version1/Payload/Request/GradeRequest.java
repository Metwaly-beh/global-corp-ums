package com.UniversityManagementSystem.Version1.Payload.Request;


public class GradeRequest {
    private Long studentId;
    private Long examId;
    private Float grade;

    public GradeRequest() {}

    public GradeRequest(Long studentId, Long examId, Float grade) {
        this.studentId = studentId;
        this.examId = examId;
        this.grade = grade;
    }

    public Long getStudentId() { return studentId; }
    public void setStudentId(Long studentId) { this.studentId = studentId; }
    public Long getExamId() { return examId; }
    public void setExamId(Long examId) { this.examId = examId; }
    public Float getGrade() { return grade; }
    public void setGrade(Float grade) { this.grade = grade; }
}
