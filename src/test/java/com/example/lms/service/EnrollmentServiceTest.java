package com.example.lms.service;

import com.example.lms.model.Course;
import com.example.lms.model.Enrollment;
import com.example.lms.model.Student;
import com.example.lms.repository.EnrollmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    private Student student;
    private Course course;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student();
        student.setId(1L);
        course = new Course();
        course.setId(1L);
    }

    @Test
    void testEnrollStudent() {
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        when(enrollmentRepository.save(enrollment)).thenReturn(enrollment);

        Enrollment savedEnrollment = enrollmentService.enrollStudent(enrollment);
        assertNotNull(savedEnrollment);
        assertEquals(1L, savedEnrollment.getStudent().getId());
        assertEquals(1L, savedEnrollment.getCourse().getId());
    }

    @Test
    void testGetEnrollmentsByStudent() {
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        when(enrollmentRepository.findByStudentId(1L)).thenReturn(List.of(enrollment));

        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(1L);
        assertEquals(1, enrollments.size());
        assertEquals(1L, enrollments.get(0).getStudent().getId());
    }

    @Test
    void testUpdateEnrollment() {
        Enrollment existingEnrollment = new Enrollment();
        existingEnrollment.setId(1L);
        existingEnrollment.setGrade(85.0);

        Enrollment updatedEnrollment = new Enrollment();
        updatedEnrollment.setGrade(90.0);

        when(enrollmentRepository.findById(1L)).thenReturn(Optional.of(existingEnrollment));
        when(enrollmentRepository.save(existingEnrollment)).thenReturn(existingEnrollment);

        Enrollment result = enrollmentService.updateEnrollment(1L, updatedEnrollment);
        assertEquals(90.0, result.getGrade());
    }
}
