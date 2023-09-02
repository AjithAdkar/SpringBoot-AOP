package com.Aforv.aop.controller;

import com.Aforv.aop.customannotation.PerformanceMonitor;
import com.Aforv.aop.dto.StudentDTO;
import com.Aforv.aop.exceptionhandler.EntryNotFoundException;
import com.Aforv.aop.model.Student;
import com.Aforv.aop.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;


    @PerformanceMonitor
    @GetMapping("/all")
    public List<StudentDTO> findAllStudent() throws InterruptedException {
        return studentService.getAllStudent();
    }

    @GetMapping("/{id}")
    public StudentDTO StudentDTO(@PathVariable int id) throws EntryNotFoundException {
        return studentService.findStudentById(id);
    }

    @GetMapping("/name/{name}")
    public List<Student> findStudentByName(@PathVariable String name) throws EntryNotFoundException {
        return studentService.findStudentByName(name);
    }

    @PostMapping("/")
    public Student saveNewStudent(@RequestBody Student student) {
        return studentService.saveNewStudent(student);
    }

    @PostMapping("/batch")
    public List<Student> saveBatchOfNewStudent(@RequestBody List<Student> student) {
        return studentService.saveBatchOfNewStudent(student);
    }

    @PatchMapping("/{id}/{contactNumber}")
    public ResponseEntity<Student> updateContactNumber(@PathVariable int id, @PathVariable String contactNumber) throws EntryNotFoundException {
        return ResponseEntity.accepted().body(studentService.updateContactNumber(id, contactNumber));
    }

    @PutMapping("/{role}/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable String role, @PathVariable int id, @RequestBody Student student) throws EntryNotFoundException {
        return ResponseEntity.accepted().body(studentService.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        return studentService.deleteStudent(id);
    }
}
