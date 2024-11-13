package com.example.lms.service;

import com.example.lms.dto.StudentProgressReportDTO;
import com.example.lms.model.Course;
import com.example.lms.model.Difficulty;
import com.example.lms.model.Enrollment;
import com.example.lms.model.Student;
import com.example.lms.repository.EnrollmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReportServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private ReportService reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetStudentProgressReport() {
        Student student = new Student();
        student.setId(1L);

        Course course1 = new Course();
        course1.setTitle("Math 101");
        course1.setDifficulty(Difficulty.MEDIUM);

        Course course2 = new Course();
        course2.setTitle("Science 101");
        course2.setDifficulty(Difficulty.HARD);

        Enrollment enrollment1 = new Enrollment();
        enrollment1.setStudent(student);
        enrollment1.setCourse(course1);
        enrollment1.setGrade(80.0);

        Enrollment enrollment2 = new Enrollment();
        enrollment2.setStudent(student);
        enrollment2.setCourse(course2);
        enrollment2.setGrade(90.0);

        when(enrollmentRepository.findByStudentId(1L)).thenReturn(List.of(enrollment1, enrollment2));

        StudentProgressReportDTO report = reportService.getStudentProgressReport(1L);

        assertEquals(2, report.getCourseGrades().size());
        assertEquals(85.0, report.getGpa());  // Average of 80.0 and 90.0
        assertTrue(report.getGradeBreakdownByDifficulty().containsKey("MEDIUM"));
        assertTrue(report.getGradeBreakdownByDifficulty().containsKey("HARD"));
    }
}
