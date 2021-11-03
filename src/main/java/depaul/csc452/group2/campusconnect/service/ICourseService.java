package depaul.csc452.group2.campusconnect.service;

import depaul.csc452.group2.campusconnect.model.Course;

import java.util.List;

import javax.validation.Valid;

public interface ICourseService {
    public List<Course> findAll();

    public void addCourse(Course course);

    public Course update(@Valid Course course);
}
