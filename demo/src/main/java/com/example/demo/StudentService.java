package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StudentService implements MainStudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public Student getStudentById(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student createStudent(Student student) {

        return studentRepository.save(student);
    }

    public Student updateStudent(long id, Student updaStudent) {

        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent != null) {

            existingStudent.setName(updaStudent.getName());
            existingStudent.setNationalCode(updaStudent.getNationalCode());
            existingStudent.setAge(updaStudent.getAge());
            existingStudent.setJob(updaStudent.getJob());
            existingStudent.setPhon(updaStudent.getPhon());
            existingStudent.setClassNumber(updaStudent.getClassNumber());
            existingStudent.setFieldOfClass(updaStudent.getFieldOfClass());

            return studentRepository.save(existingStudent);
        }
        return null;
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }
}
