module lk.ijse.elightdrivingschool {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires static lombok;
    requires java.sql;
    requires jakarta.persistence;
    requires java.desktop;
    requires javafx.graphics;
    requires jbcrypt;
    requires java.naming;
    requires javafx.base;


    opens lk.ijse.elightdrivingschool to javafx.fxml;
    opens lk.ijse.elightdrivingschool.config to jakarta.persistence;
    opens lk.ijse.elightdrivingschool.entity to org.hibernate.orm.core;
    opens lk.ijse.elightdrivingschool.controller to javafx.fxml;
    opens lk.ijse.elightdrivingschool.dto.tm to javafx.base;

    exports lk.ijse.elightdrivingschool;
}