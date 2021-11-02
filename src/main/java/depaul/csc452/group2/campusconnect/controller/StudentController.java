package depaul.csc452.group2.campusconnect.controller;

import depaul.csc452.group2.campusconnect.model.Student;
import depaul.csc452.group2.campusconnect.service.IStudentService;
import depaul.csc452.group2.campusconnect.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "students")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @GetMapping
    public ModelAndView showStudents() {
        ModelAndView mv = new ModelAndView("student_table");
        mv.addObject("students", studentService.findAll());
        return mv;
    }

    @GetMapping("demo")
    public List<Student> showDemo() {
        List<Student> students = Arrays.asList(
            new Student(),
            new Student(),
            new Student(),
            new Student(),
            new Student()
        );
        return students;
    }
}
