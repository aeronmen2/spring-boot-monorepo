package com.alexis.student.controller;

import com.alexis.student.dto.StudentDTO;
import com.alexis.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable String id) {
        return studentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public StudentDTO createStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.save(studentDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable String id, @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.updateStudent(id, studentDTO));
    }

    @PatchMapping("/{id}/school")
    public ResponseEntity<StudentDTO> updateStudentSchool(@PathVariable String id, @RequestBody Long schoolId) {
        return ResponseEntity.ok(studentService.updateStudentSchool(id, schoolId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/with-school")
    public ResponseEntity<StudentService.StudentWithSchool> getStudentWithSchool(@PathVariable String id) {
        return ResponseEntity.ok(studentService.findStudentWithSchoolById(id));
    }
}
