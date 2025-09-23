package lk.ijse.elightdrivingschool.dto.tm;

import javafx.event.ActionEvent;
import lk.ijse.elightdrivingschool.dto.CourseDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class StudentTM extends ActionEvent {
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Date dob;
    private Date registrationDate;
    private String courseId;
}