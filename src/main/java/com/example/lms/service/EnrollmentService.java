package com.example.lms.service;

import com.example.lms.model.Enrollment;
import com.example.lms.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public Enrollment enrollStudent(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getEnrollmentsByStudent(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public Optional<Enrollment> getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id);
    }

    public Enrollment updateEnrollment(Long id, Enrollment enrollmentDetails) {
        return enrollmentRepository.findById(id).map(enrollment -> {
            enrollment.setGrade(enrollmentDetails.getGrade());
            enrollment.setStudent(enrollmentDetails.getStudent());
            enrollment.setCourse(enrollmentDetails.getCourse());
            return enrollmentRepository.save(enrollment);
        }).orElseThrow(() -> new RuntimeException("Enrollment not found"));
    }

    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }
}
