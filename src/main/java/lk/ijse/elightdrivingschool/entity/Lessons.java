package lk.ijse.elightdrivingschool.entity;

import jakarta.persistence.*;
import lk.ijse.elightdrivingschool.entity.Course;
import lk.ijse.elightdrivingschool.entity.Instructors;
import lk.ijse.elightdrivingschool.entity.Students;
import lombok.*;

import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "lessons")
public class Lessons {

    @Id
    @Column
    private String lessonId;

    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    private Students student;

    @ManyToOne
    @JoinColumn(name = "courseId", referencedColumnName = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "instructorId", referencedColumnName = "instructor_id")
    private Instructors instructor;

    @Column
    private Date lessonDate;

    @Column
    private Time startTime;

    @Column
    private Time endTime;

    @Column
    private String status;
}