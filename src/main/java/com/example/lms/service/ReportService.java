package com.example.lms.service;

import com.example.lms.dto.CourseGradeDTO;
import com.example.lms.dto.StudentProgressReportDTO;
import com.example.lms.model.Enrollment;
import com.example.lms.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {
    private final EnrollmentRepository enrollmentRepository;

    public ReportService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public StudentProgressReportDTO getStudentProgressReport(Long studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);

        // Calculate course grades and organize by difficulty
        List<CourseGradeDTO> courseGrades = new ArrayList<>();
        Map<String, List<Double>> gradeBreakdownByDifficulty = new HashMap<>();

        for (Enrollment enrollment : enrollments) {
            String difficulty = enrollment.getCourse().getDifficulty().name();
            Double grade = enrollment.getGrade();
            String courseTitle = enrollment.getCourse().getTitle();

            // Add to course grades list
            courseGrades.add(new CourseGradeDTO(courseTitle, grade, difficulty));

            // Add to grade breakdown map by difficulty
            gradeBreakdownByDifficulty.computeIfAbsent(difficulty, k -> new ArrayList<>()).add(grade);
        }

        // Calculate GPA
        Double gpa = courseGrades.stream()
                .map(CourseGradeDTO::getGrade)
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        return new StudentProgressReportDTO(courseGrades, gpa, gradeBreakdownByDifficulty);
    }
}
