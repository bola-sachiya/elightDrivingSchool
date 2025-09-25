package lk.ijse.elightdrivingschool.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import lk.ijse.elightdrivingschool.bo.BOFactory;
import lk.ijse.elightdrivingschool.bo.BOTypes;
import lk.ijse.elightdrivingschool.bo.custom.UserBO;
import lk.ijse.elightdrivingschool.controller.util.PasswordEncryption;
import lk.ijse.elightdrivingschool.dto.UserDTO;
import lk.ijse.elightdrivingschool.dto.tm.UserTM;

import java.net.URL;
import java.util.ResourceBundle;

public class UserPopUpController implements Initializable {

    private final UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOTypes.USER);

    public Label lblUserId;
    public Button btnUpdate;
    public Button btnSave;
    public PasswordField txtConfirmPassword;
    public PasswordField txtPassword;
    public ComboBox cmbRole;
    public TextField txtEmail;
    public TextField txtUsername;

    private final String passwordRegex = "^.{8,}$";
    private final String usernameRegex = "^[A-Za-z_-]+$";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            lblUserId.setText(userBO.generateNextUserId());
            cmbRole.setItems(FXCollections.observableArrayList("Admin", "Receptionist"));
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public void btnSaveOncAction(ActionEvent actionEvent) {
        try {
            String userId = lblUserId.getText();
            String username = txtUsername.getText();
            String email = txtEmail.getText();
            String role = cmbRole.getValue() != null ? cmbRole.getValue().toString() : "";
            String password = txtPassword.getText();
            String confirmPassword = txtConfirmPassword.getText();
            String status = "Active";

            if (!username.matches(usernameRegex)) {
                new Alert(Alert.AlertType.ERROR, "Invalid username!").show();
                return;
            }
            if (!password.matches(passwordRegex)) {
                new Alert(Alert.AlertType.ERROR, "Password must be at least 8 characters!").show();
                return;
            }
            if (!password.equals(confirmPassword)) {
                new Alert(Alert.AlertType.ERROR, "Passwords do not match!").show();
                return;
            }

            String encryptedPassword = PasswordEncryption.hashPassword(password);

            boolean isSaved = userBO.saveUsers(new UserDTO(
                    userId,
                    username,
                    encryptedPassword,
                    role,
                    email,
                    status
            ));
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "User saved successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to save user!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        try {
            String userId = lblUserId.getText();
            String username = txtUsername.getText();
            String email = txtEmail.getText();
            String role = cmbRole.getValue() != null ? cmbRole.getValue().toString() : "";
            String password = txtPassword.getText();
            String confirmPassword = txtConfirmPassword.getText();
            String status = "Active";

            if (!username.matches(usernameRegex)) {
                new Alert(Alert.AlertType.ERROR, "Invalid username!").show();
                return;
            }
            if (!password.matches(passwordRegex)) {
                new Alert(Alert.AlertType.ERROR, "Password must be at least 8 characters!").show();
                return;
            }
            if (!password.equals(confirmPassword)) {
                new Alert(Alert.AlertType.ERROR, "Passwords do not match!").show();
                return;
            }

            String encryptedPassword = PasswordEncryption.hashPassword(password);

            boolean isUpdated = userBO.updateUsers(new UserDTO(
                    userId,
                    username,
                    encryptedPassword,
                    role,
                    email,
                    status
            ));
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "User updated successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update user!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage()).show();
        }
    }

    public void setUserData(UserTM selectedItem) {
        lblUserId.setText(selectedItem.getUserId());
        txtUsername.setText(selectedItem.getUserName());
        txtPassword.setText(selectedItem.getPassword());
        txtConfirmPassword.setText(selectedItem.getPassword());
        txtEmail.setText(selectedItem.getEmail());
        cmbRole.setValue(selectedItem.getRole());
        btnSave.setDisable(true);
        lblUserId.setDisable(true);
    }
}
