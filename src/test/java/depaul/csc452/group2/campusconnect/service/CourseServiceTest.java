package depaul.csc452.group2.campusconnect.service;

import depaul.csc452.group2.campusconnect.exceptions.CourseNotFoundException;
import depaul.csc452.group2.campusconnect.model.Course;
import depaul.csc452.group2.campusconnect.repo.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public
class CourseServiceTest {
    @Mock
    private CourseRepository courseRepository;
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        courseService = new CourseService(courseRepository);
    }

    @Test
    void checkFindAll() {
        courseService.findAll();
        verify(courseRepository).findAll();
    }


    @Test
    void update() {
        Course course = new Course(1L, "csc", 101, "intro to programming", "introduction");
        courseService.update(course);
        verify(courseRepository).save(course);
    }

    @Test
    void deleteById() {
        long id = 1L;
        courseService.deleteById(id);
        verify(courseRepository).deleteById(id);
    }

    @Test
    void findCourseByID() {
        long id = 1L;
        Course course = new Course(id, "csc", 101, "intro to programming", "introduction");
        given(courseRepository.findById(id)).willReturn(Optional.of(course));
        courseService.findCourseByID(id);
        verify(courseRepository).findById(id);
    }

    @Test
    void throwCourseNotFoundException() {
        long id = 1L;
        given(courseRepository.findById(id)).willReturn(Optional.empty());
        assertThatThrownBy(() -> courseService.findCourseByID(id))
                .isInstanceOf(CourseNotFoundException.class)
                .hasMessageContaining("Course not found for ID:" + id);
    }
}