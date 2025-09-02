package com.UniversityManagementSystem.Version1.Payload.Request;

public class ScheduleRequest {
    private Long courseId;
    private Long classroomId;
    private String scheduleDay;
    private String startTime;
    private String endTime;

    public ScheduleRequest() {}

    public ScheduleRequest(Long courseId, Long classroomId, String scheduleDay,
                           String startTime, String endTime) {
        this.courseId = courseId;
        this.classroomId = classroomId;
        this.scheduleDay = scheduleDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }
    public Long getClassroomId() { return classroomId; }
    public void setClassroomId(Long classroomId) { this.classroomId = classroomId; }
    public String getScheduleDay() { return scheduleDay; }
    public void setScheduleDay(String scheduleDay) { this.scheduleDay = scheduleDay; }
    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
}
