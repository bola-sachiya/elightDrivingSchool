package lk.ijse.elightdrivingschool.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.elightdrivingschool.bo.BOFactory;
import lk.ijse.elightdrivingschool.bo.BOTypes;
import lk.ijse.elightdrivingschool.bo.custom.CourseBO;
import lk.ijse.elightdrivingschool.bo.custom.InstructorsBO;
import lk.ijse.elightdrivingschool.bo.custom.LessonsBO;
import lk.ijse.elightdrivingschool.bo.custom.StudentsBO;
import lk.ijse.elightdrivingschool.dto.LessonsDTO;
import lk.ijse.elightdrivingschool.dto.tm.LessonsTM;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class
LessonsPopUpController implements Initializable {

    private final LessonsBO lessonsBO = (LessonsBO) BOFactory.getInstance().getBO(BOTypes.LESSONS);
    private final StudentsBO studentBO = (StudentsBO) BOFactory.getInstance().getBO(BOTypes.STUDENTS);
    private final CourseBO courseBO = (CourseBO) BOFactory.getInstance().getBO(BOTypes.COURSE);
    private final InstructorsBO instructorsBO = (InstructorsBO) BOFactory.getInstance().getBO(BOTypes.INSTRUCTORS);

    public TextField txtLessonDate;
    public TextField txtStartTime;
    public TextField txtEndTime;
    public ComboBox cmbStudentId;
    public ComboBox cmbCourseId;
    public ComboBox cmbInstructorId;
    public Button btnSave;
    public Button btnUpdate;
    public Label lblLessonId;
    public ComboBox cmbStatus;


    public void btnSaveOncAction(ActionEvent actionEvent) {
        String lessonId = lblLessonId.getText();
        String lessonDateStr = txtLessonDate.getText();
        String startTimeStr = txtStartTime.getText();
        String endTimeStr = txtEndTime.getText();
        String status = cmbStatus.getValue() != null ?  cmbStatus.getValue().toString() : "";
        String studentId = cmbStudentId.getValue() != null ? cmbStudentId.getValue().toString() : "";
        String courseId = cmbCourseId.getValue() != null ? cmbCourseId.getValue().toString() : "";
        String instructorId = cmbInstructorId.getValue() != null ? cmbInstructorId.getValue().toString() : "";

        if (lessonId.isEmpty() || lessonDateStr.isEmpty() || startTimeStr.isEmpty() || endTimeStr.isEmpty() ||
                status.isEmpty() || studentId.isEmpty() || courseId.isEmpty() || instructorId.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
            return;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


            Date lessonDate = dateFormat.parse(lessonDateStr);
            String startTime = startTimeStr;
            String endTime = endTimeStr;

            boolean isSaved = lessonsBO.saveLessons(new LessonsDTO(
                    lessonId, studentId, courseId, instructorId, lessonDate, startTime,  endTime, status
            ));
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Lesson saved successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save lesson!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String lessonId = lblLessonId.getText();
        String lessonDateStr = txtLessonDate.getText();
        String startTimeStr = txtStartTime.getText();
        String endTimeStr = txtEndTime.getText();
        String status = cmbStatus.getValue() != null ?  cmbStatus.getValue().toString() : "";
        String studentId = cmbStudentId.getValue() != null ? cmbStudentId.getValue().toString() : "";
        String courseId = cmbCourseId.getValue() != null ? cmbCourseId.getValue().toString() : "";
        String instructorId = cmbInstructorId.getValue() != null ? cmbInstructorId.getValue().toString() : "";

        if (lessonId.isEmpty() || lessonDateStr.isEmpty() || startTimeStr.isEmpty() || endTimeStr.isEmpty() ||
                status.isEmpty() || studentId.isEmpty() || courseId.isEmpty() || instructorId.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields").show();
            return;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date lessonDate = dateFormat.parse(lessonDateStr);
            String startTime = startTimeStr;
            String endTime = endTimeStr;

            boolean isUpdated = lessonsBO.updateLessons(new LessonsDTO(
                    lessonId, studentId, courseId, instructorId, lessonDate, startTime, endTime, status
            ));
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Lesson updated successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update lesson!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            lblLessonId.setText(lessonsBO.generateNewLessonId());
            cmbStatus.setItems(FXCollections.observableArrayList("Scheduled", "Ongoing", "Cancelled"));
            cmbStudentId.setItems(FXCollections.observableArrayList(studentBO.getAllStudentIds()));
            cmbCourseId.setItems(FXCollections.observableArrayList(courseBO.getAllCourseIds()));
            cmbInstructorId.setItems(FXCollections.observableArrayList(instructorsBO.getAllInstructorIds
                    ()));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void setLessonsData(LessonsTM selectedItem) {
        lblLessonId.setText(selectedItem.getLesson_id());
        txtLessonDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(selectedItem.getLessonDate()));
        txtStartTime.setText(selectedItem.getStartTime().toString());
        txtEndTime.setText(selectedItem.getEndTime().toString());
        cmbStatus.setValue(selectedItem.getStatus());
        cmbStudentId.setValue(selectedItem.getStudent_id());
        cmbCourseId.setValue(selectedItem.getCourse_id());
        cmbInstructorId.setValue(selectedItem.getInstructor_id());
        btnSave.setDisable(true);
        btnUpdate.setDisable(false);
    }
}
