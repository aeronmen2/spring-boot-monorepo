package com.alexis.school.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "schools")

public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
}