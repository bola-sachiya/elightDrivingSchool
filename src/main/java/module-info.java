module lk.ijse.elightdrivingschool {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;
    requires jakarta.persistence;


    opens lk.ijse.elightdrivingschool to javafx.fxml;
    exports lk.ijse.elightdrivingschool;
}