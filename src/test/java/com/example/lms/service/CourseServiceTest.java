package com.example.lms.service;

import com.example.lms.exception.ResourceNotFoundException;
import com.example.lms.model.Course;
import com.example.lms.model.Difficulty;
import com.example.lms.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCourse() {
        Course course = new Course();
        course.setTitle("Math 101");

        when(courseRepository.save(course)).thenReturn(course);

        Course savedCourse = courseService.createCourse(course);
        assertEquals("Math 101", savedCourse.getTitle());
    }

    @Test
    void testGetCourseById_Found() {
        Course course = new Course();
        course.setId(1L);
        course.setTitle("Math 101");

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        Course foundCourse = courseService.getCourseById(1L);
        assertNotNull(foundCourse);
        assertEquals("Math 101", foundCourse.getTitle());
    }

    @Test
    void testGetCourseById_NotFound() {
        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            courseService.getCourseById(1L);
        });
    }

    @Test
    void testUpdateCourse_Found() {
        Course existingCourse = new Course();
        existingCourse.setId(1L);
        existingCourse.setTitle("Math 101");
        existingCourse.setDescription("Basic Math");
        existingCourse.setDifficulty(Difficulty.EASY); // Use enum value

        Course updatedCourseDetails = new Course();
        updatedCourseDetails.setTitle("Math 102");
        updatedCourseDetails.setDescription("Advanced Math");
        updatedCourseDetails.setDifficulty(Difficulty.HARD); // Use enum value

        when(courseRepository.findById(1L)).thenReturn(Optional.of(existingCourse));
        when(courseRepository.save(existingCourse)).thenReturn(existingCourse);

        Course result = courseService.updateCourse(1L, updatedCourseDetails);

        // Assertions
        assertEquals("Math 102", result.getTitle());
        assertEquals("Advanced Math", result.getDescription());
        assertEquals(Difficulty.HARD, result.getDifficulty()); // Use enum value for assertion
    }

    @Test
    void testUpdateCourse_NotFound() {
        Course updatedCourseDetails = new Course();
        updatedCourseDetails.setTitle("Math 102");

        when(courseRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            courseService.updateCourse(1L, updatedCourseDetails);
        });
    }

    @Test
    void testDeleteCourse_Found() {
        when(courseRepository.existsById(1L)).thenReturn(true);

        courseService.deleteCourse(1L);
        verify(courseRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteCourse_NotFound() {
        when(courseRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            courseService.deleteCourse(1L);
        });
    }
}
