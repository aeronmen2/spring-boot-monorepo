package com.alexis.school.controller;

import com.alexis.school.dto.SchoolDTO;
import com.alexis.school.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schools")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;

    @GetMapping
    public List<SchoolDTO> getAllSchools() {
        return schoolService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolDTO> getSchoolById(@PathVariable Long id) {
        return schoolService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SchoolDTO createSchool(@RequestBody SchoolDTO schoolDTO) {
        return schoolService.save(schoolDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchool(@PathVariable Long id) {
        schoolService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
