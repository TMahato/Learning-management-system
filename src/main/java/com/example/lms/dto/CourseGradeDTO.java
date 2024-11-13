package com.example.lms.dto;

public class CourseGradeDTO {
    private String courseTitle;
    private Double grade;
    private String difficulty;

    public CourseGradeDTO(String courseTitle, Double grade, String difficulty) {
        this.courseTitle = courseTitle;
        this.grade = grade;
        this.difficulty = difficulty;
    }

    // Getters and setters
    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
