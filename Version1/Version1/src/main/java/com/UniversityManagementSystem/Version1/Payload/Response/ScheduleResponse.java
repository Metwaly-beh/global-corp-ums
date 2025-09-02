package com.UniversityManagementSystem.Version1.Payload.Response;

public class ScheduleResponse {
    private Long scheduleId;
    private String courseName;
    private String classroom;
    private String scheduleDay;
    private String startTime;
    private String endTime;

    public ScheduleResponse() {}

    public ScheduleResponse(Long scheduleId, String courseName, String classroom,
                            String scheduleDay, String startTime, String endTime) {
        this.scheduleId = scheduleId;
        this.courseName = courseName;
        this.classroom = classroom;
        this.scheduleDay = scheduleDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getScheduleId() { return scheduleId; }
    public void setScheduleId(Long scheduleId) { this.scheduleId = scheduleId; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public String getClassroom() { return classroom; }
    public void setClassroom(String classroom) { this.classroom = classroom; }
    public String getScheduleDay() { return scheduleDay; }
    public void setScheduleDay(String scheduleDay) { this.scheduleDay = scheduleDay; }
    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
}
