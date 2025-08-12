package entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="participation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"schedule","students"})
public class Participation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participation_id")
    private int participationId;


    @Column(name = "attended")
    private boolean attended;

    @Column(name = "date")
    private LocalDate date;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Students students;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="schedule_id")
    private Schedule schedule;


}
