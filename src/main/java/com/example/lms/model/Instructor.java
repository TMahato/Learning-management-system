package com.example.lms.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
