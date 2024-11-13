package com.example.lms.service;

import com.example.lms.model.Student;
import com.example.lms.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStudent() {
        Student student = new Student();
        student.setName("Alice");

        when(studentRepository.save(student)).thenReturn(student);

        Student savedStudent = studentService.createStudent(student);
        assertEquals("Alice", savedStudent.getName());
    }

    @Test
    void testGetStudentById() {
        Student student = new Student();
        student.setId(1L);
        student.setName("Alice");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Optional<Student> foundStudent = studentService.getStudentById(1L);
        assertTrue(foundStudent.isPresent());
        assertEquals("Alice", foundStudent.get().getName());
    }

    @Test
    void testUpdateStudent() {
        Student existingStudent = new Student();
        existingStudent.setId(1L);
        existingStudent.setName("Alice");

        Student updatedStudent = new Student();
        updatedStudent.setName("Bob");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(existingStudent)).thenReturn(existingStudent);

        Student result = studentService.updateStudent(1L, updatedStudent);
        assertEquals("Bob", result.getName());
    }

    @Test
    void testDeleteStudent() {
        studentService.deleteStudent(1L);
        verify(studentRepository, times(1)).deleteById(1L);
    }
}
