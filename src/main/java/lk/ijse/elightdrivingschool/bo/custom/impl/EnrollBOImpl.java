package lk.ijse.elightdrivingschool.bo.custom.impl;

import lk.ijse.elightdrivingschool.bo.custom.EnrollBO;
import lk.ijse.elightdrivingschool.dao.DAOFactory;
import lk.ijse.elightdrivingschool.dao.DAOTypes;
import lk.ijse.elightdrivingschool.dao.custom.CourseDAO;
import lk.ijse.elightdrivingschool.dao.custom.StudentsDAO;
import lk.ijse.elightdrivingschool.dto.CourseDTO;
import lk.ijse.elightdrivingschool.dto.StudentsDTO;
import lk.ijse.elightdrivingschool.entity.Course;
import lk.ijse.elightdrivingschool.entity.Students;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnrollBOImpl implements EnrollBO {

    private final CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOTypes.COURSE);
    private final StudentsDAO studentsDAO = (StudentsDAO) DAOFactory.getInstance().getDAO(DAOTypes.STUDENTS);

    @Override
    public boolean saveStudents(StudentsDTO dto) throws Exception {
        Students student = new Students();
        student.setStudentId(dto.getStudentId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setAddress(dto.getAddress());
        student.setDob(dto.getDob());
        student.setRegistrationDate(dto.getRegistrationDate());

        List<Course> courses = new ArrayList<>();
        if (dto.getCourses() != null) {
            for (CourseDTO c : dto.getCourses()) {
                Optional<Course> courseOpt = courseDAO.findById(c.getCourse_id());
                Course courseEntity = courseOpt.orElseThrow(
                        () -> new RuntimeException("Course not found: " + c.getCourse_id())
                );
                if (!courses.contains(courseEntity)) { // avoid duplicates
                    courses.add(courseEntity);
                }
            }
        }
        student.setCourses(courses);

        return studentsDAO.save(student);
    }

    @Override
    public boolean updateStudents(StudentsDTO dto) throws Exception {
        Optional<Students> studentOpt = studentsDAO.findById(dto.getStudentId());
        if (studentOpt.isEmpty()) return false;

        Students student = studentOpt.get();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());
        student.setAddress(dto.getAddress());
        student.setDob(dto.getDob());
        student.setRegistrationDate(dto.getRegistrationDate());

        student.getCourses().clear();
        if (dto.getCourses() != null) {
            for (CourseDTO c : dto.getCourses()) {
                Optional<Course> courseOpt = courseDAO.findById(c.getCourse_id());
                courseOpt.ifPresent(courseEntity -> {
                    if (!student.getCourses().contains(courseEntity)) {
                        student.getCourses().add(courseEntity);
                    }
                });
            }
        }

        return studentsDAO.update(student);
    }}
