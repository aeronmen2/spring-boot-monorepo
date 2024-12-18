package com.alexis.school.service;

import com.alexis.school.dto.SchoolDTO;
import com.alexis.school.model.School;
import com.alexis.school.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public List<SchoolDTO> findAll() {
        return schoolRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<SchoolDTO> findById(Long id) {
        return schoolRepository.findById(id)
                .map(this::toDTO);
    }

    public SchoolDTO save(SchoolDTO schoolDTO) {
        School school = toEntity(schoolDTO);
        return toDTO(schoolRepository.save(school));
    }

    public void deleteById(Long id) {
        schoolRepository.deleteById(id);
    }

    // Conversion Methods
    private SchoolDTO toDTO(School school) {
        SchoolDTO dto = new SchoolDTO();
        dto.setId(school.getId());
        dto.setName(school.getName());
        dto.setAddress(school.getAddress());
        return dto;
    }

    private School toEntity(SchoolDTO dto) {
        School school = new School();
        school.setId(dto.getId());
        school.setName(dto.getName());
        school.setAddress(dto.getAddress());
        return school;
    }
}
