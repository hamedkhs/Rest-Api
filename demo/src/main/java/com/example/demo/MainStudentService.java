package com.example.demo;

import java.util.List;

public interface MainStudentService {

    public List<Student> getAllStudent();

    public Student getStudentById(long id);

    public Student createStudent(Student student);

    public Student updateStudent(long id, Student updaStudent);

    public void deleteStudent(long id);

}
