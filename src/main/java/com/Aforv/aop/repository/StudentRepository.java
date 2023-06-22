package com.Aforv.aop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Aforv.aop.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
    public List<Student> findByname(String name);
}
