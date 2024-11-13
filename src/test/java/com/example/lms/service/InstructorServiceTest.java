package com.example.lms.service;

import com.example.lms.model.Instructor;
import com.example.lms.repository.InstructorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InstructorServiceTest {

    @Mock
    private InstructorRepository instructorRepository;

    @InjectMocks
    private InstructorService instructorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateInstructor() {
        Instructor instructor = new Instructor();
        instructor.setName("Dr. John Doe");

        when(instructorRepository.save(instructor)).thenReturn(instructor);

        Instructor savedInstructor = instructorService.createInstructor(instructor);
        assertEquals("Dr. John Doe", savedInstructor.getName());
    }

    @Test
    void testGetInstructorById() {
        Instructor instructor = new Instructor();
        instructor.setId(1L);
        instructor.setName("Dr. John Doe");

        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));

        Optional<Instructor> foundInstructor = instructorService.getInstructorById(1L);
        assertTrue(foundInstructor.isPresent());
        assertEquals("Dr. John Doe", foundInstructor.get().getName());
    }

    @Test
    void testUpdateInstructor() {
        Instructor existingInstructor = new Instructor();
        existingInstructor.setId(1L);
        existingInstructor.setName("Dr. John Doe");

        Instructor updatedInstructor = new Instructor();
        updatedInstructor.setName("Dr. Jane Doe");

        when(instructorRepository.findById(1L)).thenReturn(Optional.of(existingInstructor));
        when(instructorRepository.save(existingInstructor)).thenReturn(existingInstructor);

        Instructor result = instructorService.updateInstructor(1L, updatedInstructor);
        assertEquals("Dr. Jane Doe", result.getName());
    }

    @Test
    void testDeleteInstructor() {
        instructorService.deleteInstructor(1L);
        verify(instructorRepository, times(1)).deleteById(1L);
    }
}
