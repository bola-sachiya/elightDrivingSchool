
package lk.ijse.elightdrivingschool.entity;

import jakarta.persistence.*;
import lk.ijse.elightdrivingschool.entity.Students;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "payments")
public class Payments {

    @Id
    @Column
    private String paymentId;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "studentId")
    private Students student;

    @Column
    private Date paymentDate;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private String status;
}

