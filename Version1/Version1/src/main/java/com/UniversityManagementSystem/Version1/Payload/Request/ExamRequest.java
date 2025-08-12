package com.UniversityManagementSystem.Version1.Payload.Request;

public class ExamRequest {
    private Long courseId;
    private String examDate;
    private String startTime;
    private String endTime;
    private Long classroomId;
    private String examType;

    public ExamRequest() {}

    public ExamRequest(Long courseId, String examDate, String startTime,
                       String endTime, Long classroomId, String examType) {
        this.courseId = courseId;
        this.examDate = examDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classroomId = classroomId;
        this.examType = examType;
    }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    public String getExamDate() { return examDate; }
    public void setExamDate(String examDate) { this.examDate = examDate; }
    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
    public Long getClassroomId() { return classroomId; }
    public void setClassroomId(Long classroomId) { this.classroomId = classroomId; }
    public String getExamType() { return examType; }
    public void setExamType(String examType) { this.examType = examType; }
}
