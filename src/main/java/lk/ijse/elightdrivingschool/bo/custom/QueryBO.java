package lk.ijse.elightdrivingschool.bo.custom;

import lk.ijse.elightdrivingschool.bo.SuperBO;

public interface QueryBO extends SuperBO {
    double getTotalCourseAmountByStudentId(String studentId) throws Exception;

}
