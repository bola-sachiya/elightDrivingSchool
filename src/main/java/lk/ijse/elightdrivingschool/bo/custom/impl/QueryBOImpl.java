package lk.ijse.elightdrivingschool.bo.custom.impl;

import lk.ijse.elightdrivingschool.bo.custom.QueryBO;
import lk.ijse.elightdrivingschool.dao.DAOFactory;
import lk.ijse.elightdrivingschool.dao.DAOTypes;
import lk.ijse.elightdrivingschool.dao.custom.QueryDAO;

public class QueryBOImpl implements QueryBO {
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOTypes.QUERY);
    @Override
    public double getTotalCourseAmountByStudentId(String studentId) throws Exception {
        return queryDAO.getTotalCourseAmountByStudentId(studentId);
}

}
