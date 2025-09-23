package lk.ijse.elightdrivingschool.bo.custom;

import lk.ijse.elightdrivingschool.bo.SuperBO;
import lk.ijse.elightdrivingschool.dto.StudentsDTO;

public interface EnrollBO extends SuperBO {
    public boolean saveStudents(StudentsDTO dto) throws Exception;
    public boolean updateStudents(StudentsDTO dto) throws Exception;
}
