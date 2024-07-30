package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
class StudentController {

  @Autowired
  private final MainStudentService studentService;

  StudentController(MainStudentService studentService) {
    this.studentService = studentService;
  }

  @GetMapping()
  String getpage() {
    return "welcom to page";
  }

  @GetMapping("/all")
  public ResponseEntity<List<Student>> getAllStudent() {

    List<Student> students = studentService.getAllStudent();
    return ResponseEntity.ok(students);
  }

  @PostMapping("/save")
  public ResponseEntity<Student> createStudent(@RequestBody Student student) {

    Student createStudent = studentService.createStudent(student);
    return ResponseEntity.status(HttpStatus.CREATED).body(createStudent);
  }

  // Single item

  @GetMapping("/{id}")
  public ResponseEntity<Student> getStudentById(@PathVariable Long id) {

    Student student = studentService.getStudentById(id);
    if (student != null) {
      return ResponseEntity.ok(student);
    } else
      throw new StudentNotFoundException(id);

  }

  @PutMapping("/{id}")
  public ResponseEntity<Student> updateStudent(@RequestBody Student updateStudent, @PathVariable Long id) {

    Student student = studentService.updateStudent(id, updateStudent);
    if (student != null) {
      return ResponseEntity.ok(student);
    } else
      throw new StudentNotFoundException(id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
    studentService.deleteStudent(id);
    return ResponseEntity.noContent().build();
  }
}