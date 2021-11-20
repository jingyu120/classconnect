package depaul.csc452.group2.campusconnect.service;

import depaul.csc452.group2.campusconnect.exceptions.CourseNotFoundException;
import depaul.csc452.group2.campusconnect.model.Course;
import depaul.csc452.group2.campusconnect.repo.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

//    public void addCourse(Course course) {
//        courseRepository.save(course);
//    }

    public void update(Course course) {
        courseRepository.save(course);
    }

    public void deleteById(long id) {
        courseRepository.deleteById(id);
    }

    public Course findCourseByID(long id) {
        Optional<Course> optional = courseRepository.findById(id);
        Course course = null;
        if (optional.isPresent()) {
            course = optional.get();
        } else {
            throw new CourseNotFoundException("Course not found for ID:" + id);
        }
        return course;
    }
}
