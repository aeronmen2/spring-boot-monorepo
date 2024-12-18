package com.alexis.student.service;

import com.alexis.student.dto.StudentDTO;
import com.alexis.student.model.Student;
import com.alexis.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final RestTemplate restTemplate;

    public List<StudentDTO> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO save(StudentDTO studentDTO) {
        Student student = toEntity(studentDTO);
        return toDTO(studentRepository.save(student));
    }

    public void deleteById(String id) {
        studentRepository.deleteById(id);
    }

    public Optional<StudentDTO> findById(String id) {
        return studentRepository.findById(id)
                .map(this::toDTO);
    }


    public StudentWithSchool findStudentWithSchoolById(String id) {
        return studentRepository.findById(id)
                .map(student -> {
                    Object school = getSchoolById(student.getSchoolId());
                    return new StudentWithSchool(toDTO(student), school);
                })
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    private Object getSchoolById(Long schoolId) {
        String schoolServiceUrl = "http://school-service/schools/{id}";
        return restTemplate.getForObject(schoolServiceUrl, Object.class, schoolId);
    }


    private StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setSchoolId(student.getSchoolId());
        return dto;
    }

    private Student toEntity(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setSchoolId(dto.getSchoolId());
        return student;
    }

    public static class StudentWithSchool {
        public StudentDTO student;
        public Object school;

        public StudentWithSchool(StudentDTO student, Object school) {
            this.student = student;
            this.school = school;
        }
    }
}
