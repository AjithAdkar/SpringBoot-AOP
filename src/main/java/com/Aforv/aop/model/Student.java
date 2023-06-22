package com.Aforv.aop.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "StudentTable")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String name;
    private String steam;
    private int age;
    private  String contactNumber;
}
