package com.Aforv.aop.dto;

import com.Aforv.aop.model.Student;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class StudentDTOMapper implements Function<Student,StudentDTO> {
    @Override
    public StudentDTO apply(Student student) {
        return new StudentDTO(student.getName(),student.getSteam(),student.getAge(),student.getContactNumber());
    }
}
