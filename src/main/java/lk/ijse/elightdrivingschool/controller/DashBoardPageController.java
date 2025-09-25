package lk.ijse.elightdrivingschool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

public class DashBoardPageController {
    public AnchorPane ancDashBoardPage;

    public void btnStudentOnAction(ActionEvent actionEvent) {
        navigateTo("/view/StudentManagePage.fxml");
    }

    public void btnCourseOnAction(ActionEvent actionEvent) {
        navigateTo("/view/CourseManagePage.fxml");
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) {
        navigateTo("/view/PaymentManagePage.fxml");
    }

    public void btnLessonsOnAction(ActionEvent actionEvent) {
        navigateTo("/view/LessonsManagePage.fxml");
    }

    public void btnUserOnAction(ActionEvent actionEvent) {
        navigateTo("/view/UserManagePage.fxml");
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) {
    }


    private void navigateTo(String path) {
        try {
            ancDashBoardPage.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(path));

            anchorPane.prefWidthProperty().bind(ancDashBoardPage.widthProperty());
            anchorPane.prefHeightProperty().bind(ancDashBoardPage.heightProperty());

            ancDashBoardPage.getChildren().add(anchorPane);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Page not found..!").show();
            e.printStackTrace();
        }
    }

    public void btnInstructorOnAction(ActionEvent actionEvent) {navigateTo("/view/InstructorsManagePage.fxml");
    }
}
