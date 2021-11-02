package depaul.csc452.group2.campusconnect.frontend.controller;

import depaul.csc452.group2.campusconnect.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    @GetMapping
    public List<Student> getAllStudents() {
        List<Student> students = Arrays.asList(
                new Student(),
                new Student(),
                new Student(),
                new Student()
        );
        return students;
    }
}
