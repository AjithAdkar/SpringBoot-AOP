package com.Aforv.aop.service;

import java.util.List;
import java.util.Optional;

import com.Aforv.aop.customexception.EntryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Aforv.aop.model.Student;
import com.Aforv.aop.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
 private final StudentRepository studentRepository;

    public List<Student> getAllStudent() {
      return   studentRepository.findAll();
    }

    public Student findStudentById(int id) throws EntryNotFoundException {
        Optional<Student> optionalStudent=studentRepository.findById(id);
        return optionalStudent.orElseThrow(()->  new EntryNotFoundException("no entry found for id: "+id));
    }
    public List<Student> findStudentByName(String name) {
        return studentRepository.findByname(name);
        };

    public Student saveNewStudent(Student student) {
        return  studentRepository.save(student);
    }

    public ResponseEntity<?> updateContactNumber(int id, String contactNumber) throws EntryNotFoundException {
      if(studentRepository.existsById(id)) {
          Optional<Student> optionalStudent = studentRepository.findById(id);
          Student student = optionalStudent.orElseThrow(()-> new EntryNotFoundException("no entry found for id: "+id));
          student.setContactNumber(contactNumber);

       return new ResponseEntity<Student>(studentRepository.save(student), HttpStatus.ACCEPTED);
      }
      return new ResponseEntity<String>("no entry found for id: "+id, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> updateStudent(int id, Student student) {
        if(studentRepository.existsById(id)) {
            student.setId(id);
            return new ResponseEntity<Student>(studentRepository.save(student), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<String>("no entry found for id: "+id, HttpStatus.NOT_FOUND);
    }

    public String deleteStudent(int id) {
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            return  "entry deleted Id: "+id;
        }
        return "no entry found for Id: "+id;
    }

    public List<Student> saveBatchOfNewStudent(List<Student>student) {
        return studentRepository.saveAll(student);
    }
}
