package com.example.lms.controller;

import com.example.lms.dto.StudentProgressReportDTO;
import com.example.lms.service.ReportService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/student/{studentId}/progress")
    public StudentProgressReportDTO getStudentProgressReport(@PathVariable Long studentId) {
        return reportService.getStudentProgressReport(studentId);
    }
}
