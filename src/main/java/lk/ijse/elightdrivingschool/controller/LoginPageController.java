package lk.ijse.elightdrivingschool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.ijse.elightdrivingschool.bo.BOFactory;
import lk.ijse.elightdrivingschool.bo.BOTypes;
import lk.ijse.elightdrivingschool.bo.custom.UserBO;
import lk.ijse.elightdrivingschool.config.FactoryConfiguration;
import lk.ijse.elightdrivingschool.dto.UserDTO;
import lk.ijse.elightdrivingschool.util.AuthUtil;
import lk.ijse.elightdrivingschool.util.PasswordEncryption;
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOTypes.USER);

    public TextField txtUsername;
    public PasswordField txtPassword;
    public Label lblShowPassword;
    public TextField txtVisiblePassword;


    private final String passwordRegex = "^.{8,}$";
    private final String usernameRegex = "^[A-Za-z_-]+$";
    public Button btnLogIn;


    public void showPasswordOnMousePressedAction(MouseEvent mouseEvent) {
        txtVisiblePassword.setText(txtPassword.getText());
        txtVisiblePassword.setVisible(true);
        txtVisiblePassword.setManaged(true);

        txtPassword.setVisible(false);
        txtPassword.setManaged(false);
    }
    public void hidePasswordOnMouseReleasedAction(MouseEvent mouseEvent) {
        txtPassword.setText(txtVisiblePassword.getText());
        txtPassword.setVisible(true);
        txtPassword.setManaged(true);

        txtVisiblePassword.setVisible(false);
        txtVisiblePassword.setManaged(false);
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        String inputUsername = txtUsername.getText();
        String inputPassword = txtPassword.getText();

        if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill all the fields", ButtonType.OK).show();
            return;
        }

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            UserDTO user = userBO.getUserByUsername(inputUsername);

            if (user != null && PasswordEncryption.checkPassword(inputPassword, user.getPassword())) {

                AuthUtil.setCurrentUser(user);

                // Login success
                Parent dashboardRoot = FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"));
                Stage dashboardStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                dashboardStage.setScene(new Scene(dashboardRoot));
                dashboardStage.setTitle("Elite Driving School");
                dashboardStage.setMaximized(true);
                dashboardStage.show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Username or Password", ButtonType.OK).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Login Fail", ButtonType.OK).show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtUsername.textProperty().addListener((observable, oldValue, newValue) -> validFields());
        txtPassword.textProperty().addListener((observable, oldValue, newValue) -> validFields());
        btnLogIn.setDisable(true);
    }

    private void validFields() {
        boolean isValidUsername = txtUsername.getText().matches(usernameRegex);
        boolean isValidPassword = txtPassword.getText().matches(passwordRegex);

        txtUsername.setStyle("-fx-border-color: #7367F0; -fx-border-radius: 12px; -fx-background-radius: 12px;");
        txtPassword.setStyle("-fx-border-color: #7367F0; -fx-border-radius: 12px; -fx-background-radius: 12px;");

        if (!isValidUsername)
            txtUsername.setStyle("-fx-border-color: red; -fx-border-radius: 12px; -fx-background-radius: 12px;");
        if (!isValidPassword)
            txtPassword.setStyle("-fx-border-color: red; -fx-border-radius: 12px; -fx-background-radius: 12px;");

        btnLogIn.setDisable(!(isValidUsername && isValidPassword));
    }
}
