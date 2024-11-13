package com.example.lms.service;

import com.example.lms.model.Instructor;
import com.example.lms.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Optional<Instructor> getInstructorById(Long id) {
        return instructorRepository.findById(id);
    }

    public Instructor updateInstructor(Long id, Instructor instructorDetails) {
        return instructorRepository.findById(id).map(instructor -> {
            instructor.setName(instructorDetails.getName());
            return instructorRepository.save(instructor);
        }).orElseThrow(() -> new RuntimeException("Instructor not found"));
    }

    public void deleteInstructor(Long id) {
        instructorRepository.deleteById(id);
    }
}
