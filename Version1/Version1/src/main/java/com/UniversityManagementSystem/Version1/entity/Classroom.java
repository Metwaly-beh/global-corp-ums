package com.UniversityManagementSystem.Version1.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "classrooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"schedule", "exams"})
public class Classroom {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroom_id")
    private Integer classroomId;

    @Column(name = "building")
    private String building;

    @Column(name = "room_number", length = 10)
    private String roomNumber;

    @Column(name = "capacity")
    private Integer capacity;


    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Schedule> schedule;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Exam> exams;

    @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Exam> examList;

}
