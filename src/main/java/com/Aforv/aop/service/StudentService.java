package com.Aforv.aop.service;

import java.util.List;
import java.util.Optional;

import com.Aforv.aop.exceptionhandler.EntryNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.Aforv.aop.model.Student;
import com.Aforv.aop.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    public List<Student> getAllStudent() throws InterruptedException {
        Thread.sleep(100);
        return studentRepository.findAll();
    }

//    @Cacheable(value = "productsCache", key = "'allProducts'")
    public Student findStudentById(int id) throws EntryNotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new EntryNotFoundException("no entry found for id: " + id));
    }

    public List<Student> findStudentByName(String name) throws EntryNotFoundException {
        List<Student> students = studentRepository.findByName(name);
        if(students.isEmpty())
            throw new EntryNotFoundException("no Student found for name: " + name);
        return students;
    }

//    @CachePut(value = "productsCache", key = "'allProducts'")
    public Student saveNewStudent(Student student) {
        return studentRepository.save(student);
    }

//    @CachePut(value = "productsCache", key = "'allProducts'")
    public Student updateContactNumber(int id, String contactNumber) throws EntryNotFoundException {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        Student student = optionalStudent.orElseThrow(() -> new EntryNotFoundException("no entry found for id: " + id));
        student.setContactNumber(contactNumber);
        return studentRepository.save(student);
    }

//    @CachePut(value = "productsCache", key = "'allProducts'")
    public Student updateStudent(int id, Student student) throws EntryNotFoundException {
        if (!studentRepository.existsById(id))
            throw new EntryNotFoundException("no entry found for id: " + id);
        student.setId(id);
        return studentRepository.save(student);
    }

//    @CacheEvict(value = "productsCache", key = "'allProducts'")
    public String deleteStudent(int id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return "entry deleted Id: " + id;
        }
        return "no entry found for Id: " + id;
    }

    public List<Student> saveBatchOfNewStudent(List<Student> student) {
        return studentRepository.saveAll(student);
    }
}
