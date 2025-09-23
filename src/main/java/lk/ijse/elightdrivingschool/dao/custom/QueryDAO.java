package lk.ijse.elightdrivingschool.dao.custom;

import lk.ijse.elightdrivingschool.dao.SuperDAO;

public interface QueryDAO extends SuperDAO {
    public int getStudentCountForLesson(String lessonId);
    double getTotalCourseAmountByStudentId(String studentId) throws Exception;
}