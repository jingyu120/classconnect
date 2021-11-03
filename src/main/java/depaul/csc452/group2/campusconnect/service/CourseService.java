package depaul.csc452.group2.campusconnect.service;

import depaul.csc452.group2.campusconnect.model.Course;
import depaul.csc452.group2.campusconnect.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService{
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findAll() {
        return courseRepository.findAll();
    }
}
