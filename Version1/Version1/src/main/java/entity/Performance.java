package entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="performance")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {})
public class Performance {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "performance_id")
    private int performanceId;


    @Column(name = "grade")
    private double grade;
}
