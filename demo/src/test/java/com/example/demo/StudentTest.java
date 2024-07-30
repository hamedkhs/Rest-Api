package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
class StudentTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentService studentService;

    //@BeforeEach
    //void setUp() {
    //    MockitoAnnotations.openMocks(this);
    //}

    @Test
    void testNationalCodeUniqueness() {
        
        Student student1 = new Student(1L, "hamed", "4670353497", 21, "student", "09922200295", "computer", 102);
        Student student2 = new Student(2L, "ali", "4670353497", 20, "student", "09140856785", "computer", 102);

        
        when(studentRepository.save(student1)).thenReturn(student1);
        when(studentRepository.save(student2)).thenThrow(new DataIntegrityViolationException("duplicate national code"));

        // Act
        Student result = studentService.createStudent(student1);

        // Assert
        assertNotNull(result);
        assertEquals("hamed", result.getName());

        
        assertThrows(DataIntegrityViolationException.class,
        () -> studentService.createStudent(student2));

        
        verify(studentRepository).save(student1);
        verify(studentRepository).save(student2);
    }
}
