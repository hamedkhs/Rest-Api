package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    private @Id @GeneratedValue Long id;
    private String name;
    @Column(unique = true)
    private String nationalCode;
    private int age;
    private String job;
    private String phon;
    private String fieldOfClass;
    private int classNumber;
}