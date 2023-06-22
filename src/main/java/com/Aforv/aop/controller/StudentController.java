package com.Aforv.aop.controller;

import com.Aforv.aop.customexception.EntryNotFoundException;
import com.Aforv.aop.model.Student;
import com.Aforv.aop.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/aop")
@RequiredArgsConstructor
public class StudentController {
private final StudentService studentService;

    @GetMapping("/")
    public List<Student> findAllStudent(){
        return studentService.getAllStudent() ;
    }

    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable int id) throws EntryNotFoundException {
        return studentService.findStudentById(id);
    }
    
    @GetMapping("/name/{name}")
    public List<Student> findStudentByName(@PathVariable String name){
        return studentService.findStudentByName(name) ;
    }
    
    @PostMapping("/")
     public Student saveNewStudent(@RequestBody Student student){
        return studentService.saveNewStudent(student);
    }
    @PostMapping("/batch")
    public List<Student> saveBatchOfNewStudent(@RequestBody List<Student> student){
       return studentService.saveBatchOfNewStudent(student);
   }
   
    @PatchMapping("/{id}/{contactNumber}")
    public ResponseEntity<?> updateContactNumber(@PathVariable int id, @PathVariable String contactNumber ) throws EntryNotFoundException {
        return studentService.updateContactNumber(id,contactNumber);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody Student student){
        return  studentService.updateStudent(id,student);
    }
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id){
        return  studentService.deleteStudent(id);
    }
}
