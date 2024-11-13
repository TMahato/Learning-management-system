package com.example.lms.dto;

import java.util.List;
import java.util.Map;

public class StudentProgressReportDTO {
    private List<CourseGradeDTO> courseGrades;
    private Double gpa;  // Average grade across all courses
    private Map<String, List<Double>> gradeBreakdownByDifficulty;

    public StudentProgressReportDTO(List<CourseGradeDTO> courseGrades, Double gpa, Map<String, List<Double>> gradeBreakdownByDifficulty) {
        this.courseGrades = courseGrades;
        this.gpa = gpa;
        this.gradeBreakdownByDifficulty = gradeBreakdownByDifficulty;
    }

    // Getters and setters
    public List<CourseGradeDTO> getCourseGrades() {
        return courseGrades;
    }

    public void setCourseGrades(List<CourseGradeDTO> courseGrades) {
        this.courseGrades = courseGrades;
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public Map<String, List<Double>> getGradeBreakdownByDifficulty() {
        return gradeBreakdownByDifficulty;
    }

    public void setGradeBreakdownByDifficulty(Map<String, List<Double>> gradeBreakdownByDifficulty) {
        this.gradeBreakdownByDifficulty = gradeBreakdownByDifficulty;
    }
}
