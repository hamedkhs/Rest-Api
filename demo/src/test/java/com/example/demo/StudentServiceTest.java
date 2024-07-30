package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStudent() {
        List<Student> students = new ArrayList<>(); // Corrected ArrayList declaration

        students.add(new Student(1L, "hamed", "4670353497", 21, "student", "09922200295", "computer", 102));
        students.add(new Student(2L, "ali", "4006213054", 21, "student", "09927651965", "computer", 102));

        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = studentService.getAllStudent();

        assertEquals(2, result.size());
        assertEquals("hamed", result.get(0).getName());
        assertEquals("ali", result.get(1).getName());
    }

    @Test
    void testGetStudentById() {
        long studentId = 1L;
        Student student = new Student(studentId, "hamed", "4670353497", 21, "student", "09922200295", "computer", 102);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        Student result = studentService.getStudentById(studentId);

        assertEquals("hamed", result.getName());
        assertEquals("4670353497", result.getNationalCode());
    }

    @Test
    void testCreateStudent() {

        Student newStudent = new Student(1L, "hamed", "4670353497", 21, "student", "09922200295", "computer", 102);

        when(studentRepository.save(any())).thenReturn(newStudent);

                // Act
                Student result = studentService.createStudent(newStudent);

                // Assert
                assertNotNull(result);
                assertEquals("hamed", result.getName());
    }

    @Test
    void testUpdateStudent() {
        
        long studentId = 1L;
        Student existingStudent =new Student(1L, "hamed", "4670353497", 21, "student", "09922200295", "computer", 102);

        Student updatedStudent = new Student(2L, "ali", "4006213054", 21, "student", "09927651965", "computer", 102);


        when(studentRepository.findById(studentId)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(any())).thenReturn(updatedStudent);

        // Act
        Student result = studentService.updateStudent(studentId, updatedStudent);

        // Assert
        assertNotNull(result);
        assertEquals("ali", result.getName());
        assertEquals("4006213054", result.getNationalCode());
        // Add other assertions for remaining properties
    }

    @Test
    void testDeleteStudent() {
        
        long studentId = 1L;
        Student existingStudent =new Student(studentId, "hamed", "4670353497", 21, "student", "09922200295", "computer", 102);

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(existingStudent));

        // Act
        studentService.deleteStudent(studentId);

        // Assert
        verify(studentRepository, times(1)).deleteById(studentId);
    }

}
